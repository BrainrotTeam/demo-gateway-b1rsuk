package com.codecon.backend.controller;

import com.codecon.backend.model.dto.ExampleDto;
import com.codecon.backend.service.GRPCExampleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("example")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExampleController {

    GRPCExampleService grpcExampleService;

    @PostMapping
    public ExampleDto createExample(@RequestBody ExampleDto exampleDto) {
        return grpcExampleService.createExample(exampleDto);
    }

    @PutMapping("/{id}")
    public ExampleDto updateExample(@RequestBody ExampleDto exampleDto, @PathVariable Long id) {
        return grpcExampleService.updateExample(exampleDto, id);
    }

    @GetMapping
    public List<ExampleDto> findExamples() {
        return grpcExampleService.findExamples();
    }

}
