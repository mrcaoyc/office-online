package com.github.mrcaoyc.office.online.factory.filter;

import com.github.mrcaoyc.common.exception.runtime.UnauthorizedException;
import com.github.mrcaoyc.office.online.factory.autoconfigurer.JwtTokenProperties;
import com.github.mrcaoyc.office.online.factory.constants.OfficeOnlineErrorMessage;
import io.jsonwebtoken.Jwts;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author CaoYongCheng
 */
public class AuthorizationFilter implements HandlerInterceptor {
    private final JwtTokenProperties jwtTokenProperties;

    public AuthorizationFilter(JwtTokenProperties jwtTokenProperties) {
        this.jwtTokenProperties = jwtTokenProperties;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String accessToken = request.getParameter("access_token");
        if (StringUtils.isEmpty(accessToken)) {
            throw new UnauthorizedException(OfficeOnlineErrorMessage.UNAUTHORIZED);
        }
        accessToken = new String(Base64Utils.decodeFromUrlSafeString(accessToken));
        if (StringUtils.isEmpty(accessToken)) {
            throw new UnauthorizedException(OfficeOnlineErrorMessage.UNAUTHORIZED);
        }
        parseAccessToken(accessToken);
        return true;
    }

    /**
     * 解析accessToken
     *
     * @param accessToken 访问令牌
     */
    private void parseAccessToken(String accessToken) {
        if (StringUtils.isEmpty(accessToken) || "".equals(accessToken.trim())) {
            throw new UnauthorizedException(OfficeOnlineErrorMessage.UNAUTHORIZED);
        }
        String secret = jwtTokenProperties.getSecret();
        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(accessToken.replace(jwtTokenProperties.getType() + " ", ""))
                    .getBody();
        } catch (Exception e) {
            throw new UnauthorizedException(OfficeOnlineErrorMessage.UNAUTHORIZED);
        }
    }
}
