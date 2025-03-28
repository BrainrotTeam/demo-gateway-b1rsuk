package com.codecon.backend.model.dto;

import com.codecon.backend.model.Role;
import com.codecon.infrastructure.grpc.authentication.ClientPermission;
import com.codecon.infrastructure.grpc.authentication.ClientResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    Long id;
    String email;
    Role role;

    public static ClientDto convertToDto(ClientResponseDto clientResponseDto) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(clientResponseDto.getId());
        clientDto.setEmail(clientResponseDto.getEmail());

        ClientPermission clientPermission = clientResponseDto.getClientPermission();
        Role clientRole = Role.of(clientPermission);
        clientDto.setRole(clientRole);

        return clientDto;
    }

}
