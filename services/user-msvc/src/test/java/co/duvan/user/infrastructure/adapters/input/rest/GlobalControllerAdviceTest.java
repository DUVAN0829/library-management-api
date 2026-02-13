package co.duvan.user.infrastructure.adapters.input.rest;

import co.duvan.user.application.ports.input.CreateUserUseCase;
import co.duvan.user.application.ports.input.DeleteUserUseCase;
import co.duvan.user.application.ports.input.GetUserUseCase;
import co.duvan.user.application.ports.input.UpdateUserUseCase;
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

}