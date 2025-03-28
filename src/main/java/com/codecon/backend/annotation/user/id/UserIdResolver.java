package com.codecon.backend.annotation.user.id;

import com.codecon.backend.annotation.require.Require;
import com.codecon.backend.exception.UnAuthorizedException;
import com.codecon.backend.model.ClientSession;
import com.codecon.backend.service.ClientSessionService;
import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Optional;

import static com.codecon.backend.util.HttpRequestUtils.*;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserIdResolver implements HandlerMethodArgumentResolver {

    ClientSessionService clientSessionService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(UserId.class);
    }


    @Override
    public Object resolveArgument(@NonNull MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  @NonNull NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        HttpServletRequest httpServletRequest = getHttpServletRequest();
        String bearerToken = getAuthorizationHeader(httpServletRequest);
        String token = parseToken(bearerToken);
        Optional<ClientSession> clientSessionByToken = clientSessionService.findByToken(token);

        if (clientSessionByToken.isPresent()) {
            ClientSession clientSession = clientSessionByToken.get();
            return clientSession.getClientId();
        }

        throw new UnAuthorizedException("Unauthorized");
    }

}