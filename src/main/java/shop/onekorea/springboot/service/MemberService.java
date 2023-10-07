package shop.onekorea.springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.onekorea.springboot.dto.SignInRequestDto;
import shop.onekorea.springboot.dto.SignInResponseDto;
import shop.onekorea.springboot.entity.Member;
import shop.onekorea.springboot.repository.MemberRepository;

import java.util.List;

import static shop.onekorea.springboot.SpringbootApplication.getInfo;

@Service
@RequiredArgsConstructor
public class MemberService {

    /**
     * "final" 이게 없으면, 치명적 에러 발생한다.
     * ERROR 95736 --- [nio-8080-exec-2] o.a.c.c.C.[.[.[/].[dispatcherServlet] :
     * Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [request processing failed:
     * java.lang.NullPointerException: Cannot invoke "shop.onekorea.springboot.service.UserService.userListService()" because "this.userService" is null] with root cause
     */
    private final MemberRepository userRepository;

    public SignInResponseDto signInService(SignInRequestDto signInRequestDto) {
        String userId = signInRequestDto.getUserid();
        String password = signInRequestDto.getPassword();

        Member users = userRepository.findByUserIdAndPassword(userId, password);


        // password 값 없애기
        users.setPassword("");

        String token = "token";
        int expiration = 60 * 60 * 1000; // 1시간

        // SignInResponseDto 만들기
        SignInResponseDto signInResponseDto = new SignInResponseDto(token, expiration, users);

        System.err.println("getInfo: " + getInfo() + ", signInResponseDto : " + signInResponseDto);

        return signInResponseDto;
    }

    public List<Member> userListService() {
        System.err.println(getInfo());

        return userRepository.findAll();
    }

}
