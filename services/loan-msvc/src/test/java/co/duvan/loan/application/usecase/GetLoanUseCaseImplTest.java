package co.duvan.loan.application.usecase;

import co.duvan.loan.application.ports.output.CopyClientPort;
import co.duvan.loan.application.ports.output.LoanRepositoryPort;
import co.duvan.loan.application.ports.output.UserClientPort;
import co.duvan.loan.application.ports.output.dto.CopyClientResponse;
import co.duvan.loan.application.ports.output.dto.LoanDetailResult;
import co.duvan.loan.application.ports.output.dto.UserClientResponse;
import co.duvan.loan.domain.exceptions.LoanNotFoundException;
import co.duvan.loan.domain.model.Loan;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GetLoanUseCaseImplTest {

    @Mock
    private LoanRepositoryPort repositoryPort;

    @Mock
    private UserClientPort userClientPort;

    @Mock
    private CopyClientPort copyClientPort;

    @InjectMocks
    private GetLoanUseCaseImpl getLoanUseCase;

    @Test
    void should_return_loan_detail_when_exists() {

        //* Arrange
        Loan loan = new Loan();
        loan.setLoanId(1L);
        loan.setUserId(10L);
        loan.setCopyId(20L);

        UserClientResponse user = new UserClientResponse();
        CopyClientResponse copy = new CopyClientResponse();

        when(repositoryPort.findById(1L)).thenReturn(Optional.of(loan));
        when(userClientPort.findUserById(10L, null)).thenReturn(user);
        when(copyClientPort.findCopyById(20L, null)).thenReturn(copy);

        //* Act
        LoanDetailResult result = getLoanUseCase.findById(1L);

        //* Assert
        assertNotNull(result);
        assertNotNull(result.getLoan().getLoanId());
        assertEquals(user, result.getUser());
        assertEquals(copy, result.getCopy());

        verify(repositoryPort).findById(1L);
        verify(userClientPort).findUserById(10L, null);
        verify(copyClientPort).findCopyById(20L, null);
    }

    @Test
    void should_throw_exception_when_loan_not_found() {

        //* Arrange
        when(repositoryPort.findById(1L)).thenReturn(Optional.empty());

        //* Act
        assertThrows(LoanNotFoundException.class,
                () -> getLoanUseCase.findById(1L));

        //* Assert
        verify(repositoryPort).findById(1L);
    }

    @Test
    void should_return_all_loans_successfully() {

        //* Arrange
        Loan loanA = new Loan();
        loanA.setLoanId(1L);

        Loan loanB = new Loan();
        loanB.setLoanId(2L);

        when(repositoryPort.findAll()).thenReturn(List.of(loanA, loanB));

        //* Act
        List<Loan> result = getLoanUseCase.findAll();

        //* Assert
        assertNotNull(result);
        assertEquals(2, result.size());

        verify(repositoryPort).findAll();
    }
}