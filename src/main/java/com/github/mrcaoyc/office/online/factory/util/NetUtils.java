package com.github.mrcaoyc.office.online.factory.util;

import com.github.mrcaoyc.common.exception.runtime.DataNotFoundException;
import com.github.mrcaoyc.office.online.factory.constants.FileErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author CaoYongCheng
 */
@Slf4j
public class NetUtils {

    /**
     * 下载文件，获取字节码
     *
     * @param url     文件url
     * @param referer 用于解决防盗链无法访问资源问题
     * @return 文件字节
     */
    public static byte[] downloadFile(String url, String referer) {
        HttpHeaders headers = new HttpHeaders();
        if (!StringUtils.isEmpty(referer)) {
            headers.add("Referer", referer);
        }
        HttpEntity<Resource> httpEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.GET,
                httpEntity, byte[].class);
        if (Objects.equals(response.getStatusCode(), HttpStatus.OK)) {
            return response.getBody();
        }
        throw new DataNotFoundException(FileErrorEnum.NOT_FOUND);
    }

    /**
     * 获取文件的扩展名
     *
     * @param url 文件地址
     * @return 扩展名
     */
    public static String getFileExtension(String url) {
        int index = url.lastIndexOf(".");
        return url.substring(index + 1);
    }
}
