package com.example.demo.DTO;

import com.example.demo.Entity.Image.ItemImg;
import lombok.*;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString
@Builder
public class ItemImgDTO {
    private Long id;

    private String imgName;

    private String originalImgName;

    private String imgURL;

    private String repImgYn;

    private static ModelMapper modelMapper = new ModelMapper();

    public static ItemImgDTO of(ItemImg itemImg){
        return modelMapper.map(itemImg, ItemImgDTO.class);
    }
}