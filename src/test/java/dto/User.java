package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String role;
    private String gender;
    private String birthDate;
    private String avatarUrl;
    private String backgroundUrl;
}

