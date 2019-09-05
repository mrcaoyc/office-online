package com.github.mrcaoyc.office.online.factory.controller;

import com.github.mrcaoyc.common.exception.runtime.BaseRuntimeException;
import com.github.mrcaoyc.office.online.factory.constants.ActionEnum;
import com.github.mrcaoyc.office.online.factory.service.DiscoveryService;
import com.github.mrcaoyc.web.constant.GlobalErrorMessage;
import io.swagger.annotations.Api;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author CaoYongCheng
 */
@RestController
@Api
@RequestMapping("/wopi")
public class DiscoveryController {
    private final DiscoveryService discoveryService;

    public DiscoveryController(DiscoveryService discoveryService) {
        this.discoveryService = discoveryService;
    }


    @GetMapping(value = "/embed-view")
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
}
