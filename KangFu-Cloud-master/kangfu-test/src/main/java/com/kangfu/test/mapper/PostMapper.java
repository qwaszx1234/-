package com.kangfu.test.mapper;


import com.kangfu.test.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostMapper {

    List<Post> selectPostAll();

    List<Integer> selectPostListByUserId(Long userId);

}
