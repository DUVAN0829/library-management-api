package co.duvan.loan.infrastructure.adapters.input.rest;

import co.duvan.loan.application.ports.input.CreateLoanUseCase;
import co.duvan.loan.application.ports.input.DeleteLoanUseCase;
import co.duvan.loan.application.ports.input.GetLoanUseCase;
import co.duvan.loan.application.ports.input.UpdateLoanUseCase;
import co.duvan.loan.infrastructure.adapters.input.rest.documentation.DefaultApiErrors;
import co.duvan.loan.infrastructure.adapters.input.rest.documentation.ValidationApiError;
import co.duvan.loan.infrastructure.adapters.input.rest.mapper.LoanRestMapper;
import co.duvan.loan.infrastructure.adapters.input.rest.model.response.LoanDetailResponse;
import co.duvan.loan.infrastructure.adapters.input.rest.model.request.LoanRequest;
import co.duvan.loan.infrastructure.adapters.input.rest.model.response.LoanResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@Tag(name = "Loans API", description = "Operations basic related loans (CRUD)")
public class LoanRestAdapter {

    private final LoanRestMapper loanRestMapper;
    private final CreateLoanUseCase createLoanUseCase;
    private final GetLoanUseCase getLoanUseCase;
    private final DeleteLoanUseCase deleteLoanUseCase;
    private final UpdateLoanUseCase updateLoanUseCase;

    @Operation(summary = "Get all loans")
    @ApiResponse(responseCode = "200", description = "List all loans")
    @DefaultApiErrors
    @GetMapping("/api/v1")
    public ResponseEntity<List<LoanResponse>> findAll() {

        return ResponseEntity.ok(loanRestMapper.toLoanResponseList(getLoanUseCase.findAll()));

    }

    @Operation(summary = "Get loan")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loan found"),
            @ApiResponse(responseCode = "404", description = "Loan not found")
    })
    @DefaultApiErrors
    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN') or @loanSecurity.isOwner(authentication, #id)")
    @GetMapping("/api/v1/{id}")
    public ResponseEntity<LoanDetailResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(loanRestMapper.toLoanDetailResponse(getLoanUseCase.findById(id)));
    }

    @Operation(summary = "Create loan")
    @ApiResponse(responseCode = "201", description = "Loan created")
    @DefaultApiErrors
    @ValidationApiError
    @PostMapping("/api/v1")
    public ResponseEntity<LoanResponse> save(@Valid @RequestBody LoanRequest loanRequest) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(loanRestMapper.toLoanResponse(createLoanUseCase.save(loanRestMapper.toLoan(loanRequest))));

    }

    @Operation(summary = "Update loan")
    @ApiResponse(responseCode = "200", description = "Loan updated")
    @DefaultApiErrors
    @ValidationApiError
    @PutMapping("/api/v1/{id}")
    public ResponseEntity<LoanResponse> update(@Valid @PathVariable Long id, @RequestBody LoanRequest loanRequest) {

        return ResponseEntity.ok(loanRestMapper.toLoanResponse(updateLoanUseCase.update(id, loanRestMapper.toLoan(loanRequest))));

    }

    @Operation(summary = "Delete loan")
    @ApiResponse(responseCode = "204", description = "Loan deleted")
    @DefaultApiErrors
    @DeleteMapping("/api/v1/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {

        deleteLoanUseCase.deleteById(id);

        return ResponseEntity.noContent().build();

    }

}