package shop.onekorea.springboot;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import shop.onekorea.springboot.entity.Post;
import shop.onekorea.springboot.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * CommandLineRunner: 개발 시에 데이터를 임시로 생성하여 넣어준다.
 */

@SpringBootApplication
@RequiredArgsConstructor
public class SpringbootApplication implements CommandLineRunner {
//public class SpringbootApplication {

    private final PostRepository postRepository;

    public static void main(String[] args) {

        SpringApplication.run(SpringbootApplication.class, args);

        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[1];
        System.err.println(stackTraceElement.getClassName() + " : " + stackTraceElement.getLineNumber());

    }

    @Override
    public void run(String... args) throws Exception {
        List<Post> postList = List.of( // "개발 시에 데이터를 임시로 생성하여 넣어준다.
                new Post(UUID.randomUUID(), "타이틀 1", "컨텐츠 1", "rwkang", LocalDateTime.now()),
                new Post(UUID.randomUUID(), "타이틀 2", "컨텐츠 2", "rwkang", LocalDateTime.now()),
                new Post(UUID.randomUUID(), "타이틀 3", "컨텐츠 3", "rwkang", LocalDateTime.now())
                );

        postRepository.saveAll(postList);

    }

}