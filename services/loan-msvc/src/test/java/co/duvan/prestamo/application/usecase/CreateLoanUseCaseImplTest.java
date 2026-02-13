package co.duvan.prestamo.application.usecase;

import co.duvan.prestamo.application.ports.output.LoanRepositoryPort;
import co.duvan.prestamo.domain.model.Loan;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateLoanUseCaseImplTest {

    @Mock
    private LoanRepositoryPort repositoryPort;

    @InjectMocks
    private CreateLoanUseCaseImpl createLoanUseCase;

    @Test
    void should_create_loan_successfully() {

        //* Arrange
        Loan loan = new Loan();

        Loan existingLoan = new Loan();
        existingLoan.setLoanId(1L);

        when(repositoryPort.save(loan)).thenReturn(existingLoan);

        //* Act
        Loan result = createLoanUseCase.save(loan);

        //* Assert
        assertNotNull(result);
        assertNotNull(result.getLoanId());

        verify(repositoryPort).save(loan);
    }
}
