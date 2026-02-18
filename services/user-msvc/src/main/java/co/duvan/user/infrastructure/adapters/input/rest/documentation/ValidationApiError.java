package co.duvan.user.infrastructure.adapters.input.rest.documentation;

import co.duvan.user.infrastructure.adapters.input.rest.model.error.ErrorResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ApiResponse(
        responseCode = "400",
        description = "Invalid request",
        content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class),
                examples = @ExampleObject(
                        name = "Bad Request",
                        value = """
                                {
                                  "code": "ERR_USER_02",
                                  "message": "Invalid user parameters",
                                  "details": ["Field phoneNumber cannot be blank"],
                                  "timestamp": "2026-02-17T13:30:14"
                                }
                                """
                )
        )
)
public @interface ValidationApiError {
}