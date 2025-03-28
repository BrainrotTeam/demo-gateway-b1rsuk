package com.codecon.backend.filter;

import com.codecon.backend.model.ClientSession;
import com.codecon.backend.model.Role;
import com.codecon.backend.model.dto.ClientDto;
import com.codecon.backend.service.ClientSessionService;
import com.codecon.backend.service.GRPCAuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;

import static com.codecon.backend.util.HttpRequestUtils.*;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RequestInterceptor implements HandlerInterceptor {

    ClientSessionService clientSessionService;
    GRPCAuthenticationService grpcAuthenticationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HttpServletRequest httpServletRequest = getHttpServletRequest();

        if (!isAuthorizationHeaderExists(httpServletRequest)) {
            return true;
        }

        String bearerToken = getAuthorizationHeader(httpServletRequest);
        String token = parseToken(bearerToken);
        Optional<ClientSession> clientSessionByToken = clientSessionService.findByToken(token);

        if (clientSessionByToken.isEmpty()) {
            ClientDto clientDto = grpcAuthenticationService.getActualDataByToken(bearerToken);

            ClientSession clientSession = new ClientSession();

            Long clientId = clientDto.getId();
            clientSession.setClientId(clientId);

            Role role = clientDto.getRole();
            clientSession.setRole(role);

            clientSession.setToken(token);
            clientSessionService.save(clientSession);
        }

        return true;
    }
}
