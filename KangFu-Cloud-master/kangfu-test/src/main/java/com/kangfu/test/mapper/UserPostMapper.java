package com.kangfu.test.mapper;

import com.kangfu.test.domain.UserPost;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author kangfu
 */
public interface UserPostMapper {
    /**
     * 批处理
     * @param list
     */
    void batchUserPost(List<UserPost> list);

    /**
     * 删除用户岗位
     * @param userId
     */
    void deleteUserPostByUserId(Long userId);

    /**
     * 删除用户岗位
     * @param userIds
     */
    void deleteUserPost(Long[] userIds);
}
