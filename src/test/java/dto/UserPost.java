package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPost {
    private String title;
    private String description;
    private String body;
    private String imageUrl;
    private String createdAt;
    private String updatedAt;
    private String draft;
    private String id;
    private User user; // Change from String to User
}
