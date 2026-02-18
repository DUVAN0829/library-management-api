package co.duvan.user.infrastructure.adapters.input.rest.documentation;

import co.duvan.user.infrastructure.adapters.input.rest.model.error.ErrorResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ApiResponses(value = {

        @ApiResponse(
                responseCode = "404",
                description = "Resource not found",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ErrorResponse.class),
                        examples = @ExampleObject(
                                name = "Not Found",
                                value = """
                                        {
                                          "code": "ERR_USER_01",
                                          "message": "User not found",
                                          "details": [],
                                          "timestamp": "2026-02-17T07:35:45"
                                        }
                                        """
                        )
                )
        ),

        @ApiResponse(
                responseCode = "500",
                description = "Internal server error",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ErrorResponse.class),
                        examples = @ExampleObject(
                                name = "Server Error",
                                value = """
                                        {
                                          "code": "GEN_ERR_01",
                                          "message": "Unexpected internal error",
                                          "details": ["Something went wrong"],
                                          "timestamp": "2026-02-17T14:15:30"
                                        }
                                        """
                        )
                )
        )
})
public @interface DefaultApiErrors {
}