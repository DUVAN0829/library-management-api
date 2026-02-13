package co.duvan.user.infrastructure.adapters.input.rest;

import co.duvan.user.application.ports.input.CreateUserUseCase;
import co.duvan.user.application.ports.input.DeleteUserUseCase;
import co.duvan.user.application.ports.input.GetUserUseCase;
import co.duvan.user.application.ports.input.UpdateUserUseCase;
import co.duvan.user.domain.enums.DocumentType;
import co.duvan.user.domain.enums.Gender;
import co.duvan.user.domain.exceptions.UserNotFoundException;
import co.duvan.user.infrastructure.adapters.input.rest.mapper.UserRestMapper;
import co.duvan.user.infrastructure.adapters.input.rest.model.request.UserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserRestAdapter.class)
@Import(GlobalControllerAdvice.class)
class GlobalControllerAdviceTest {

    private static final String BASE_URL = "/users/api/v1";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GetUserUseCase getUserUseCase;

    @MockitoBean
    private CreateUserUseCase createUserUseCase;

    @MockitoBean
    private UpdateUserUseCase updateUserUseCase;

    @MockitoBean
    private DeleteUserUseCase deleteUserUseCase;

    @MockitoBean
    private UserRestMapper userRestMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_return_404_when_user_not_found() throws Exception {

        //* Given
        when(getUserUseCase.findById(99L))
                .thenThrow(new UserNotFoundException());

        //* When
        mockMvc.perform(get(BASE_URL + "/99"))

                //* Then
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("ERR_USER_01"))
                .andExpect(jsonPath("$.message").value("User not found"));
    }

    @Test
    void should_return_400_when_user_request_is_invalid() throws Exception {

        //* Given
        UserRequest invalidRequest = new UserRequest();
        invalidRequest.setFirstname(""); // asumiendo que tienes @NotBlank

        //* When
        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))

                //* Then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("ERR_USER_02"))
                .andExpect(jsonPath("$.message").value("Invalid user parameters"))
                .andExpect(jsonPath("$.details").isArray());
    }

    @Test
    void should_return_400_when_invalid_nationality_code() throws Exception {

        //* Given
        when(createUserUseCase.save(any()))
                .thenThrow(new IllegalArgumentException("Invalid ISO 3166-1 alpha-2 code"));

        UserRequest request = new UserRequest(
                "Duvan",
                "Gonzalez",
                DocumentType.IDENTITY_DOCUMENT,
                "12345678",
                LocalDate.of(1995, 5, 10),
                Gender.MALE,
                "duvan@email.com",
                "3001234567",
                "Co"
        );

        //* When
        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))

                //* Then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("ERR_USER_02"))
                .andExpect(jsonPath("$.message").value("Invalid user parameters"))
                .andExpect(jsonPath("$.details[0]")
                        .value("Invalid ISO 3166-1 alpha-2 code"));
    }

    @Test
    void should_return_500_when_generic_exception_occurs() throws Exception {

        //* Given
        when(getUserUseCase.findById(1L))
                .thenThrow(new RuntimeException("Something went wrong"));

        //* When
        mockMvc.perform(get(BASE_URL + "/1"))

                //* Then
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.code").value("GEN_ERR_01"))
                .andExpect(jsonPath("$.message").value("An unexpected error occurred"))
                .andExpect(jsonPath("$.details[0]")
                        .value("Something went wrong"));
    }

}