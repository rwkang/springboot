package shop.onekorea.springboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data // Getter, Setter, ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // DB 연동하여 직접 Table 생성, 카멜 케이스는 스네이크 케이스로 자동 변경된다. (postId => post_id, createdAt => created_at)
@Table // 여기 "Entity 변수명"과 "Table 컬럼명"을 다르게 설정할 때 필요.
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID postId;
//    private String postId;
    private String title;
    private String contents;
    private String author;
    private LocalDateTime createdAt;

}
