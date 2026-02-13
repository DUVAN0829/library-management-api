package co.duvan.prestamo.application.usecase;

import co.duvan.prestamo.application.ports.output.LoanRepositoryPort;
import co.duvan.prestamo.domain.exceptions.LoanNotFoundException;
import co.duvan.prestamo.domain.model.Loan;
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

    @InjectMocks
    private GetLoanUseCaseImpl getLoanUseCase;

    @Test
    void should_return_loan_when_exists() {

        //* Arrange
        Loan loan = new Loan();
        loan.setLoanId(1L);

        when(repositoryPort.findById(1L)).thenReturn(Optional.of(loan));

        //* Act
        Loan result = getLoanUseCase.findById(1L);

        //* Assert
        assertNotNull(result);
        assertNotNull(result.getLoanId());

        verify(repositoryPort).findById(1L);
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
