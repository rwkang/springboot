package shop.onekorea.springboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import shop.onekorea.springboot.entity.Post;
import shop.onekorea.springboot.service.PostService;

import java.util.List;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/list")
    public String getPostList(Model model) {
        List<Post> postList = postService.getPostListService();
        model.addAttribute("postList", postList);

        return "post/post_list";
    }

}
