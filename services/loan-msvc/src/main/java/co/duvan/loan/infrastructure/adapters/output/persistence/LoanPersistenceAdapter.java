package co.duvan.loan.infrastructure.adapters.output.persistence;

import co.duvan.loan.application.ports.output.LoanRepositoryPort;
import co.duvan.loan.domain.model.Loan;
import co.duvan.loan.infrastructure.adapters.output.persistence.entity.LoanEntity;
import co.duvan.loan.infrastructure.adapters.output.persistence.mapper.LoanPersitenceMapper;
import co.duvan.loan.infrastructure.adapters.output.persistence.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LoanPersistenceAdapter implements LoanRepositoryPort {

    private final LoanRepository repository;
    private final LoanPersitenceMapper loanPersitenceMapper;

    @Override
    public Optional<Loan> findById(Long id) {
        return repository.findById(id).map(loanPersitenceMapper::toLoan);
    }

    @Override
    public Loan save(Loan loan) {
        return loanPersitenceMapper.toLoan(repository.save(loanPersitenceMapper.toLoanEntity(loan)));
    }

    @Override
    public List<Loan> findAll() {
        return loanPersitenceMapper.toListLoan((List<LoanEntity>) repository.findAll());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}