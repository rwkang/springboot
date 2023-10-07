/**
 * Spring Boot.스프링 부트 이해
 * 1. application.yml 파일을 통한 JPA 데이터베이스 연동 : local(H2), dev(MySQL), prod(MySQL, Oracle 실패)
 *    post.xml 파일을 통한 mybatic 데이터베이스 연동 포함
 *    https://www.youtube.com/watch?v=xpXAj1udnkU&list=PLeMeDIV7bypvyxWv7eIUZubmfx9W-Jjdg&index=3
 *
 *    => 2023.09.26 10:27 Flutter 접속 성공, PowerAppMenu Flutter Project에서 SpringBoot Project로의 "로그인 접속 성공".
 *
 * 2. jasypt.Java Simplified Encryption 보안
 *    https://www.youtube.com/watch?v=3CY2pk-Ug10&list=PLeMeDIV7bypvyxWv7eIUZubmfx9W-Jjdg&index=4
 *    => MSA 패턴에서는, Config 서버를 별도로 두고, 일반 개발자는 어떤 곳에 접속을 못하게 하는 보안을 구성할 수 있다.
 *
 * @author : rwkang on 2023.10.01"
 * @see : https://www.youtube.com/watch?v=3CY2pk-Ug10&list=PLeMeDIV7bypvyxWv7eIUZubmfx9W-Jjdg&index=5&t=981s
 */

package shop.onekorea.springboot;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import shop.onekorea.springboot.entity.Dept;
import shop.onekorea.springboot.entity.Post;
import shop.onekorea.springboot.entity.Member;
import shop.onekorea.springboot.repository.DeptRepository;
import shop.onekorea.springboot.repository.PostRepository;
import shop.onekorea.springboot.repository.MemberRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;


/**
 * CommandLineRunner: 개발 시에 데이터를 임시로 생성하여 넣어준다.
 * application.yml 파일에서, 아래 내용으로 먼저 세팅하고, 반드시 운영 시에는 반드시 "Update" 또는 "Valid "로 전환해야 한다.
 *  ddl-auto: create-drop
 * @author : rwkang on 2023.10.05
 */

@SpringBootApplication
@RequiredArgsConstructor
public class SpringbootApplication implements CommandLineRunner {
//public class SpringbootApplication {

    private final PostRepository postRepository;
    private final MemberRepository userRepository;
    private final DeptRepository deptRepository;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String jpaHibernateDdlAuto;

    // Java에서 현재 클래스명, 메소드명, 줄 번호를 가져옵니다.
    public static String getInfo() {

        String className = Thread.currentThread().getStackTrace()[2].getClassName();
        // System.err.println("className: " + className);
        int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();

        /**
         * className = "shop.onekorea.springboot.SpringbootApplication"
         * 맨 마지막 클래스 이름만 받기.
         * 이렇게 하면, 아상한 배열이 리턴된다. ∵) 정규식에서 마침표(.)는 임의의 한 문자를 의미 : className.split(".")
         * ∴) 반드시 className.split("\\.") 이렇게 처리해야 한다.
         */

        String lastClassName = className.split("\\.")[className.split("\\.").length - 1];

        String info = lastClassName + "." + lineNumber;

        return info;
    }


    public static void main(String[] args) {

        SpringApplication.run(SpringbootApplication.class, args);

        System.err.println(getInfo());

    }

    @Override
    public void run(String... args) throws Exception {

        System.err.println(getInfo());

        if (jpaHibernateDdlAuto.contains("none")) {
            System.err.println(getInfo() + ", jpaHibernateDdlAuto: " + jpaHibernateDdlAuto);
            return;
        }

        if (jpaHibernateDdlAuto.contains("create")) {

            Random random = new Random();
            int max = random.nextInt(10);
            System.err.println("max : " + max);
            if (max < 3) max = 3;

            List<Dept> deptList = new ArrayList<>();

            String[] deptNameList = {"관리부", "영업부", "생산부", "자재부", "품질부", "A부", "B부", "C부", "D부", "E부"};

            for (int i = 0; i < max; i++) {
                Dept dept = new Dept(
                        "1001" + i * 1001,
                        deptNameList[i],
                        LocalDateTime.now(),
                        LocalDateTime.now());
                deptList.add(dept);

                // 부서는 10개 로우까지만 처리.
                if (i > 9) break;

            }
            deptRepository.saveAll(deptList);

            ///////////////////////////////////////////////////////////////////////////////////////////////

            List<Member> userList = new ArrayList<>();

            /**
             * UUID V4 : UUID.randomUUID().toString
             * "UUID" 타입으로 직접 지정하면, 글자가 깨지는 현상이 발생하면서, MySQL DB에서는 제대로 뿌려주질 못하고, null을 뿌린다.
             * 그러므로, 반드시 "String" 타입으로 설정하고, UUID 생성 시에도 반드시 ".toString()"으로 변환하여 저장해야 한다.
             * 또한,
             * Post.class.createdAt 컬럼을 @Builder.Default로 주고, 값을 LocalDateTime.now()로 주면, 여기서 줄 필요가 없다.
             */

            for (int i = 1; i < max; i++) {
                Member user = new Member(
                        UUID.randomUUID().toString(),
                        "rwkang" + i + "@naver.com",
                        "rwkang" + i,
                        "1111",
                        "ROLE_USER",
                        "0101111" + i * 1000,
                        "서울 강서구 " + i * 1000,
                        "프로파일 " + i,
                        "10011001",
                        LocalDateTime.now()
//                    LocalDateTime.now()
                );
                userList.add(user);
            }
            userRepository.saveAll(userList);

            ///////////////////////////////////////////////////////////////////////////////////////////////

            List<Post> postList = new ArrayList<>();

            /**
             * UUID V4 : UUID.randomUUID().toString
             * "UUID" 타입으로 직접 지정하면, 글자가 깨지는 현상이 발생하면서, MySQL DB에서는 제대로 뿌려주질 못하고, null을 뿌린다.
             * 그러므로, 반드시 "String" 타입으로 설정하고, UUID 생성 시에도 반드시 ".toString()"으로 변환하여 저장해야 한다.
             * 또한,
             * Post.class.createdAt 컬럼을 @Builder.Default로 주고, 값을 LocalDateTime.now()로 주면, 여기서 줄 필요가 없다.
             */

            for (int i = 1; i < max; i++) {
                Post post = new Post(UUID.randomUUID().toString(), "타이틀 " + i, "컨텐츠 " + i, "rwkang@naver.com");
//            Post post = new Post(UUID.randomUUID(), "타이틀 " + i, "컨텐츠 " + i, "rwkang", LocalDateTime.now());
                postList.add(post);

            }
//        List<Post> postList = List.of( // "개발 시에 데이터를 임시로 생성하여 넣어준다.
//                new Post(UUID.randomUUID(), "타이틀 1", "컨텐츠 1", "rwkang", LocalDateTime.now()),
//                new Post(UUID.randomUUID(), "타이틀 2", "컨텐츠 2", "rwkang", LocalDateTime.now()),
//                new Post(UUID.randomUUID(), "타이틀 3", "컨텐츠 3", "rwkang", LocalDateTime.now())
//                );

            postRepository.saveAll(postList);

            System.err.println(getInfo());
            System.err.println(getInfo() + ", jpaHibernateDdlAuto: " + jpaHibernateDdlAuto);

        }

    }

}