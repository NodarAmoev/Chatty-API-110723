package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class PutUpdatePasswordRequest {
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;
}
