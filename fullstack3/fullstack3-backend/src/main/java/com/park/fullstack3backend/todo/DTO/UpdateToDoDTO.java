package com.park.fullstack3backend.todo.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateToDoDTO {

    private String name;

    private Boolean completed;
}
