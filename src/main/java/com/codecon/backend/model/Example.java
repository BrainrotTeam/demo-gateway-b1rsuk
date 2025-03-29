package com.codecon.backend.model;

import com.codecon.backend.model.dto.ExampleDto;
import com.codecon.backend.shared.model.BaseModel;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.apache.logging.log4j.util.Strings;

@Entity
@Table
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Example implements BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long exampleLong = 0L;
    String exampleString= Strings.EMPTY;

}
