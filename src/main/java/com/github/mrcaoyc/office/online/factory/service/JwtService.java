package com.github.mrcaoyc.office.online.factory.service;

/**
 * @author CaoYongCheng
 */
public interface JwtService {
    /**
     * 生成一个访问令牌
     *
     * @return 令牌
     */
    String generateKey();
}
