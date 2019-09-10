package com.github.mrcaoyc.office.online.factory.service;

import com.github.mrcaoyc.office.online.factory.model.dto.FileInfoDTO;

/**
 * @author CaoYongCheng
 */
public interface FileService {
    /**
     * 根据文件地址获取文件信息
     *
     * @param fileName 文件地址
     * @return 文件信息
     */
    FileInfoDTO getFileInfo(String fileName);
}
