package com.example.demo.Entity;


import com.example.demo.Base.BaseEntity;
import com.example.demo.Constant.ItemSellStatus;
import com.example.demo.DTO.GoodsDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter@Setter@ToString
@Entity
@Table
public class Goods extends BaseEntity {

    @Id
    @Column(name = "goods_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String goodsName;

    @Column(nullable = false)
    private Integer price;

    @Lob
    private String goodsDetails;

    @Column(nullable = false)
    private Integer stockNumber;

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;

    public static Goods createGoods(GoodsDTO goodsDTO){
        return Goods.builder()
                .goodsName(goodsDTO.getGoodsName())
                .goodsDetails(goodsDTO.getGoodsDetails())
                .price(goodsDTO.getPrice())
                .stockNumber(goodsDTO.getStockNumber())
                .itemSellStatus(goodsDTO.getItemSellStatus())
                .build();
    }

    public void updateGoods(GoodsDTO goodsDTO){
        this.goodsName = goodsDTO.getGoodsName();
        this.goodsDetails = goodsDTO.getGoodsDetails();
        this.price = goodsDTO.getPrice();
        this.itemSellStatus = goodsDTO.getItemSellStatus();
        this.stockNumber = goodsDTO.getStockNumber();
    }
}