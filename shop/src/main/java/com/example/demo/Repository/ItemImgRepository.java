package com.example.demo.Repository;

import com.example.demo.Entity.Image.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemImgRepository extends JpaRepository<ItemImg, Long> {
    List<ItemImg> findByGoodsIdOrderByIdAsc(Long goodsId);

}
