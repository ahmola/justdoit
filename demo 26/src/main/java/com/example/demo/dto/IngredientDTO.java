package com.example.demo.dto;

import com.example.demo.model.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class IngredientDTO {
    private String name;
    private String abbreviation;
    private String type;
}
