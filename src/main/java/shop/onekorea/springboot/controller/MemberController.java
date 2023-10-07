package shop.onekorea.springboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import shop.onekorea.springboot.dto.SignInRequestDto;
import shop.onekorea.springboot.dto.SignInResponseDto;
import shop.onekorea.springboot.entity.Member;
import shop.onekorea.springboot.service.MemberService;

import java.util.List;

import static shop.onekorea.springboot.SpringbootApplication.getInfo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class MemberController {

    /**
     * "final" 이게 없으면, 치명적 에러 발생한다.
     * ERROR 95736 --- [nio-8080-exec-2] o.a.c.c.C.[.[.[/].[dispatcherServlet] :
     * Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [request processing failed:
     * java.lang.NullPointerException: Cannot invoke "shop.onekorea.springboot.service.UserService.userListService()" because "this.userService" is null] with root cause
     */
//    private UserService userService;
    private final MemberService userService;

    @GetMapping("/login")
    public List<Member> login() {
        System.err.println(getInfo());

        return userService.userListService();
//        return "login.html";

    }

    @GetMapping("/list")
    public List<Member> userList() {
        System.err.println(getInfo());

        return userService.userListService();

    }

    @PostMapping("/signin")
//    @GetMapping("/signin")
//    public ResponseDto<SignInResponseDto> signIn(@RequestBody SignInRequestDto signInRequestDto) {
    public SignInResponseDto signIn(@RequestBody SignInRequestDto signInRequestDto) { // userId, password
        System.err.println(getInfo());

//        ResponseDto<SignInResponseDto> responseDto = userService.signInService(signInRequestDto);
        SignInResponseDto signInResponseDto = userService.signInService(signInRequestDto);

        System.err.println(getInfo());

//        return responseDto; // 2023.09.26 Conclusion. 일종의 "통합 DTO" 개념인 "ResponseDto"는 나중에 다시 검토하자.
        return signInResponseDto;

    }

//    public ResponseDto<SignInResponseDto> doSignIn(@RequestBody SignInRequestDto requestBody) {
//        System.out.println("=====> AuthController.doSignIn.requestBody: " + requestBody.toString());
//
//        ResponseDto<SignInResponseDto> result = authService.serviceSignIn(requestBody);
//        System.out.println("=====> AuthController.doSignIn.result: " + result.toString());
//
//        return result;
//    }


}
