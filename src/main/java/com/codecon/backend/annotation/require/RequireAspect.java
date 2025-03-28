package com.codecon.backend.annotation.require;

import com.codecon.backend.exception.UnAuthorizedException;
import com.codecon.backend.model.ClientSession;
import com.codecon.backend.model.dto.ClientDto;
import com.codecon.backend.service.ClientSessionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

import static com.codecon.backend.util.HttpRequestUtils.*;

@Aspect
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RequireAspect {

    ClientSessionService clientSessionService;

    @Around("@annotation(require)")
    public Object validateRequire(ProceedingJoinPoint joinPoint, Require require) throws Throwable {
        HttpServletRequest httpServletRequest = getHttpServletRequest();
        String bearerToken = getAuthorizationHeader(httpServletRequest);
        String token = parseToken(bearerToken);
        Optional<ClientSession> clientSessionByToken = clientSessionService.findByToken(token);

        clientSessionByToken.ifPresent(clientSession -> {
            ClientDto clientDto = clientSession.toClientDto();
            validateClientAccess(clientDto, require);
        });

        return joinPoint.proceed();
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