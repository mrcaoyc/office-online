package com.github.mrcaoyc.office.online.factory.service.impl;

import com.github.mrcaoyc.office.online.factory.autoconfigurer.OfficeOnlineConfiguration;
import com.github.mrcaoyc.office.online.factory.model.dto.FileInfoDTO;
import com.github.mrcaoyc.office.online.factory.service.FileService;
import com.github.mrcaoyc.office.online.factory.util.DigestUtils;
import com.github.mrcaoyc.office.online.factory.util.NetUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author CaoYongCheng
 */
@Service
public class FileServiceImpl implements FileService {
    private final OfficeOnlineConfiguration officeOnlineConfiguration;

    public FileServiceImpl(OfficeOnlineConfiguration officeOnlineConfiguration) {
        this.officeOnlineConfiguration = officeOnlineConfiguration;
    }

    @Override
    @Cacheable(value = "fileInfo", key = "#fileName")
    public FileInfoDTO getFileInfo(String fileName) {
        byte[] bytes = NetUtils.downloadFile(fileName, officeOnlineConfiguration.getReferer());
        FileInfoDTO info = new FileInfoDTO();
        info.setBaseFileName(DigestUtils.computeMd5(fileName) + "." + NetUtils.getFileExtension(fileName));
        info.setSize((long) bytes.length);
        info.setOwnerId("admin");
        // 将dateId设置给version，相同的version，office-online会自动缓存
        long dateId = System.currentTimeMillis() / 1000 / officeOnlineConfiguration.getFileCacheExpires();
        info.setVersion(dateId);
        info.setSha256(DigestUtils.computeSha256(bytes));
        info.setAllowExternalMarketplace(true);
        info.setUserCanWrite(true);
        info.setSupportsUpdate(true);
        info.setSupportsLocks(true);
        return info;
    }
}
