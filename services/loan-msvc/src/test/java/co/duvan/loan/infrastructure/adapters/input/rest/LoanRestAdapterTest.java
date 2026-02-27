package co.duvan.loan.infrastructure.adapters.input.rest;

import co.duvan.loan.application.ports.input.CreateLoanUseCase;
import co.duvan.loan.application.ports.input.DeleteLoanUseCase;
import co.duvan.loan.application.ports.input.GetLoanUseCase;
import co.duvan.loan.application.ports.input.UpdateLoanUseCase;
import co.duvan.loan.application.ports.output.dto.CopyClientResponse;
import co.duvan.loan.application.ports.output.dto.LoanDetailResult;
import co.duvan.loan.application.ports.output.dto.UserClientResponse;
import co.duvan.loan.domain.enums.Status;
import co.duvan.loan.domain.model.Loan;
import co.duvan.loan.infrastructure.adapters.input.rest.mapper.LoanRestMapper;
import co.duvan.loan.infrastructure.adapters.input.rest.model.response.LoanDetailResponse;
import co.duvan.loan.infrastructure.adapters.input.rest.model.request.LoanRequest;
import co.duvan.loan.infrastructure.adapters.input.rest.model.response.LoanResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.security.autoconfigure.SecurityAutoConfiguration;
import org.springframework.boot.security.autoconfigure.web.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.security.oauth2.server.resource.autoconfigure.servlet.OAuth2ResourceServerAutoConfiguration;
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

@WebMvcTest(
        controllers = LoanRestAdapter.class,
        excludeAutoConfiguration = {
                SecurityAutoConfiguration.class,
                SecurityFilterAutoConfiguration.class,
                OAuth2ResourceServerAutoConfiguration.class
        }
)
class LoanRestAdapterTest {

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

    private Loan loan;
    private LoanResponse loanResponse;
    private LoanRequest loanRequest;

    @BeforeEach
    void setup() {

        loan = new Loan(
                1L,
                10L,
                20L,
                LocalDate.now(),
                LocalDate.now().plusDays(7),
                null,
                Status.ACTIVE
        );

        loanResponse = new LoanResponse(
                1L,
                10L,
                20L,
                LocalDate.now(),
                LocalDate.now().plusDays(7),
                null,
                Status.ACTIVE
        );

        loanRequest = new LoanRequest(
                10L,
                20L,
                LocalDate.now(),
                LocalDate.now().plusDays(7),
                null,
                Status.RETURNED
        );
    }

    @Test
    void should_get_loan_by_id() throws Exception {

        UserClientResponse clientResponse = new UserClientResponse();
        clientResponse.setUserId(1L);

        CopyClientResponse copyResponse = new CopyClientResponse();

        LoanDetailResult result = new LoanDetailResult(loan, clientResponse, copyResponse);

        LoanDetailResponse loanDetailResponse = new LoanDetailResponse(
                1L,
                LocalDate.now(),
                LocalDate.now().plusDays(7),
                null,
                Status.ACTIVE,
                clientResponse,
                copyResponse
        );

        //* Given
        when(getLoanUseCase.findById(1L)).thenReturn(result);
        when(loanRestMapper.toLoanDetailResponse(result)).thenReturn(loanDetailResponse);

        //* When
        mockMvc.perform(get(BASE_URL + "/1"))

                //* Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.loanId").value(1))
                .andExpect(jsonPath("$.user.userId").value(1));

        verify(getLoanUseCase).findById(1L);
        verify(loanRestMapper).toLoanDetailResponse(result);
    }

    @Test
    void should_get_all_loans() throws Exception {

        //* Given
        when(getLoanUseCase.findAll()).thenReturn(List.of(loan));
        when(loanRestMapper.toLoanResponseList(List.of(loan))).thenReturn(List.of(loanResponse));

        //* When
        mockMvc.perform(get(BASE_URL))

                //* Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].loanId").value(1));

        verify(getLoanUseCase).findAll();
        verify(loanRestMapper).toLoanResponseList(List.of(loan));
    }

    @Test
    void should_create_loan() throws Exception {

        //* Given
        when(loanRestMapper.toLoan(any(LoanRequest.class))).thenReturn(loan);
        when(createLoanUseCase.save(any(Loan.class))).thenReturn(loan);
        when(loanRestMapper.toLoanResponse(any(Loan.class))).thenReturn(loanResponse);

        //* When
        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanRequest)))

                //* Then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.loanId").value(1));

        verify(createLoanUseCase).save(any(Loan.class));
        verify(loanRestMapper).toLoan(any(LoanRequest.class));
        verify(loanRestMapper).toLoanResponse(any(Loan.class));
    }

    @Test
    void should_update_loan() throws Exception {

        //* Given
        when(loanRestMapper.toLoan(any(LoanRequest.class))).thenReturn(loan);
        when(updateLoanUseCase.update(eq(1L), any(Loan.class))).thenReturn(loan);
        when(loanRestMapper.toLoanResponse(any(Loan.class))).thenReturn(loanResponse);

        //* When
        mockMvc.perform(put(BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanRequest)))

                //* Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.loanId").value(1));

        verify(updateLoanUseCase).update(eq(1L), any(Loan.class));
        verify(loanRestMapper).toLoan(any(LoanRequest.class));
        verify(loanRestMapper).toLoanResponse(any(Loan.class));
    }

    @Test
    void should_delete_loan() throws Exception {

        //* Given
        doNothing().when(deleteLoanUseCase).deleteById(1L);

        //* When
        mockMvc.perform(delete(BASE_URL + "/1"))

                //* Then
                .andExpect(status().isNoContent());

        verify(deleteLoanUseCase).deleteById(1L);
    }

}