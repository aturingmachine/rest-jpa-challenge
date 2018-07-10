package solstice.bootcamp.jpaapi.service;

import org.springframework.stereotype.Service;
import solstice.bootcamp.jpaapi.model.Account;
import solstice.bootcamp.jpaapi.model.Address;
import solstice.bootcamp.jpaapi.model.Shipment;
import solstice.bootcamp.jpaapi.repository.AccountRepository;
import solstice.bootcamp.jpaapi.repository.AddressRepository;
import solstice.bootcamp.jpaapi.repository.ShipmentRepository;

@Service
public class ShipmentService {

  private ShipmentRepository shipmentRepository;
  private AddressRepository addressRepository;
  private AccountRepository accountRepository;

  public ShipmentService(ShipmentRepository shipmentRepository, AddressRepository addressRepository, AccountRepository accountRepository) {
    this.shipmentRepository = shipmentRepository;
    this.addressRepository = addressRepository;
    this.accountRepository = accountRepository;
  }

  public Iterable<Shipment> getAll(Long accountId, Long addressId) {
    return shipmentRepository.getAllByAccountIdAndShippingAddressId(accountId, addressId);
  }

  public Shipment getOne(Long id) {
    return shipmentRepository.findById(id).get();
  }

  public Shipment create(Long accountId, Long addressId, Shipment shipment) {
    Account account = accountRepository.findById(accountId).get();
    Address address = addressRepository.findById(addressId).get();
    shipment.setAccount(account);
    shipment.setShippingAddress(address);
    return shipmentRepository.save(shipment);
  }

  public Shipment update(Long id, Shipment shipment) {
    Shipment shipmentToUpdate = shipmentRepository.findById(id).get();
    shipmentToUpdate.setShippedDate(shipment.getShippedDate());
    shipmentToUpdate.setDeliveredDate(shipment.getDeliveredDate());

    return shipmentRepository.save(shipmentToUpdate);
  }
}
