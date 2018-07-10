package solstice.bootcamp.jpaapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solstice.bootcamp.jpaapi.model.OrderLineItem;
import solstice.bootcamp.jpaapi.service.LineItemService;

@RequestMapping("/shipments/{shipmentId}/orders/{orderId}/lineItems")
@RestController
public class LineItemController {

  private LineItemService lineItemService;

  public LineItemController(LineItemService lineItemService) {
    this.lineItemService = lineItemService;
  }

  @GetMapping("")
  public Iterable<OrderLineItem> getAll(@PathVariable("shipmentId") Long shipmentId,
      @PathVariable("orderId") Long orderId) {
    return lineItemService.getAll(shipmentId, orderId);
  }

  @GetMapping("/{itemId}")
  public OrderLineItem getOne(@PathVariable("itemId") Long id) {
    return lineItemService.getOne(id);
  }

  @PostMapping("/products/{productId}")
  public ResponseEntity create(
      @PathVariable("shipmentId") Long shipmentId, @PathVariable("orderId") Long orderId,
      @PathVariable("productId") Long productId, @RequestBody OrderLineItem item) {

    OrderLineItem newItem = lineItemService.create(shipmentId, productId, orderId, item);

    return new ResponseEntity<>(newItem, HttpStatus.CREATED);
  }

  @PutMapping("/{itemId}/products/{productId}")
  public OrderLineItem update(
      @PathVariable("shipmentId") Long shipmentId, @PathVariable("itemId") Long itemId,
      @PathVariable("productId") Long productId, @PathVariable("orderId") Long orderId,
      @RequestBody OrderLineItem item) {

    return lineItemService.update(shipmentId, itemId, productId, orderId, item);
  }

  @DeleteMapping("/{itemId}")
  public ResponseEntity delete(@PathVariable("itemId") Long id) {
    lineItemService.delete(id);

    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }
}
