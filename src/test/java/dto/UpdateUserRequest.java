package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.time.LocalDateTime;

@Data@AllArgsConstructor@NoArgsConstructor
public class UpdateUserRequest {
    private String avatarUrl;
    private String name;
    private String surname;
    private LocalDateTime birthDate;
    private String phone;
    private String gender;
    private String backgroundUrl;
    private String blocked;
}
