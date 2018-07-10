package solstice.bootcamp.jpaapi.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solstice.bootcamp.jpaapi.model.Address;
import solstice.bootcamp.jpaapi.service.AddressService;

import java.util.List;

@RestController
@RequestMapping("/accounts/{id}/addresses")
public class AddressController {

  private AddressService addressService;

  public AddressController(AddressService addressService) {
    this.addressService = addressService;
  }

  @PostMapping("")
  public ResponseEntity create(@PathVariable("id") Long id, @RequestBody Address address) {
    return new ResponseEntity<>(addressService.save(address, id), HttpStatus.CREATED);
  }

  @GetMapping("")
  public List<Address> getAll(@PathVariable("id") Long id) {
    return addressService.getAll(id);
  }

  @GetMapping("/{addressId}")
  public Address getOne(@PathVariable("id") Long id, @PathVariable("addressId") Long addressId) {
    return addressService.getOne(addressId);
  }

  @PutMapping("/{addressId}")
  public Address getOne(@PathVariable("addressId") Long addressId, @RequestBody Address address) {
    return addressService.update(addressId, address);
  }

  @DeleteMapping("/{addressId}")
  public ResponseEntity delete(@PathVariable("addressId") Long id) {
    addressService.delete(id);

    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }
}
