package shop.onekorea.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.onekorea.springboot.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    /**
     * Query Creation : 간단한 일반 검색 조건일 때...
     * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-creation
     */

    Member findByUserId(String userId);

    Member findByUserIdAndPassword(String userId, String password);

    // "Contains" = "Select ~~~ like"
    Member findByUserIdContainsAndPassword(String userId, String password);


}
