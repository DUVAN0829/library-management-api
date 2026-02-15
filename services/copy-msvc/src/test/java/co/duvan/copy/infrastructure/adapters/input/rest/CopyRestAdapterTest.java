package co.duvan.copy.infrastructure.adapters.input.rest;

import co.duvan.copy.application.ports.input.CreateCopyUseCase;
import co.duvan.copy.application.ports.input.DeleteCopyUseCase;
import co.duvan.copy.application.ports.input.GetCopyUseCase;
import co.duvan.copy.application.ports.input.UpdateCopyUseCase;
import co.duvan.copy.domain.enums.Status;
import co.duvan.copy.domain.model.Copy;
import co.duvan.copy.infrastructure.adapters.input.rest.mapper.CopyRestMapper;
import co.duvan.copy.infrastructure.adapters.input.rest.model.request.CopyRequest;
import co.duvan.copy.infrastructure.adapters.input.rest.model.response.CopyResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CopyRestAdapter.class)
class CopyRestAdapterTest {

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

    private Copy copy;
    private CopyResponse copyResponse;
    private CopyRequest copyRequest;

    @BeforeEach
    void setup() {

        copy = new Copy(
                1L,
                10L,
                "CP-001",
                Status.AVAILABLE
        );

        copyResponse = new CopyResponse(
                1L,
                10L,
                "CP-001",
                Status.AVAILABLE
        );

        copyRequest = new CopyRequest(
                10L,
                "CP-002",
                Status.LOANED
        );
    }

    @Test
    void should_get_copy_by_id() throws Exception {

        when(getCopyUseCase.findById(1L)).thenReturn(copy);
        when(copyRestMapper.toCopyResponse(copy)).thenReturn(copyResponse);

        mockMvc.perform(get(BASE_URL + "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.copyId").value(1))
                .andExpect(jsonPath("$.code").value("CP-001"));

        verify(getCopyUseCase).findById(1L);
        verify(copyRestMapper).toCopyResponse(copy);
    }

    @Test
    void should_get_all_copies() throws Exception {

        when(getCopyUseCase.findAll()).thenReturn(List.of(copy));
        when(copyRestMapper.toCopyResponseList(List.of(copy))).thenReturn(List.of(copyResponse));

        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].copyId").value(1))
                .andExpect(jsonPath("$[0].code").value("CP-001"));

        verify(getCopyUseCase).findAll();
        verify(copyRestMapper).toCopyResponseList(List.of(copy));
    }


}