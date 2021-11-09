package com.kangfu.test.mapper;

import com.kangfu.test.domain.UserPost;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPostMapper {
    void batchUserPost(List<UserPost> list);

    void deleteUserPostByUserId(Long userId);

    void deleteUserPost(Long[] userIds);
}
