package co.duvan.user.infrastructure.adapters.input.rest;

import co.duvan.user.application.ports.input.CreateUserUseCase;
import co.duvan.user.application.ports.input.DeleteUserUseCase;
import co.duvan.user.application.ports.input.GetUserUseCase;
import co.duvan.user.application.ports.input.UpdateUserUseCase;
import co.duvan.user.domain.enums.DocumentType;
import co.duvan.user.domain.enums.Gender;
import co.duvan.user.domain.model.Nationality;
import co.duvan.user.domain.model.User;
import co.duvan.user.infrastructure.adapters.input.rest.mapper.UserRestMapper;
import co.duvan.user.infrastructure.adapters.input.rest.model.request.UserRequest;
import co.duvan.user.infrastructure.adapters.input.rest.model.response.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserRestAdapter.class)
class UserRestAdapterTest {

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

    private User user;
    private UserRequest userRequest;
    private UserResponse userResponse;


    @BeforeEach
    void setup() {

        user = User.builder()
                .userId(1L)
                .firstname("Duvan")
                .lastname("Gonzalez")
                .documentType(DocumentType.IDENTITY_DOCUMENT)
                .documentNumber("12345678")
                .birthdate(LocalDate.of(1995, 5, 10))
                .gender(Gender.MALE)
                .email("duvan@email.com")
                .phoneNumber("3001234567")
                .nationality(new Nationality("CO"))
                .build();

        userRequest = new UserRequest(
                "Duvan",
                "Gonzalez",
                DocumentType.IDENTITY_DOCUMENT,
                "12345678",
                LocalDate.of(1995, 5, 10),
                Gender.MALE,
                "duvan@email.com",
                "3001234567",
                "CO"
        );

        userResponse = new UserResponse(
                1L,
                "Duvan",
                "Gonzalez",
                DocumentType.PASSPORT,
                "12345678",
                LocalDate.of(1995, 5, 10),
                Gender.MALE,
                "duvan@email.com",
                "3001234567",
                "CO"
        );
    }

    @Test
    void should_get_user_by_id() throws Exception {

        //* Given
        when(getUserUseCase.findById(1L)).thenReturn(user);
        when(userRestMapper.toUserResponse(user)).thenReturn(userResponse);

        //* When
        mockMvc.perform(get(BASE_URL + "/1"))

                //* Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.email").value("duvan@email.com"));

        verify(getUserUseCase).findById(1L);
        verify(userRestMapper).toUserResponse(user);
    }

    @Test
    void should_get_all_users() throws Exception {

        //* Given
        when(getUserUseCase.findAll()).thenReturn(List.of(user));
        when(userRestMapper.toUserResponseList(List.of(user))).thenReturn(List.of(userResponse));

        //* When
        mockMvc.perform(get(BASE_URL))

                //* Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId").value(1))
                .andExpect(jsonPath("$[0].documentNumber").value("12345678"));

        verify(getUserUseCase).findAll();
        verify(userRestMapper).toUserResponseList(List.of(user));
    }

    @Test
    void should_create_user() throws Exception {

        //* Given
        when(userRestMapper.toUser(any(UserRequest.class))).thenReturn(user);
        when(createUserUseCase.save(any(User.class))).thenReturn(user);
        when(userRestMapper.toUserResponse(any(User.class))).thenReturn(userResponse);

        //* When
        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))

                //* Then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname").value("Duvan"));

        verify(createUserUseCase).save(any(User.class));
        verify(userRestMapper).toUser(any(UserRequest.class));
        verify(userRestMapper).toUserResponse(any(User.class));
    }

    @Test
    void should_update_user() throws Exception {

        //* Given
        when(userRestMapper.toUser(any(UserRequest.class))).thenReturn(user);
        when(updateUserUseCase.update(anyLong(), any(User.class))).thenReturn(user);
        when(userRestMapper.toUserResponse(any(User.class))).thenReturn(userResponse);

        //* When
        mockMvc.perform(put(BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))

                //* Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1));

        verify(updateUserUseCase).update(eq(1L), any(User.class));
        verify(userRestMapper).toUser(any(UserRequest.class));
        verify(userRestMapper).toUserResponse(any(User.class));
    }

    @Test
    void should_delete_user() throws Exception {

        //* Given
        doNothing().when(deleteUserUseCase).deleteById(1L);

        //* When
        mockMvc.perform(delete(BASE_URL + "/1"))

                //* Then
                .andExpect(status().isNoContent());

        verify(deleteUserUseCase).deleteById(1L);
    }

}