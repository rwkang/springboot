/**
 * JPA 복합키(2개 이상 PK) 사용할 때.
 * 1. MemberRolePk.class 작성.
 * 2. MemberRole.class 각각의 컬럼에 @Id 설정.
 *
 * @author : 2023.10.05 Created by rwkang.
 * @see : https://abbo.tistory.com/329
 */

package shop.onekorea.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.*;

import java.io.Serializable;

@Data
@Entity
@IdClass(MemberRole.MemberRolePk.class)
public class MemberRole {
    @Id
    private Long memberId;
    @Id
    private Long roleId;

    @Data
    public static class MemberRolePk implements Serializable {
        private Long memberId;
        private Long roleId;

        public MemberRolePk() {};
    }

    public MemberRole() {};

    public MemberRole(Long memberId, Long roleId) {
        this.memberId = memberId;
        this.roleId = roleId;
    }

}
