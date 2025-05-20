package com.example.demo.DTO;

import com.example.demo.Constant.ItemSellStatus;
import com.example.demo.Entity.Goods;
import jakarta.persistence.Lob;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter@Setter@ToString
public class GoodsDTO {
    private Long id;

    private String goodsName;

    private Integer price;

    @Lob
    private String goodsDetails;

    private Integer stockNumber;

    private ItemSellStatus itemSellStatus;

    private List<ItemImgDTO> itemImgDTOList = new ArrayList<>();

    private List<Long> itemImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public Goods createGoods(){
        return modelMapper.map(this, Goods.class);
    }

    public static GoodsDTO of(Goods goods){
        return modelMapper.map(goods, GoodsDTO.class);
    }
}
