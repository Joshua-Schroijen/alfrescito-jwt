package be.joshuaschroijen.alfrescito.dto;

import java.util.List;
import java.util.Arrays;

import org.springframework.http.HttpStatusCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiErrorResponse {
    private HttpStatusCode status;
    private String message;
    private List<String> errors;

    public ApiErrorResponse(HttpStatusCode status, String message, String error){
        this.status = status;
        this.message = message;
        this.errors = Arrays.asList(error);
    }
}