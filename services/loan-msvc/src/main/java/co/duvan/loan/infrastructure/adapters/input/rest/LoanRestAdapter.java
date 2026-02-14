package co.duvan.loan.infrastructure.adapters.input.rest;

import co.duvan.loan.application.ports.input.CreateLoanUseCase;
import co.duvan.loan.application.ports.input.DeleteLoanUseCase;
import co.duvan.loan.application.ports.input.GetLoanUseCase;
import co.duvan.loan.application.ports.input.UpdateLoanUseCase;
import co.duvan.loan.infrastructure.adapters.input.rest.mapper.LoanRestMapper;
import co.duvan.loan.infrastructure.adapters.input.rest.model.request.LoanRequest;
import co.duvan.loan.infrastructure.adapters.input.rest.model.response.LoanResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
public class LoanRestAdapter {

    private final LoanRestMapper loanRestMapper;
    private final CreateLoanUseCase createLoanUseCase;
    private final GetLoanUseCase getLoanUseCase;
    private final DeleteLoanUseCase deleteLoanUseCase;
    private final UpdateLoanUseCase updateLoanUseCase;

    @GetMapping("/api/v1")
    public ResponseEntity<List<LoanResponse>> findAll() {

        return ResponseEntity.ok(loanRestMapper.toLoanResponseList(getLoanUseCase.findAll()));

    }

    @GetMapping("/api/v1/{id}")
    public ResponseEntity<LoanResponse> findById(@PathVariable Long id) {

        return ResponseEntity.ok(loanRestMapper.toLoanResponse(getLoanUseCase.findById(id)));

    }

    @PostMapping("/api/v1")
    public ResponseEntity<LoanResponse> save(@Valid @RequestBody LoanRequest loanRequest) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(loanRestMapper.toLoanResponse(createLoanUseCase.save(loanRestMapper.toLoan(loanRequest))));

    }

    @PutMapping("/api/v1/{id}")
    public ResponseEntity<LoanResponse> update(@Valid @PathVariable Long id, @RequestBody LoanRequest loanRequest) {

        return ResponseEntity.ok(loanRestMapper.toLoanResponse(updateLoanUseCase.update(id, loanRestMapper.toLoan(loanRequest))));

    }

    @DeleteMapping("/api/v1/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {

        deleteLoanUseCase.deleteById(id);

        return ResponseEntity.noContent().build();

    }

}