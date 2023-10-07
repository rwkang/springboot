package shop.onekorea.springboot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@ToString(of = {"id", "depts_code", "depts_name", "updated_at", "created_at"})
public class Dept {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "dept_code", length = 100)
    private String deptCode;
    @Column(name = "dept_name", length = 100)
    private String deptName;

    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

//    @OneToMany(mappedBy = "dept")
//    private List<User> userList = new ArrayList<>();

    public Dept(String deptCode, String deptName, LocalDateTime updatedAt, LocalDateTime createdAt) {
        /**
         * 여기에 명기를 하지 않은 컬럼은 "SpringbootApplication.class"에서 데이터를 추가할 때, 그 값이 "null"로 들어가네...
         */
        this.deptCode = deptCode;
        this.deptName = deptName;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

}