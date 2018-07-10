package solstice.bootcamp.jpaapi.service;

import org.springframework.stereotype.Service;
import solstice.bootcamp.jpaapi.model.Account;
import solstice.bootcamp.jpaapi.model.Address;
import solstice.bootcamp.jpaapi.model.Order;
import solstice.bootcamp.jpaapi.repository.AccountRepository;
import solstice.bootcamp.jpaapi.repository.AddressRepository;
import solstice.bootcamp.jpaapi.repository.OrderRepository;

@Service
public class OrderService {

  private OrderRepository orderRepository;
  private AccountRepository accountRepository;
  private AddressRepository addressRepository;

  public OrderService(OrderRepository orderRepository, AccountRepository accountRepository, AddressRepository addressRepository) {
    this.orderRepository = orderRepository;
    this.accountRepository = accountRepository;
    this.addressRepository = addressRepository;
  }

  //@TODO this
  public Order create(Long accountId, Long addressId, Order order) {
    Account account = accountRepository.findById(accountId).get();
    Address address = addressRepository.findById(addressId).get();

    order.setAccount(account);
    order.setShippingAddress(address);
    order.generateTotal();

    return orderRepository.save(order);
  }

  public Iterable<Order> getAll(Long accountId, Long addressId) {
    return orderRepository.findByAccountIdAndShippingAddressId(accountId, addressId);
  }

  public Order getOne(Long orderId) {
    return orderRepository.findById(orderId).get();
  }

  public void delete(Long id) {
    orderRepository.deleteById(id);
  }

  public Order update(Long accountId, Long addressId, Long orderId, Order order) {

    Account account = accountRepository.findById(accountId).get();
    Address address = addressRepository.findById(addressId).get();
    Order orderToUpdate = orderRepository.findById(orderId).get();

    orderToUpdate.setAccount(account);
    orderToUpdate.setShippingAddress(address);
    orderToUpdate.setOrderDate(order.getOrderDate());
    orderToUpdate.setOrderNumber(order.getOrderNumber());
    orderToUpdate.generateTotal();

    return orderRepository.save(orderToUpdate);
  }
}
