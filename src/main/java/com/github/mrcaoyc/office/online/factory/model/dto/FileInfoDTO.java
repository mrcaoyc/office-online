package com.github.mrcaoyc.office.online.factory.model.dto;

import lombok.Data;

/**
 * 属性名首字母大写
 *
 * @author CaoYongCheng
 */
@Data
public class FileInfoDTO {
    private String baseFileName;
    private Long size;
    private String ownerId;
    private Long version;
    private String sha256;
    private Boolean allowExternalMarketplace;
    private Boolean userCanWrite;
    private Boolean supportsUpdate;
    private Boolean supportsLocks;
}
