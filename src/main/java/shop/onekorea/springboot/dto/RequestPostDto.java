package shop.onekorea.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestPostDto {
    @NotBlank
    private String postId;
    @NotBlank
    private String title;
    @NotBlank
    private String contents;
    @NotBlank
    private String author;
    private LocalDateTime createdAt;

}
