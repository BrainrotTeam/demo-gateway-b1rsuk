package com.codecon.backend.model.dto;


import com.codecon.infrastructure.grpc.example.CreateExampleRequestDto;
import com.codecon.infrastructure.grpc.example.ExampleResponseDto;
import com.codecon.infrastructure.grpc.example.UpdateExampleRequestDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ExampleDto {

    Long id;
    Long exampleLong;
    String exampleString;

    public static ExampleDto of(ExampleResponseDto exampleResponseDto) {
        return new ExampleDto(exampleResponseDto.getId(), exampleResponseDto.getExampleLong(), exampleResponseDto.getExampleString());
    }

    public CreateExampleRequestDto toCreateExampleRequestDto() {
        CreateExampleRequestDto.Builder builder = CreateExampleRequestDto.newBuilder();

        return builder
                .setExampleLong(exampleLong)
                .setExampleString(exampleString)
                .build();
    }

    public UpdateExampleRequestDto toUpdateExampleRequestDto() {
        UpdateExampleRequestDto.Builder builder = UpdateExampleRequestDto.newBuilder();

        return builder
                .setId(id)
                .setExampleLong(exampleLong)
                .setExampleString(exampleString)
                .build();
    }

}
