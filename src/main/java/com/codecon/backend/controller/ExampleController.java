package com.codecon.backend.controller;

import com.codecon.backend.annotation.Require;
import com.codecon.backend.model.Example;
import com.codecon.backend.model.Role;
import com.codecon.backend.model.dto.ExampleDto;
import com.codecon.backend.service.ExampleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("example")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExampleController {

    ExampleService exampleService;

    @PostMapping
    public ExampleDto createExample(@RequestBody ExampleDto exampleDto) {
        Example example = exampleService.toEntity(exampleDto);
        Example createdExample = exampleService.create(example);

        return createdExample.toDto();
    }

    @PutMapping("/{id}")
    public ExampleDto updateExampleById(Long id, @RequestBody ExampleDto exampleDto) {
        Example example = exampleService.toEntity(exampleDto);
        Example createdExample = exampleService.update(example, id);

        return createdExample.toDto();
    }

    @GetMapping
    public List<ExampleDto> getExamples() {
        return exampleService.findAll().stream()
                .map(Example::toDto)
                .collect(Collectors.toList());
    }


//    @PostMapping("/kafka")
//    public void sendExampleToConsumer(@RequestBody ExampleDto exampleDto) {
//        Example example = exampleService.toEntity(exampleDto);
//
//        kafkaProducer.sendExample(example);
//    }

}
