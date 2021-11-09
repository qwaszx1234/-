package com.kangfu.test.service;

/**
 * @author kangfu
 */
public interface PostService {
    /**
     * 查询全部岗位
     * @return
     */
    Object selectPostAll();

    /**
     * 查询岗位列表
     * @param userId
     * @return
     */
    Object selectPostListByUserId(Long userId);
}
