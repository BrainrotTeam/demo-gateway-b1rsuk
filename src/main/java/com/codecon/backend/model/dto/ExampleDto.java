package com.codecon.backend.model.dto;


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

}
