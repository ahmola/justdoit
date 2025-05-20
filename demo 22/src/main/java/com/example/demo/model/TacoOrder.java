package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Data
@Document(collection = "taco_order")
public class TacoOrder {

    public TacoOrder(String id){
        this.id = id;
    }

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private Date placedAt = new Date();

    private List<Taco> tacos = new ArrayList<>();

    private void addTacos(Taco taco){
        this.tacos.add(taco);
    }
}
