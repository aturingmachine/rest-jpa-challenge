package solstice.bootcamp.jpaapi.service;

import org.springframework.stereotype.Service;
import solstice.bootcamp.jpaapi.model.Account;
import solstice.bootcamp.jpaapi.model.Address;
import solstice.bootcamp.jpaapi.repository.AccountRepository;
import solstice.bootcamp.jpaapi.repository.AddressRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AddressService {

  private AddressRepository addressRepository;
  private AccountRepository accountRepository;

  public AddressService(AddressRepository addressRepository, AccountRepository accountRepository) {
    this.addressRepository = addressRepository;
    this.accountRepository = accountRepository;
  }

  public Address save(Address address, Long id) {
    Account account = accountRepository.findById(id).get();
    address.setAccount(account);
    address = addressRepository.save(address);
    return address;

  }

  public List<Address> getAll(Long id) {
    return addressRepository.findAllByAccountId(id);
  }

  public Address getOne(Long id) {
    Optional add = addressRepository.findById(id);

    if (add.isPresent()) {
      return (Address) add.get();
    } else {
      throw new NoSuchElementException("Address with ID: " + id + " not found");
    }
  }

  public Address update(Long id, Address address) {
    Address addressToUpdate = addressRepository.getOne(id);
    addressToUpdate.setBuilding(address.getBuilding());
    addressToUpdate.setCity(address.getCity());
    addressToUpdate.setCountry(address.getCountry());
    addressToUpdate.setState(address.getState());
    addressToUpdate.setStreet(address.getStreet());
    addressToUpdate.setZip(address.getZip());

    return addressRepository.save(addressToUpdate);
  }

  public void delete(Long addressId) {
    if (addressRepository.findById(addressId).isPresent()) {
      addressRepository.deleteById(addressId);
    } else {
      throw new NoSuchElementException("Address with ID: " + addressId + " not found");
    }
  }
}
