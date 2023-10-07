package shop.onekorea.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.onekorea.springboot.entity.Dept;

public interface DeptRepository extends JpaRepository<Dept, Integer> {

}
