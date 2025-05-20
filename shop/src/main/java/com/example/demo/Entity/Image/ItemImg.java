package com.example.demo.Entity.Image;

import com.example.demo.Base.BaseEntity;
import com.example.demo.Entity.Goods;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString
@Entity
@Table(name = "item_img")
public class ItemImg extends BaseEntity {

    @Id
    @Column(name = "item_img_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String imgName;

    private String originalImgName;

    private String imgURL;

    private String repImgYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Goods goods;

    public void updateItemImg(String originalImgName, String imgName, String imgURL){
        this.originalImgName = originalImgName;
        this.imgName = imgName;
        this.imgURL = imgURL;
    }
}
