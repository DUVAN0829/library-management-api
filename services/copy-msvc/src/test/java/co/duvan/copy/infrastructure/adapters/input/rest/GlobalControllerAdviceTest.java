package co.duvan.copy.infrastructure.adapters.input.rest;

import co.duvan.copy.application.ports.input.CreateCopyUseCase;
import co.duvan.copy.application.ports.input.DeleteCopyUseCase;
import co.duvan.copy.application.ports.input.GetCopyUseCase;
import co.duvan.copy.application.ports.input.UpdateCopyUseCase;
import co.duvan.copy.domain.exceptions.CopyNotFoundException;
import co.duvan.copy.infrastructure.adapters.input.rest.mapper.CopyRestMapper;
import co.duvan.copy.infrastructure.adapters.input.rest.model.request.CopyRequest;
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

@WebMvcTest(CopyRestAdapter.class)
@Import(GlobalControllerAdvice.class)
class GlobalControllerAdviceTest {

    private static final String BASE_URL = "/copies/api/v1";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GetCopyUseCase getCopyUseCase;

    @MockitoBean
    private CreateCopyUseCase createCopyUseCase;

    @MockitoBean
    private UpdateCopyUseCase updateCopyUseCase;

    @MockitoBean
    private DeleteCopyUseCase deleteCopyUseCase;

    @MockitoBean
    private CopyRestMapper copyRestMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_return_404_when_copy_not_found() throws Exception {

        //* Given
        when(getCopyUseCase.findById(99L)).thenThrow(new CopyNotFoundException());

        //* When
        mockMvc.perform(get(BASE_URL + "/99"))

                //* Then
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("ERR_COPY_01"))
                .andExpect(jsonPath("$.message").value("Copy not found"));
    }

    @Test
    void should_return_400_when_copy_request_is_invalid() throws Exception {

        //* Given
        CopyRequest invalidRequest = new CopyRequest();
        invalidRequest.setCode(""); // invalid (blank)

        //* When
        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))

                //* Then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("ERR_COPY_02"))
                .andExpect(jsonPath("$.message").value("Invalid copy parameters"))
                .andExpect(jsonPath("$.details").isArray());
    }

}