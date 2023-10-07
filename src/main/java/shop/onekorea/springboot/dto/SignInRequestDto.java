package shop.onekorea.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignInRequestDto {
    @NotBlank
    private String userid;
    @NotBlank
    private String password;

}
