package solstice.bootcamp.jpaapi.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solstice.bootcamp.jpaapi.model.Address;
import solstice.bootcamp.jpaapi.service.AddressService;

import java.util.List;

@RestController
@RequestMapping("")
public class AddressController {

  private AddressService addressService;

  public AddressController(AddressService addressService) {
    this.addressService = addressService;
  }

  @PostMapping("/accounts/{id}/addresses")
  public ResponseEntity create(@PathVariable("id") Long id, @RequestBody Address address) {
    return new ResponseEntity<>(addressService.save(address, id), HttpStatus.CREATED);
  }

  @GetMapping("/accounts/{id}/addresses")
  public List<Address> getAll(@PathVariable("id") Long id) {
    return addressService.getAll(id);
  }

  @GetMapping("/addresses/{addressId}")
  public Address getOne(@PathVariable("addressId") Long addressId) {
    return addressService.getOne(addressId);
  }

  @PutMapping("/addresses/{addressId}")
  public Address getOne(@PathVariable("addressId") Long addressId, @RequestBody Address address) {
    return addressService.update(addressId, address);
  }

  @DeleteMapping("/addresses/{addressId}")
  public ResponseEntity delete(@PathVariable("addressId") Long addressId) {
    addressService.delete(addressId);

    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }
}
