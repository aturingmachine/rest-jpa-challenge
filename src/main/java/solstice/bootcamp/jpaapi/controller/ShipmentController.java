package solstice.bootcamp.jpaapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solstice.bootcamp.jpaapi.model.Shipment;
import solstice.bootcamp.jpaapi.service.ShipmentService;

@RequestMapping("/accounts/{accountId}/addresses/{addressId}/shipments")
@RestController
public class ShipmentController {

  private ShipmentService shipmentService;

  public ShipmentController(ShipmentService shipmentService) {
    this.shipmentService = shipmentService;
  }

  @GetMapping("")
  public Iterable<Shipment> getAll(@PathVariable("accountId") Long accountId,
      @PathVariable("addressId") Long addressId) {

    return shipmentService.getAll(accountId, addressId);
  }

  @GetMapping("/{shipmentId}")
  public Shipment getOne(@PathVariable("shipmentId") Long id) {
    return shipmentService.getOne(id);
  }

  @PostMapping("")
  public ResponseEntity create(@PathVariable("accountId") Long accountId,
      @PathVariable("addressId") Long addressId, @RequestBody Shipment shipment) {

    Shipment newShipment = shipmentService.create(accountId, addressId, shipment);

    return new ResponseEntity<>(newShipment, HttpStatus.CREATED);
  }

  @PutMapping("/{shipmentId}")
  public Shipment update(@PathVariable("shipmentId") Long id, @RequestBody Shipment shipment) {
    return shipmentService.update(id, shipment);
  }

}
