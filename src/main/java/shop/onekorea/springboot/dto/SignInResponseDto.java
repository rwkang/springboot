package shop.onekorea.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import shop.onekorea.springboot.entity.Member;

@Data
@AllArgsConstructor
public class SignInResponseDto {
    private String token;
    private int expiration;
    private Member user;

//    public SignInResponseDto(String token, int expiration, User user) {
//    }

}
