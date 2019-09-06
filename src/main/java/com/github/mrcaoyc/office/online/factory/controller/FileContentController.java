package com.github.mrcaoyc.office.online.factory.controller;

import com.github.mrcaoyc.common.exception.runtime.BaseRuntimeException;
import com.github.mrcaoyc.office.online.factory.autoconfigurer.OfficeOnlineConfiguration;
import com.github.mrcaoyc.office.online.factory.model.vo.FileInfoResponse;
import com.github.mrcaoyc.office.online.factory.util.DigestUtils;
import com.github.mrcaoyc.office.online.factory.util.NetUtils;
import com.github.mrcaoyc.web.constant.GlobalErrorMessage;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * 文件信息
 *
 * @author CaoYongCheng
 */
@RestController
@RequestMapping(value = "/wopi")
public class FileContentController {

    private final OfficeOnlineConfiguration officeOnlineConfiguration;

    public FileContentController(OfficeOnlineConfiguration officeOnlineConfiguration) {
        this.officeOnlineConfiguration = officeOnlineConfiguration;
    }

    /**
     * 获取文件流
     * <p>
     * office online server 读取文件流
     *
     * @param name     文件名称
     * @param response 文件流
     */
    @GetMapping("/files/{name}/contents")
    public void getFile(@PathVariable(name = "name") String name, HttpServletResponse response) {
        OutputStream outputStream = null;
        try {
            // 文件的路径
            String fileName = getFileRealPath(name);
            byte[] fileBytes = NetUtils.downloadFile(fileName, officeOnlineConfiguration.getReferer());
            // 以流的形式下载文件
            // 清空response
            response.reset();
            String downLoadFile = DigestUtils.computeMd5(fileName) + "." + NetUtils.getFileExtension(fileName);
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" +
                    new String(downLoadFile.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            response.addHeader("Content-Length", "" + fileBytes.length);
            response.setContentType("application/octet-stream");
            outputStream = response.getOutputStream();
            outputStream.write(fileBytes);
            outputStream.flush();
            System.out.println("GET获取文件Contents结束!!!!");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取文件信息
     *
     * @param name 文件名称
     */
    @GetMapping(value = "/files/{name}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<?> getFileInfo(@PathVariable String name) {
        System.out.println("获取文件信息");
        FileInfoResponse info = new FileInfoResponse();

        String fileName = getFileRealPath(name);
        byte[] bytes = NetUtils.downloadFile(fileName, officeOnlineConfiguration.getReferer());
        // 取得文件名
        info.setBaseFileName(DigestUtils.computeMd5(fileName) + "." + NetUtils.getFileExtension(fileName));
        info.setSize((long) bytes.length);
        info.setOwnerId("admin");
        // 将dateId设置给version，相同的version，office-online会自动缓存
        long dateId = System.currentTimeMillis() / 1000 / 3600 / 24;
        info.setVersion(dateId);
        info.setSha256(DigestUtils.computeSha256(bytes));
        info.setAllowExternalMarketplace(true);
        info.setUserCanWrite(true);
        info.setSupportsUpdate(true);
        info.setSupportsLocks(true);

        return ResponseEntity.ok(info);

    }

    /**
     * 获取文件的真实地址
     *
     * @param filePath 文件地址
     * @return 文件的真实地址
     */
    private String getFileRealPath(String filePath) {
        String privateFilePath;
        try {
            privateFilePath = URLDecoder.decode(filePath, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new BaseRuntimeException(GlobalErrorMessage.INTERNAL_SERVER_ERROR);
        }
        byte[] fileNameBytes = Base64Utils.decodeFromString(privateFilePath);
        return new String(fileNameBytes, StandardCharsets.UTF_8);
    }
}