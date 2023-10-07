/**
 * Controller 단에서 Service 단을 불러 올 때,
 * Service 단 내부에서, 아래 2가지 방식 중에서, 어떤 것으로 처리가 되어, 보내 져도, 전혀 변화가 없다. Layered Architecture의 장점...
 * 1. to JPA 방식으로 "return postRepository.findAll();
 * 2. to mybatis 방식으로 "return postMapper.listPost();
 */

package shop.onekorea.springboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import shop.onekorea.springboot.entity.Post;
import shop.onekorea.springboot.service.PostService;

import java.util.List;

import static shop.onekorea.springboot.SpringbootApplication.getInfo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostRestController {

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
        System.err.println(getInfo());

        // resources/templates/index.html 파일이 안 불러와 지고, 그냥 String "index"만 찍어주네.
        // resources/static/index.html 이것을 바로 불러온다.
        return "index";
    }

    @GetMapping("/list")
    public List<Post> postList() {
        System.err.println(getInfo());

        return postService.getPostListService();

    }

//    @GetMapping("/postid")
//    public Post getPostById(RequestPostDto requestPostDto, Model model) {
//        String postId = requestPostDto.getPostId();
//
//        Post post = postService.getPostByIdService(postId);
//
//        model.addAttribute("post", post);
//
//        return post;
////        return "/study/studied";
//
//
//    }

//    @GetMapping("/{postId}")
//    public Post postDetail(@PathVariable UUID postId) {
//        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[1];
//        System.err.println(stackTraceElement.getClassName() + " : " + stackTraceElement.getLineNumber());
//
////        Post post = postService.getPost(String.valueOf(postId));
////
////        return post;
//
//        // return new Post("1", "title 1", "컨텐츠 1", "rwkang", LocalDateTime.now());
//        return new Post(UUID.randomUUID(), "title 1", "컨텐츠 1", "rwkang", LocalDateTime.now());
//    }

    @GetMapping("/update")
    public String updatePost(@RequestParam String postId) { // url 뒤에 "?" 다음 값이 "@RequestParam"으로 받는다.
        return postId + " 블로그 수정 페이지입니다.";
    }




}
