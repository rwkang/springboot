package shop.onekorea.springboot.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shop.onekorea.springboot.entity.Post;
import shop.onekorea.springboot.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 2023.09.27 Conclusion. 테스트에서는 @RequiredArgsConstructor => final PostRepository postRepository; 이거 사용 불가
 * @RequiredArgsConstructor
 *
 * "SQL" 구분 사용하기
 * public long getAccountsByJPQL() throws ParseException {
 *     Query query = entityManager.createQuery("SELECT COUNT(*) FROM Account a");
 *     return (long) query.getSingleResult();
 * }
 */

@SpringBootTest
class PostServiceTest {

    @Autowired PostService postService;

    StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[1];

    @Test
    void getTopByCreatedAtOrderByCreatedAtService() throws Exception {
        Post post = postService.getReferenceByIdService();
        System.err.println(stackTraceElement.getClassName() + " : " + stackTraceElement.getLineNumber());

        System.out.println(post.getPostId());
        System.out.println(post.getTitle());
        System.out.println(post.getContents());
        System.out.println(post.getAuthor());
        System.out.println(post.getCreatedAt());
        System.out.println("\n");

    }

    @Test
    void insertPostService() throws Exception {
        Post post = Post.builder()
                .postId("UUID.randomUUID().toString()")
                .title("타이틀 추가")
                .contents("컨텐츠 추가")
                .createdAt(LocalDateTime.now())
                .build();

        Post postInserted = postService.insertPostService(post);

//        for (Post post : postList) {
        System.out.println(post.getPostId());
        System.out.println(post.getTitle());
        System.out.println(post.getContents());
        System.out.println(post.getAuthor());
        System.out.println(post.getCreatedAt());
        System.out.println("\n");
//        }
    }

//    @Test
//    void UpdateService() throws Exception {
//        System.err.println(stackTraceElement.getClassName() + " : " + stackTraceElement.getLineNumber());
//
//        Post post = postService.getTopByCreatedAtOrderByCreatedAtService();
//
//
//        Post postUpdated = postService.updateService(post);
//
//        System.out.println(post.getPostId());
//        System.out.println(post.getTitle());
//        System.out.println(post.getContents());
//        System.out.println(post.getAuthor());
//        System.out.println(post.getCreatedAt());
//        System.out.println("\n");
//
//    }

    @Test
    void getPostListService() throws Exception {

        List<Post> postList = postService.getPostListService();

        for (Post post : postList) {
            System.out.println(post.getPostId());
            System.out.println(post.getTitle());
            System.out.println(post.getContents());
            System.out.println(post.getAuthor());
            System.out.println(post.getCreatedAt());
            System.out.println("\n");
        }
    }
}