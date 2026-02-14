package co.duvan.loan.infrastructure.adapters.output.persistence.mapper;

import co.duvan.loan.domain.model.Loan;
import co.duvan.loan.infrastructure.adapters.output.persistence.entity.LoanEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LoanPersitenceMapper {

    LoanEntity toLoanEntity(Loan loan);

    Loan toLoan(LoanEntity loanEntity);

    List<Loan> toListLoan(List<LoanEntity> loanEntityList);

}
