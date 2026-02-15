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
import org.springframework.http.MediaType;
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

    @Test
    void should_create_copy() throws Exception {

        when(copyRestMapper.toCopy(any(CopyRequest.class))).thenReturn(copy);
        when(createCopyUseCase.save(any(Copy.class))).thenReturn(copy);
        when(copyRestMapper.toCopyResponse(any(Copy.class))).thenReturn(copyResponse);

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(copyRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.copyId").value(1));

        verify(createCopyUseCase).save(any(Copy.class));
        verify(copyRestMapper).toCopy(any(CopyRequest.class));
        verify(copyRestMapper).toCopyResponse(any(Copy.class));
    }

    @Test
    void should_update_copy() throws Exception {

        when(copyRestMapper.toCopy(any(CopyRequest.class))).thenReturn(copy);
        when(updateCopyUseCase.update(anyLong(), any(Copy.class))).thenReturn(copy);
        when(copyRestMapper.toCopyResponse(any(Copy.class))).thenReturn(copyResponse);

        mockMvc.perform(put(BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(copyRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.copyId").value(1));

        verify(updateCopyUseCase).update(eq(1L), any(Copy.class));
        verify(copyRestMapper).toCopy(any(CopyRequest.class));
        verify(copyRestMapper).toCopyResponse(any(Copy.class));
    }

    @Test
    void should_delete_copy() throws Exception {

        doNothing().when(deleteCopyUseCase).deleteById(1L);

        mockMvc.perform(delete(BASE_URL + "/1"))
                .andExpect(status().isNoContent());

        verify(deleteCopyUseCase).deleteById(1L);
    }

}