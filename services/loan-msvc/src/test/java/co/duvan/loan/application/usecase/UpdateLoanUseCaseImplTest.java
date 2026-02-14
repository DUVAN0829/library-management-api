package co.duvan.loan.application.usecase;

import co.duvan.loan.application.ports.output.LoanRepositoryPort;
import co.duvan.loan.domain.enums.Status;
import co.duvan.loan.domain.model.Loan;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UpdateLoanUseCaseImplTest {

    @Mock
    private LoanRepositoryPort repositoryPort;

    @InjectMocks
    private UpdateLoanUseCaseImpl updateLoanUseCase;

    @Test
    void should_update_loan_successfully() {

        //* Arrange
        Long loanId = 1L;

        Loan existingLoan = new Loan();
        existingLoan.setLoanId(loanId);
        existingLoan.setUserId(1L);

        Loan loanToUpdate = new Loan();
        loanToUpdate.setUserId(2L);
        loanToUpdate.setLoanStatus(Status.RETURNED);
        loanToUpdate.setLoanDate(LocalDate.now());

        Loan savedLoan = new Loan();
        savedLoan.setLoanId(loanId);
        savedLoan.setUserId(2L);
        savedLoan.setLoanStatus(Status.RETURNED);
        savedLoan.setLoanDate(loanToUpdate.getLoanDate());

        when(repositoryPort.findById(loanId))
                .thenReturn(Optional.of(existingLoan));

        when(repositoryPort.save(any(Loan.class)))
                .thenReturn(savedLoan);

        //* Act
        Loan result = updateLoanUseCase.update(loanId, loanToUpdate);

        //* Assert
        assertEquals(Status.RETURNED, result.getLoanStatus());

        verify(repositoryPort).findById(loanId);
        verify(repositoryPort).save(existingLoan);
    }
}
