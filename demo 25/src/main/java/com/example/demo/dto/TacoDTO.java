package com.example.demo.dto;

import com.example.demo.model.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TacoDTO {

    private String name;

    private List<String> ingredients;

}
