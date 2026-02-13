package co.duvan.prestamo.application.usecase;

import co.duvan.prestamo.application.ports.output.LoanRepositoryPort;
import co.duvan.prestamo.domain.exceptions.LoanNotFoundException;
import co.duvan.prestamo.domain.model.Loan;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteLoanUseCaseImplTest {

    @Mock
    private LoanRepositoryPort repositoryPort;

    @InjectMocks
    private DeleteLoanUseCaseImpl deleteLoanUseCase;

    @Test
    void should_delete_loan_successfully() {

        //* Arrange
        Loan loan = new Loan();
        loan.setLoanId(1L);

        when(repositoryPort.findById(1L)).thenReturn(Optional.of(loan));

        //* Act
        deleteLoanUseCase.deleteById(1L);

        //* Assert
        verify(repositoryPort).deleteById(1L);
    }

    @Test
    void should_throw_exception_when_loan_not_found() {

        //* Arrange
        when(repositoryPort.findById(1L)).thenReturn(Optional.empty());

        //* Act & Assert
        assertThrows(LoanNotFoundException.class,
                () -> deleteLoanUseCase.deleteById(1L));

        verify(repositoryPort, never()).deleteById(1L);
    }
}
