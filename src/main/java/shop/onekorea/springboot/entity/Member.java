/**
 * "User"가 JPA 예약어이므로 "Users"를 사용한다.
 *
 * https://velog.io/@tmdgh0221/%EC%8A%A4%ED%94%84%EB%A7%81-Data-JPA-%EC%A0%95%EB%A6%AC
 * 롬복을 활용에 @Getter, @Setter 설정을 합니다. 물론 실제 프로젝트에서는 수정자 접근은 재고려해야 합니다.
 * 엔티티 클래스의 경우 access 수준이 protected 이상인 디폴트 생성자가 있어야 합니다.
 * 직접 만들어줘도 되고, @NoArgsConstructor(access = AccessLevel.PROTECTED)으로 자동 생성해도 됩니다.
 * 향후 해당 객체 정보 출력을 위해 @ToString으로 설정합니다. 이때 가급적이면 연관 관계가 없는 내부 필드만 적는 것이 좋습니다.
 * Member와 Team은 N:1 관계이므로, Member 클래스에 FK가 있어야 하므로,
 * @JoinColumn(name = "team_id") 설정을 해주어야 하고,
 * @ManyToOne fetch 전략도 @ManyToOne(fetch = FetchType.LAZY)을 명시해서 지연 전략으로 설정합니다.
 * changeTeam()과 같은 메서드를 통해, 즉 연관 관계 편의 메서드를 통해, 양방향 연관 관계 객체를 한번에 처리합니다.
 *
 * rwkang on 2023.09.25
 */

package shop.onekorea.springboot.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenerationTime;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Table()
@ToString(of = {"id", "user_id", "email", "name", "password", "phone_no", "role", "address", "profile", "dept_code", "updated_at", "created_at"})
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;
    @Column(nullable = false, length = 100, unique = true)
    private String email;
    @Column(nullable = false, length = 100, unique = true)
    private String name;
    @Column(nullable = false, length = 100)
    private String password;
    @Column(nullable = false, length = 100)
    private String role;
    @Column(nullable = false, length = 100)
    private String phoneNo;
    private String address;
    private String profile;

    // A 부분. 3-1
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "dept_code")
//    private Dept dept;
    private String deptCode;

    @org.hibernate.annotations.Generated(GenerationTime.ALWAYS) // 2023.09.29 Conclusion. "생성" 시에만 "서버 컴퓨터 시간"을 사용하고,
    @Column(name = "updated_at", updatable = true)              //                        "update" 시에는 "수정 되도록" : 생략 가능 ∵) true가 디폴트 임.
    private LocalDateTime updatedAt;

    @Builder.Default
    @org.hibernate.annotations.Generated(GenerationTime.ALWAYS) // 2023.09.29 Conclusion. "생성" 시에만 "서버 컴퓨터 시간"을 사용하고,
    @Column(name = "create_at", updatable = false)              //                        "update" 시에는 "수정 안 됨"
    private LocalDateTime createdAt=LocalDateTime.now();

    public Member(String userId, String email, String name, String password, String role, String phoneNo, String address, String profile,
                  String deptCode, LocalDateTime updatedAt) {
//        this.userId = userId;
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
        this.phoneNo = phoneNo;
        this.address = address;
        this.profile = profile;

        this.deptCode = deptCode;
        // A 부분. 3-2
//        if (deptsCode != null) {
//            changeDept(dept);
//        }

        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    // A 부분. 3-3
//    public void changeDept(Depts dept) {
//        this.dept = dept;
//        dept.getUsersList().add(this);
//    }

}
