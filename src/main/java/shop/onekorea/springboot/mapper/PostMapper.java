package shop.onekorea.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import shop.onekorea.springboot.entity.Post;

import java.util.List;

@Mapper
public interface PostMapper {

    // 여기서 메소드 이름인 "listPost"가 "resources/mybatis/mapper/mysql/Post.xml" 파일에서 "id" 값과 반드시 일치해야 한다.
    public List<Post> listPost();
}
