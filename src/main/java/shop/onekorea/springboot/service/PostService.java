package shop.onekorea.springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.onekorea.springboot.entity.Post;
import shop.onekorea.springboot.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> postList() {
        return postRepository.findAll();

        /**
         * A. 서버를 실행하고,
         *     1. "http://localhost:8080/posts/list"로 호출하거나,
         *     2. Controller.postList() 메소드에서 "Open in HTTP Client"를 실행하여 보면,
         *        Response.Data 값이, 결국 아래와 같이 나와야 된다.
         *        이것을 위해, 여기 "Service.postList()" 메소드에서 생성해서, Controller로 보내는 것이다.
         * B. 그런데, 여기 "Service.postList()" 메소드는 원래, PostRepository를 통해서,
         *    DB.post에 있는 자료를 가져와야 하는 것이기 때문에,
         *    여기서 생성하는 것을,
         *    프로그램 실행과 동시에, "Application.main()" 메소드에서 생성하여, DB.port 테이블에 저장하게 하고,
         *    여기서는 그 자료를 위와 같이 불러서(FindAll()), Controller.postList() 메소드로 보내주게 한다.
         *
         * [
         *   {
         *     "postId": "9b211a37-e1f1-4cf8-9200-e189f562dbdb",
         *     "title": "타이틀 1",
         *     "contents": "컨텐츠 1",
         *     "author": "rwkang",
         *     "createdAt": "2023-09-25T10:43:30.853239"
         *   },
         *   {
         *     "postId": "9262d3eb-6d59-48f1-92fa-71bdc7447cfe",
         *     "title": "타이틀 2",
         *     "contents": "컨텐츠 2",
         *     "author": "rwkang",
         *     "createdAt": "2023-09-25T10:43:30.853239"
         *   },
         *   {
         *     "postId": "aa579306-39e4-4990-80ce-856583505151",
         *     "title": "타이틀 3",
         *     "contents": "컨텐츠 3",
         *     "author": "rwkang",
         *     "createdAt": "2023-09-25T10:43:30.853239"
         *   }
         * ]
         */

//        return List.of( // "Service"에서 데이터를 생성해서 Controller로 보낸다.
//                new Post(UUID.randomUUID(), "타이틀 1", "컨텐츠 1", "rwkang", LocalDateTime.now()),
//                new Post(UUID.randomUUID(), "타이틀 2", "컨텐츠 2", "rwkang", LocalDateTime.now()),
//                new Post(UUID.randomUUID(), "타이틀 3", "컨텐츠 3", "rwkang", LocalDateTime.now())
//                );

//        return List.of(
//                new Post("1", "타이틀 1", "컨텐츠 1", "rwkang", LocalDateTime.now()),
//                new Post("2", "타이틀 2", "컨텐츠 2", "rwkang", LocalDateTime.now()),
//                new Post("3", "타이틀 3", "컨텐츠 3", "rwkang", LocalDateTime.now())
//                );

    }
}
