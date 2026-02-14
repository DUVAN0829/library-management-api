package co.duvan.loan.infrastructure.adapters.input.rest;

import co.duvan.loan.application.ports.input.CreateLoanUseCase;
import co.duvan.loan.application.ports.input.DeleteLoanUseCase;
import co.duvan.loan.application.ports.input.GetLoanUseCase;
import co.duvan.loan.application.ports.input.UpdateLoanUseCase;
import co.duvan.loan.domain.exceptions.LoanNotFoundException;
import co.duvan.loan.infrastructure.adapters.input.rest.mapper.LoanRestMapper;
import co.duvan.loan.infrastructure.adapters.input.rest.model.request.LoanRequest;
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

@WebMvcTest(LoanRestAdapter.class)
@Import(GlobalControllerAdvice.class)
class GlobalControllerAdviceTest {

    private static final String BASE_URL = "/loans/api/v1";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LoanRestMapper loanRestMapper;

    @MockitoBean
    private CreateLoanUseCase createLoanUseCase;

    @MockitoBean
    private GetLoanUseCase getLoanUseCase;

    @MockitoBean
    private DeleteLoanUseCase deleteLoanUseCase;

    @MockitoBean
    private UpdateLoanUseCase updateLoanUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_return_404_when_loan_not_found() throws Exception {

        //* Given
        when(getLoanUseCase.findById(99L))
                .thenThrow(new LoanNotFoundException());

        //* When
        mockMvc.perform(get(BASE_URL + "/99"))

                //* Then
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("ERR_LOAN_01"))
                .andExpect(jsonPath("$.message").value("Loan not found"));
    }

    @Test
    void should_return_400_when_loan_request_is_invalid() throws Exception {

        //* Given
        LoanRequest invalidRequest = new LoanRequest();
        invalidRequest.setUserId(null);

        //* When
        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))

                //* Then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("ERR_LOAN_02"))
                .andExpect(jsonPath("$.message").value("Invalid loan parameters"))
                .andExpect(jsonPath("$.details").isArray());
    }

    @Test
    void should_return_500_when_generic_exception_occurs() throws Exception {

        //* Given
        when(getLoanUseCase.findById(1L))
                .thenThrow(new RuntimeException("Something went wrong"));

        //* When
        mockMvc.perform(get(BASE_URL + "/1"))

                //* Then
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.code").value("GEN_ERR_01"))
                .andExpect(jsonPath("$.message").value("An unexpected error occurred"))
                .andExpect(jsonPath("$.details[0]").value("Something went wrong"));
    }

}