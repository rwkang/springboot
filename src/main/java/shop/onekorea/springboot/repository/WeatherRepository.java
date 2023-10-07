package shop.onekorea.springboot.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import shop.onekorea.springboot.entity.Weather;


@RequiredArgsConstructor
public abstract class WeatherRepository implements JpaRepository<Weather, Integer> {

}
