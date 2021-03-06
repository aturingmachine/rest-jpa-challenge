package solstice.bootcamp.jpaapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solstice.bootcamp.jpaapi.model.Order;
import solstice.bootcamp.jpaapi.service.OrderService;

@RequestMapping("")
@RestController
public class OrderController {

  private OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping("/accounts/{id}/addresses/{addressId}/orders")
  public Iterable<Order> getAll(@PathVariable("id") Long id,
      @PathVariable("addressId") Long addressId) {

    return orderService.getAll(id, addressId);
  }

  @GetMapping("/orders/{orderId}")
  public Order getOne(@PathVariable("orderId") Long orderId) {
    return orderService.getOne(orderId);
  }

  @PostMapping("/accounts/{id}/addresses/{addressId}/orders")
  public ResponseEntity create(@PathVariable("id") Long id, @PathVariable("addressId") Long addressId,
      @RequestBody Order order) {

    Order newOrder = orderService.create(id, addressId, order);

    return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
  }

  @DeleteMapping("/orders/{orderId}")
  public ResponseEntity delete(@PathVariable("orderId") Long id) {

    orderService.delete(id);

    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/accounts/{id}/addresses/{addressId}/orders/{orderId}")
  public Order update(@PathVariable("accountId") Long accountId,
      @PathVariable("addressId") Long addressId, @PathVariable("orderId") Long orderId,
      @RequestBody Order order) {

    return orderService.update(accountId, addressId, orderId, order);
  }
}
