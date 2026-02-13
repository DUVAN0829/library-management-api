package co.duvan.prestamo.infrastructure.adapters.input.rest.mapper;

import co.duvan.prestamo.domain.model.Loan;
import co.duvan.prestamo.infrastructure.adapters.input.rest.model.request.LoanRequest;
import co.duvan.prestamo.infrastructure.adapters.input.rest.model.response.LoanResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoanRestMapper {

    Loan toLoan(LoanRequest loanRequest);

    LoanResponse toLoanResponse(Loan loan);

    List<LoanResponse> toLoanResponseList(List<Loan> loans);

}
