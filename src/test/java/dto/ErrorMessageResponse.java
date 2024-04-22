package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data@AllArgsConstructor@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorMessageResponse {
    private String httpStatus;
    private String message;
    private String errors;
    private List<String> email;
}

