package com.kangfu.test.mapper;


import com.kangfu.test.domain.Post;


import java.util.List;

/**
 * @author kangfu
 */
public interface PostMapper {

    /**
     * 查询全部岗位
     * @return
     */
    List<Post> selectPostAll();

    /**
     * 查询岗位列表
     * @param userId
     * @return
     */
    List<Integer> selectPostListByUserId(Long userId);

}
