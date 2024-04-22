package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class CreateUserPostResponse {
    private String id;
    private String title;
    private String description;
    private String body;
    private String imageUrl;
    private String publishDate;
    private String updatedAt;
    private String draft;
    private String userId;

}
