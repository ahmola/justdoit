package com.example.demo.controllers.api;

import com.example.demo.dto.OrderDTO;
import com.example.demo.model.Taco;
import com.example.demo.model.TacoOrder;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.TacoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.results.DomainResultCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/order",
produces = {"application/json", "text/xml"})
public class OrderController {

    private final OrderRepository orderRepository;
    private final TacoRepository tacoRepository;

    public OrderController(
            OrderRepository orderRepository,
            TacoRepository tacoRepository){
        this.orderRepository = orderRepository;
        this.tacoRepository = tacoRepository;
    }

    @PostMapping
    public ResponseEntity<String> postOrder(@RequestBody OrderDTO orderDTO) throws Exception{
        log.info("Post " + orderDTO.toString());

        TacoOrder tacoOrder = new TacoOrder(orderDTO);
        log.info("Create " + tacoOrder);

        if(tacoOrder.equals(null))
            throw new DomainResultCreationException("Cannot Create " + orderDTO.getDeliveryName());

        orderRepository.save(tacoOrder);
        return ResponseEntity.ok("yeah!");
    }

    @GetMapping
    public ResponseEntity<List<Taco>> showTaco(@RequestParam String orderName) throws Exception{
        log.info("Get " + orderName);
        List<TacoOrder> order = orderRepository.findByDeliveryName(orderName);

        if(order.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        log.info("Tacos "+order.get(0).getTacos());

        List<Taco> tacos = new ArrayList<>();
        for (String tacoName : order.get(0).getTacos()){
            tacos.add(tacoRepository.findByName(tacoName).get(0));
        }

        return ResponseEntity.ok(tacos);
    }

    @PutMapping(path = "/{deliveryName}", consumes = "application/json")
    public ResponseEntity<TacoOrder> putOrder(
            @PathVariable("deliveryName") String orderName,
            @RequestBody OrderDTO updateOrder
    ) throws Exception{
        log.info("put " + orderName + " to " + updateOrder);
        TacoOrder tacoOrder = orderRepository.findByDeliveryName(orderName).get(0);

        if(tacoOrder.equals(null))
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        patchAndPut(tacoOrder, updateOrder);
        log.info("Updated " + tacoOrder);
        TacoOrder updatedTacoOrder = orderRepository.save(tacoOrder);

        return ResponseEntity.ok(updatedTacoOrder);
    }

    @PatchMapping(path = "/{orderName}", consumes = "application/json")
    public ResponseEntity<TacoOrder> patchOrder(
            @PathVariable("orderName") String orderName,
            @RequestBody OrderDTO patchOrder
    ){
        log.info("patch " + orderName + " with " + patchOrder);
        TacoOrder order = orderRepository.findByDeliveryName(orderName).get(0);

        patchAndPut(order, patchOrder);
        log.info("Patched " + order);
        TacoOrder updatedOrder = orderRepository.save(order);

        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{orderName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Boolean> deleteOrder(
            @PathVariable("orderName") String orderName
    ) throws Exception{
        boolean isDeleted = false;
        TacoOrder deletedOrder = new TacoOrder();
        try {
            orderRepository.deleteByDeliveryName(orderName);
            isDeleted = true;
        }catch (Exception e){
            log.info(e.toString());
        }

        return ResponseEntity.ok(isDeleted);
    }

    public void patchAndPut(TacoOrder tacoOrder, OrderDTO updateOrder){
        if(updateOrder.getDeliveryName() != null){
            tacoOrder.setDeliveryName(updateOrder.getDeliveryName());
        }
        if(updateOrder.getDeliveryCity() != null){
            tacoOrder.setDeliveryCity(updateOrder.getDeliveryCity());
        }
        if(updateOrder.getDeliveryState() != null){
            tacoOrder.setDeliveryState(updateOrder.getDeliveryState());
        }
        if(updateOrder.getDeliveryStreet() != null){
            tacoOrder.setDeliveryStreet(updateOrder.getDeliveryStreet());
        }
        if(updateOrder.getDeliveryZip() != null){
            tacoOrder.setDeliveryZip(updateOrder.getDeliveryZip());
        }
        if(updateOrder.getCcCVV() != null){
            tacoOrder.setCcCVV(updateOrder.getCcCVV());
        }
        if(updateOrder.getCcExpiration() != null){
            tacoOrder.setCcExpiration(updateOrder.getCcExpiration());
        }
        if(updateOrder.getCcNumber() != null){
            tacoOrder.setCcNumber(updateOrder.getCcNumber());
        }
        if(updateOrder.getTacos() != null){
            tacoOrder.setTacos(updateOrder.getTacos());
        }
    }
}
