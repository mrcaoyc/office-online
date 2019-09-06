package com.github.mrcaoyc.office.online.factory.controller;

import com.github.mrcaoyc.common.exception.runtime.BaseRuntimeException;
import com.github.mrcaoyc.office.online.factory.constants.ActionEnum;
import com.github.mrcaoyc.office.online.factory.service.DiscoveryService;
import com.github.mrcaoyc.web.constant.GlobalErrorMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author CaoYongCheng
 */
@RestController
@Api(tags = "地址转换", value = "asdf")
@RequestMapping("/wopi")
public class DiscoveryController {
    private final DiscoveryService discoveryService;

    public DiscoveryController(DiscoveryService discoveryService) {
        this.discoveryService = discoveryService;
    }

    @GetMapping(value = "/view")
    @ApiOperation(value = "获取普通视图", response = String.class, produces = MediaType.TEXT_PLAIN_VALUE)
    @ApiImplicitParam(name = "path", value = "文件地址", dataTypeClass = String.class, paramType = "query")
    public HttpEntity<?> getView(@RequestParam String path) {
        String decodePath;
        try {
            decodePath = URLDecoder.decode(path, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new BaseRuntimeException(GlobalErrorMessage.INTERNAL_SERVER_ERROR, e);
        }
        String actionUrl = discoveryService.getActionUrl(ActionEnum.VIEW, decodePath);
        return ResponseEntity.ok(actionUrl);
    }

    @GetMapping(value = "/embed-view")
    @ApiOperation(value = "获取嵌入式视图", response = String.class, produces = MediaType.TEXT_PLAIN_VALUE)
    @ApiImplicitParam(name = "path", value = "文件地址", dataTypeClass = String.class, paramType = "query")
    public HttpEntity<?> getEmbedView(@RequestParam String path) {
        String decodePath;
        try {
            decodePath = URLDecoder.decode(path, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new BaseRuntimeException(GlobalErrorMessage.INTERNAL_SERVER_ERROR, e);
        }
        String actionUrl = discoveryService.getActionUrl(ActionEnum.EMBED_VIEW, decodePath);
        return ResponseEntity.ok(actionUrl);
    }

    @GetMapping(value = "/mobile-view")
    @ApiOperation(value = "获取移动端视图", response = String.class, produces = MediaType.TEXT_PLAIN_VALUE)
    @ApiImplicitParam(name = "path", value = "文件地址", dataTypeClass = String.class, paramType = "query")
    public HttpEntity<?> getMobileView(@RequestParam String path) {
        String decodePath;
        try {
            decodePath = URLDecoder.decode(path, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new BaseRuntimeException(GlobalErrorMessage.INTERNAL_SERVER_ERROR, e);
        }
        String actionUrl = discoveryService.getActionUrl(ActionEnum.MOBILE_VIEW, decodePath);
        return ResponseEntity.ok(actionUrl);
    }
}
