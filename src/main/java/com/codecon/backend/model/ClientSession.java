package com.codecon.backend.model;

import com.codecon.backend.model.dto.ClientDto;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(timeToLive = 300)
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ClientSession {

    @Id
    String token;

    Long clientId;
    Role role;

    public ClientDto toClientDto() {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(clientId);
        clientDto.setRole(role);

        return clientDto;
    }

}
