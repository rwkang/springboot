package shop.onekorea.springboot.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FormDto {
    private String name;
    private LocalDateTime today;
}
