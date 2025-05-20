package com.example.demo.repositories;

import com.example.demo.model.TacoOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<TacoOrder, Long> {

    List<TacoOrder> findByDeliveryName(String orderName);

    Page<TacoOrder> findAll(Pageable pageable);

    @Modifying
    @Transactional
    @Query("DELETE FROM TacoOrder o WHERE o.deliveryName = :deliveryName")  // use entity's name not database's
    void deleteByDeliveryName(@Param("deliveryName") String deliveryName);
}
