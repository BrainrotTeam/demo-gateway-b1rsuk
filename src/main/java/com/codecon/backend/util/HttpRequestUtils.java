package com.codecon.backend.util;

import com.codecon.backend.exception.UnAuthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

public final class HttpRequestUtils {

    public static String parseToken(String bearerToken) {
        return bearerToken.substring("Bearer ".length());
    }

    public static HttpServletRequest getHttpServletRequest() {
        return Optional.ofNullable((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .map(ServletRequestAttributes::getRequest)
                .orElseThrow(() -> new UnAuthorizedException("Request attributes are not available"));
    }

    public static String getAuthorizationHeader(HttpServletRequest httpServletRequest) {
        return Optional.ofNullable(httpServletRequest.getHeader("Authorization"))
                .orElseThrow(() -> new UnAuthorizedException("Authorization header does not exist"));
    }

    public static boolean isAuthorizationHeaderExists(HttpServletRequest httpServletRequest) {
        return Optional.ofNullable(httpServletRequest.getHeader("Authorization"))
                .isPresent();
    }


}
