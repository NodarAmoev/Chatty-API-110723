package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class UpdatePostResponse {
    private String title;
    private String description;
    private String body;
    private String imageUrl;
    private String draft;
}
