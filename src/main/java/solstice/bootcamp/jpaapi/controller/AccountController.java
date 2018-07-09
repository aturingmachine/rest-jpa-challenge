package solstice.bootcamp.jpaapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solstice.bootcamp.jpaapi.model.Account;
import solstice.bootcamp.jpaapi.repository.AccountRepository;

@RequestMapping("/accounts")
@RestController
public class AccountController {
  private AccountRepository accountRepository;

  public AccountController(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @GetMapping("")
  public Iterable<Account> getAll() {
    Iterable<Account> accounts = accountRepository.findAll();

    return accounts;
  }

  @PostMapping("")
  public ResponseEntity create(@RequestBody Account account) {
    account = accountRepository.save(account);

    return new ResponseEntity<>(account, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public Account getOne(@PathVariable("id") Long id) {
    return accountRepository.findById(id).get();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable("id") Long id) {
    accountRepository.deleteById(id);

    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/{id}")
  public Account update(@PathVariable("id") Long id, @RequestBody Account account) {
    Account accountToUpdate = accountRepository.getOne(id);
    accountToUpdate.setFirstName(account.getFirstName());
    accountToUpdate.setLastName(account.getLastName());
    accountToUpdate.setEmailAddress(account.getEmailAddress());

    return accountRepository.save(accountToUpdate);
  }
}
