package com.kangfu.test.service;

public interface PostService {
    Object selectPostAll();

    Object selectPostListByUserId(Long userId);
}
