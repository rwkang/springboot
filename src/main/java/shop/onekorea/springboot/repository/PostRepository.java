package shop.onekorea.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.onekorea.springboot.entity.Post;

import java.util.UUID;

/**
 * Query Creation : 간단한 일반 검색 조건일 때...
 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-creation
  */

//@Repository // 아래 "JpaRepository"에서 상속되었을 때는, 여기를 생략해도 된다.
public interface PostRepository extends JpaRepository<Post, String> {  // "Entity.Post.postId" 타입이 "String"
//public interface PostRepository extends JpaRepository<Post, UUID> {  // "Entity.Post.postId" 타입이 "UUID"
//public interface PostRepository extends JpaRepository<PostEntity, Integer> { // "Entity.Post.postId" 타입이 "Integer"

//    Post save(Post post);

//    Post getPostById(String postId);

//    Post findTop1By();

//    Post getOne();

//    Post findTopByPostId();


    // 2023.09.25 Conclusion. "JpaRepository"는 기본적으로 아래 4가지 모두를 지원해 준다.
    // 1. Select
    // 2. Insert
    // 3. Update
    // 4. Delete

}
