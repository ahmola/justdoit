package com.example.demo.Repository;

import com.example.demo.Entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, Long> {

    // find by name
    List<Goods> findByGoodsName(String goodsName);

    // find by price
    List<Goods> findByPrice(Integer price);
    List<Goods> findByPriceLessThan(Integer price);
    List<Goods> findByPriceGreaterThan(Integer price);
}
