package solstice.bootcamp.jpaapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solstice.bootcamp.jpaapi.model.Account;
import solstice.bootcamp.jpaapi.model.Order;
import solstice.bootcamp.jpaapi.model.OrderDetails;
import solstice.bootcamp.jpaapi.model.Shipment;
import solstice.bootcamp.jpaapi.repository.AccountRepository;
import solstice.bootcamp.jpaapi.service.AccountService;

@RequestMapping("/accounts")
@RestController
public class AccountController {
  private AccountService accountService;

  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @GetMapping("")
  public Iterable<Account> getAll() {
    return accountService.getAll();
  }

  @PostMapping("")
  public ResponseEntity create(@RequestBody Account account) {
    account = accountService.save(account);

    return new ResponseEntity<>(account, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public Account getOne(@PathVariable("id") Long id) {
    return accountService.getOne(id);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable("id") Long id) {
    accountService.delete(id);

    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/{id}")
  public Account update(@PathVariable("id") Long id, @RequestBody Account account) {

    return accountService.update(id, account);
  }

  @GetMapping("/{id}/orders")
  public Iterable<Order> getAllOrders(@PathVariable("id") Long id) {
    return accountService.getAllOrders(id);
  }

  @GetMapping("/{id}/orders/{number}")
  public OrderDetails getOrderDetails(@PathVariable("id") Long id,
      @PathVariable("number") String orderNumber) {

    return accountService.getOrderDetails(id, orderNumber);
  }

  @GetMapping("/{id}/shipments")
  public Iterable<Shipment> getAllShipments(@PathVariable("id") Long id) {
    return accountService.getAllShipments(id);
  }
}
