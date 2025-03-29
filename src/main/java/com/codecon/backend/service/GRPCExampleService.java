package com.codecon.backend.service;

import com.codecon.backend.model.dto.ExampleDto;
import com.codecon.infrastructure.grpc.example.*;
import com.codecon.infrastructure.grpc.example.CreateExampleRequestDto;
import com.codecon.infrastructure.grpc.example.UpdateExampleRequestDto;
import com.codecon.infrastructure.grpc.example.ExampleResponseDto;
import com.codecon.infrastructure.grpc.example.ExamplesResponseDto;
import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GRPCExampleService {

    ExampleServiceGrpc.ExampleServiceBlockingStub exampleServiceBlockingStub;

    public GRPCExampleService() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9091)
                .usePlaintext() // todo: Настроить SSL
                .build();
        this.exampleServiceBlockingStub = ExampleServiceGrpc.newBlockingStub(channel);
    }

    public ExampleDto createExample(ExampleDto exampleDto) {
        CreateExampleRequestDto exampleRequestDto = exampleDto.toCreateExampleRequestDto();
        ExampleResponseDto exampleResponseDto = exampleServiceBlockingStub.createExample(exampleRequestDto);
        return ExampleDto.of(exampleResponseDto);
    }

    public ExampleDto updateExample(ExampleDto exampleDto, Long exampleId) {
      exampleDto.setId(exampleId);

      UpdateExampleRequestDto exampleRequestDto = exampleDto.toUpdateExampleRequestDto();
      ExampleResponseDto exampleResponseDto = exampleServiceBlockingStub.updateExample(exampleRequestDto);
      return ExampleDto.of(exampleResponseDto);
    }

    public List<ExampleDto> findExamples() {
        Empty empty = Empty.newBuilder().build();
        ExamplesResponseDto examplesResponseDto = exampleServiceBlockingStub.findExamples(empty);

        return examplesResponseDto
                .getExamplesRequestDtoList()
                .stream()
                .map(ExampleDto::of)
                .toList();
    }

}
