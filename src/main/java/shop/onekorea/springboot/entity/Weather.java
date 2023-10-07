package shop.onekorea.springboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.checkerframework.checker.units.qual.C;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Builder // 값을 주입하여 객체를 만드는 용도
 * @ToString // ToString 메소드 자동 생성 (객체가 가지고 있는 정보나 값들을 문자열로 만들어 리턴)
 */
@Data
@AllArgsConstructor
@Builder
@ToString
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50, nullable = false)
    private String weather;
    @Column(length = 50, nullable = false)
    private String icon;
    @Column(nullable = false)
    private Double temperature;
    @Column(length = 500, nullable = false)
    private String text;
    @Column(nullable = false)
    private LocalDate createdAt;
//    private LocalDateTime createdAt;

    public Weather() {

    }
}
