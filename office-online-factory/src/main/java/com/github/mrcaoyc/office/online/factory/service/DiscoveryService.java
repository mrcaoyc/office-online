package com.github.mrcaoyc.office.online.factory.service;

import com.github.mrcaoyc.office.online.factory.constants.ActionEnum;

/**
 * @author CaoYongCheng
 */
public interface DiscoveryService {
    /**
     * 获取操作的url地址
     *
     * @param action   操作
     * @param filePath 原始文件地址
     * @return 操作的url地址
     */
    String getActionUrl(ActionEnum action, String filePath);
}
