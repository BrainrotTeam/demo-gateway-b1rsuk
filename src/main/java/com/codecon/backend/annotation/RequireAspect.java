package com.codecon.backend.annotation;

import com.codecon.backend.exception.UnAuthorizedException;
import com.codecon.backend.model.dto.ClientDto;
import com.codecon.backend.service.GRPCAuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Optional;

@Aspect
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RequireAspect {

    GRPCAuthenticationService grpcAuthenticationService;

    @Around("@annotation(require)")
    public Object validateRequire(ProceedingJoinPoint joinPoint, Require require) throws Throwable {
        HttpServletRequest httpServletRequest = getHttpServletRequest();
        String bearerToken = getAuthorizationHeader(httpServletRequest);
        ClientDto clientDto = grpcAuthenticationService.getActualDataByToken(bearerToken);

        validateClientAccess(clientDto, require);

        return joinPoint.proceed();
    }

    public HttpServletRequest getHttpServletRequest() {
        return Optional.ofNullable((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .map(ServletRequestAttributes::getRequest)
                .orElseThrow(() -> new UnAuthorizedException("Request attributes are not available"));
    }

    public String getAuthorizationHeader(HttpServletRequest httpServletRequest) {
        return Optional.ofNullable(httpServletRequest.getHeader("Authorization"))
                .orElseThrow(() -> new UnAuthorizedException("Authorization header does not exist"));
    }

    private void validateClientAccess(ClientDto clientDto, Require require) {
        if (!hasRequiredRole(clientDto, require)) {
            throw new UnAuthorizedException(clientDto, require.value());
        }
    }

    public boolean hasRequiredRole(ClientDto clientDto, Require require) {
        return Arrays.asList(require.value()).contains(clientDto.getRole());
    }

}