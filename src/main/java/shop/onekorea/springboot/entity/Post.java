package shop.onekorea.springboot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data // Getter, Setter, ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity // DB 연동하여 직접 Table 생성, 카멜 케이스는 스네이크 케이스로 자동 변경된다. (postId => post_id, createdAt => created_at)
@Table // 여기 "Entity 변수명"과 "Table 컬럼명"을 다르게 설정할 때 필요.
@ToString(of = {"postId", "title", "contents", "author", "createdAt"})
public class Post {
    /**
     * "UUID" 타입으로 직접 지정하면, 글자가 깨지는 현상이 발생하면서, MySQL DB에서는 제대로 뿌려주질 못하고, null을 뿌린다.
     * 그러므로, 반드시 "String" 타입으로 설정하고, UUID 생성 시에도 반드시 ".toString()"으로 변환하여 저장해야 한다.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @GeneratedValue(strategy = GenerationType.UUID)
    private String postId;
//    private UUID postId;
    @Column(length = 255)
    private String title;
    @Column(length = 500)
    private String contents;
    @Column(nullable = false, length = 100)
    private String author;
    @Builder.Default
    private LocalDateTime createdAt=LocalDateTime.now();

    public Post(String postId, String title, String contents, String author) {
//        this.postId = postId;
        this.title = title;
        this.contents = contents;
        this.author = author;
    }

}
