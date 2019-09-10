package com.github.mrcaoyc.office.online.factory.controller;

import com.github.mrcaoyc.common.exception.runtime.BaseRuntimeException;
import com.github.mrcaoyc.office.online.factory.autoconfigurer.OfficeOnlineConfiguration;
import com.github.mrcaoyc.office.online.factory.model.dto.FileInfoDTO;
import com.github.mrcaoyc.office.online.factory.model.vo.FileInfoResponse;
import com.github.mrcaoyc.office.online.factory.service.FileService;
import com.github.mrcaoyc.office.online.factory.util.DigestUtils;
import com.github.mrcaoyc.office.online.factory.util.NetUtils;
import com.github.mrcaoyc.web.constant.GlobalErrorMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@Api(tags = "文件信息")
public class FileContentController {

    private final OfficeOnlineConfiguration officeOnlineConfiguration;
    private final FileService fileService;

    public FileContentController(OfficeOnlineConfiguration officeOnlineConfiguration, FileService fileService) {
        this.officeOnlineConfiguration = officeOnlineConfiguration;
        this.fileService = fileService;
    }

    @ApiOperation(value = "获取文件流", response = OutputStream.class)
    @ApiImplicitParam(name = "name", value = "文件路径", dataTypeClass = String.class, paramType = "path")
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


    @ApiOperation(value = "获取文件信息", response = FileInfoResponse.class)
    @ApiImplicitParam(name = "name", value = "文件路径", dataTypeClass = String.class, paramType = "path")
    @GetMapping(value = "/files/{name}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<?> getFileInfo(@PathVariable String name) {
        String fileName = getFileRealPath(name);
        FileInfoDTO fileInfo = fileService.getFileInfo(fileName);
        FileInfoResponse fileInfoResponse = new FileInfoResponse();
        BeanUtils.copyProperties(fileInfo, fileInfoResponse);
        return ResponseEntity.ok(fileInfoResponse);
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