package solstice.bootcamp.jpaapi.service;

import org.springframework.stereotype.Service;
import solstice.bootcamp.jpaapi.model.Account;
import solstice.bootcamp.jpaapi.model.OrderLineItem;
import solstice.bootcamp.jpaapi.model.Product;
import solstice.bootcamp.jpaapi.model.Shipment;
import solstice.bootcamp.jpaapi.repository.AccountRepository;
import solstice.bootcamp.jpaapi.repository.OrderLineItemRepository;
import solstice.bootcamp.jpaapi.repository.ProductRepository;
import solstice.bootcamp.jpaapi.repository.ShipmentRepository;

@Service
public class LineItemService {

  private OrderLineItemRepository orderLineItemRepository;
  private ShipmentRepository shipmentRepository;
  private ProductRepository productRepository;

  public LineItemService(OrderLineItemRepository orderLineItemRepository,
      ShipmentRepository shipmentRepository, ProductRepository productRepository) {

    this.orderLineItemRepository = orderLineItemRepository;
    this.shipmentRepository = shipmentRepository;
    this.productRepository = productRepository;
  }

  public Iterable<OrderLineItem> getAll(Long shipmentId) {
    return orderLineItemRepository.getAllByShipmentId(shipmentId);
  }

  public OrderLineItem getOne(Long itemId) {
    return orderLineItemRepository.findById(itemId).get();
  }

  public OrderLineItem create(Long shipmentId, Long productId, OrderLineItem item) {
    Shipment shipment = shipmentRepository.findById(shipmentId).get();
    Product product = productRepository.findById(productId).get();

    item.setShipment(shipment);
    item.setProduct(product);
    item.setPrice(product.getPrice());
    item.genTotalPrice();

    return orderLineItemRepository.save(item);
  }

  public OrderLineItem update(Long shipmentId, Long itemId, Long productId, OrderLineItem item) {
    Shipment shipment = shipmentRepository.findById(shipmentId).get();
    Product product = productRepository.findById(productId).get();
    OrderLineItem itemToUpdate = orderLineItemRepository.findById(itemId).get();

    itemToUpdate.setProduct(product);
    itemToUpdate.setShipment(shipment);
    itemToUpdate.setPrice(product.getPrice());
    itemToUpdate.setQuantity(item.getQuantity());
    itemToUpdate.genTotalPrice();

    return orderLineItemRepository.save(itemToUpdate);
  }

  public void delete(Long itemId) {
    orderLineItemRepository.deleteById(itemId);
  }
}
