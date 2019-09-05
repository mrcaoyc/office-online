package com.github.mrcaoyc.office.online.factory.service.impl;

import com.github.mrcaoyc.common.exception.runtime.BadRequestException;
import com.github.mrcaoyc.common.exception.runtime.BaseRuntimeException;
import com.github.mrcaoyc.office.online.factory.autoconfigurer.OfficeOnlineConfiguration;
import com.github.mrcaoyc.office.online.factory.constants.ActionEnum;
import com.github.mrcaoyc.office.online.factory.constants.OfficeOnlineAction;
import com.github.mrcaoyc.office.online.factory.constants.OfficeOnlineErrorMessage;
import com.github.mrcaoyc.office.online.factory.service.DiscoveryService;
import com.github.mrcaoyc.office.online.factory.service.JwtService;
import com.github.mrcaoyc.web.constant.GlobalErrorMessage;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;

/**
 * @author CaoYongCheng
 */
@Service
public class DiscoveryServiceImpl implements DiscoveryService {
    private final OfficeOnlineConfiguration officeOnlineConfiguration;
    private final JwtService jwtService;

    public DiscoveryServiceImpl(OfficeOnlineConfiguration officeOnlineConfiguration,
                                JwtService jwtService) {
        this.officeOnlineConfiguration = officeOnlineConfiguration;
        this.jwtService = jwtService;
    }

    @Override
    public String getActionUrl(ActionEnum action, String filePath) {
        String extension = FilenameUtils.getExtension(filePath);
        if (StringUtils.isEmpty(extension)) {
            throw new BadRequestException(OfficeOnlineErrorMessage.NONSUPPORT);
        }
        String actionUrl = OfficeOnlineAction.parse(action, extension);
        if (actionUrl == null) {
            throw new BadRequestException(OfficeOnlineErrorMessage.NONSUPPORT);
        }
        String wopiSrc = MessageFormat.format("{0}/wopi/files/{1}",
                officeOnlineConfiguration.getDomainServerHost(),
                Base64Utils.encodeToString(filePath.getBytes(StandardCharsets.UTF_8))
        );
        String wopiSrcURLEncoder;
        try {
            wopiSrcURLEncoder = URLEncoder.encode(wopiSrc, StandardCharsets.ISO_8859_1.name());
        } catch (Exception e) {
            throw new BaseRuntimeException(GlobalErrorMessage.INTERNAL_SERVER_ERROR, e);
        }
        String accessToken = Base64Utils.encodeToString(jwtService.generateKey().getBytes(StandardCharsets.UTF_8));
        return MessageFormat.format("{0}/{1}?access_token={2}&WOPISrc={3}",
                officeOnlineConfiguration.getHost(),
                actionUrl,
                accessToken,
                wopiSrcURLEncoder
        );
    }
}
