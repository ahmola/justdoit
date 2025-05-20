package com.example.demo.repositories;

import com.example.demo.model.TacoOrder;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//@EnableCassandraRepositories(basePackages = "com.example.demo.repositories")
//public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
//    List<TacoOrder> findByDeliveryZip(String deliveryZip);
//}
