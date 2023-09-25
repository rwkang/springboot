package shop.onekorea.springboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import shop.onekorea.springboot.entity.Post;
import shop.onekorea.springboot.service.PostService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    /**
     * 2023.09.25 Conclusion. 아래 "생성자 의존 주입 방식" 코드를,
     * 상단에 "@RequiredArgsConstructor" 어노테이션으로 처리할 수 있다.
     * 단, 필드 변수가 반드시 "final"로 지정되어야 한다.
     @Autowired
     public MainController(PostService postService) {
         this.postService = postService;
     }
     */

//    @GetMapping("/")
    @GetMapping("") // "/" 슬러시가 붙어 있는 것과 없는 것은 완전히 다른 url 이다.
    public String post() {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[1];
        System.err.println(stackTraceElement.getClassName() + " : " + stackTraceElement.getLineNumber());

        return "index"; // index.html 파일이 안 불러와 지고, 그냥 String "index"만 찍어주네.
    }

    @GetMapping("/list")
    public List<Post> postList() {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[1];
        System.err.println(stackTraceElement.getClassName() + " : " + stackTraceElement.getLineNumber());

        return postService.postList();

    }

    @GetMapping("/{postId}")
    public Post postDetail(@PathVariable String postId) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[1];
        System.err.println(stackTraceElement.getClassName() + " : " + stackTraceElement.getLineNumber());

//        return new Post("1", "title 1", "컨텐츠 1", "rwkang", LocalDateTime.now());
        return new Post(UUID.randomUUID(), "title 1", "컨텐츠 1", "rwkang", LocalDateTime.now());
    }

    @GetMapping("/update")
    public String updatePost(@RequestParam String postId) { // url 뒤에 "?" 다음 값이 "@RequestParam"으로 받는다.
        return postId + " 블로그 수정 페이지입니다.";
    }

}
