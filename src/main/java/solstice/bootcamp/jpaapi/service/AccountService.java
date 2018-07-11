package solstice.bootcamp.jpaapi.service;

import org.springframework.stereotype.Service;
import solstice.bootcamp.jpaapi.model.*;
import solstice.bootcamp.jpaapi.repository.AccountRepository;
import solstice.bootcamp.jpaapi.repository.OrderLineItemRepository;
import solstice.bootcamp.jpaapi.repository.OrderRepository;
import solstice.bootcamp.jpaapi.repository.ShipmentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class AccountService {

  private AccountRepository accountRepository;
  private OrderRepository orderRepository;
  private OrderLineItemRepository lineItemRepository;
  private ShipmentRepository shipmentRepository;

  public AccountService(AccountRepository accountRepository, OrderRepository orderRepository,
      OrderLineItemRepository lineItemRepository, ShipmentRepository shipmentRepository) {

    this.accountRepository = accountRepository;
    this.orderRepository = orderRepository;
    this.lineItemRepository = lineItemRepository;
    this.shipmentRepository = shipmentRepository;
  }

  public Iterable<Account> getAll() {
    return accountRepository.findAll();
  }

  public Account save(Account account) {
    return accountRepository.save(account);
  }

  public Account getOne(Long id) {
    if (accountRepository.findById(id).isPresent()) {
      return accountRepository.findById(id).get();
    } else {
      throw new NoSuchElementException("Account with ID: " + id + " not found");
    }
  }

  public void delete(Long id) {
    if (accountRepository.findById(id).isPresent()) {
      accountRepository.deleteById(id);
    } else {
      throw new NoSuchElementException("Account with ID: " + id + " not found");
    }
  }

  public Account update(Long id, Account account) {
    Account accountToUpdate = accountRepository.getOne(id);
    accountToUpdate.setFirstName(account.getFirstName());
    accountToUpdate.setLastName(account.getLastName());
    accountToUpdate.setEmailAddress(account.getEmailAddress());

    return accountRepository.save(accountToUpdate);
  }

  public Iterable<Order> getAllOrders(Long id) {
    return orderRepository.findByAccountIdOrderByOrderDateAsc(id);
  }

  public OrderDetails getOrderDetails(Long id, String orderNumber) {
    Order order = orderRepository.findByOrderNumber(orderNumber);
    Iterable<OrderLineItem> items = lineItemRepository.getAllByShipmentIdOrOrderId(null, order.getId());
    OrderLineItem item = items.iterator().next();
    Shipment shipment = item.getShipment();
    OrderDetails details = new OrderDetails();

    details.setItems(items);
    details.setOrderNumber(order.getOrderNumber());
    details.setShippingAddress(shipment.getShippingAddress());
    ArrayList<Shipment> shipmentList = new ArrayList<>();
    items.forEach(i -> shipmentList.add(shipmentRepository.findById(i.getShipment().getId()).get()));
    details.setShipment(shipmentList);
    details.genTotalOrderPrice();

    return details;
  }

  public Iterable<Shipment> getAllShipments(Long id) {
    return shipmentRepository.getAllByAccountId(id);
  }
}
