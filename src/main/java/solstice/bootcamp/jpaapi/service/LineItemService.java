package solstice.bootcamp.jpaapi.service;

import org.springframework.stereotype.Service;
import solstice.bootcamp.jpaapi.model.Order;
import solstice.bootcamp.jpaapi.model.OrderLineItem;
import solstice.bootcamp.jpaapi.model.Product;
import solstice.bootcamp.jpaapi.model.Shipment;
import solstice.bootcamp.jpaapi.repository.*;

import java.util.Optional;

@Service
public class LineItemService {

  private OrderLineItemRepository orderLineItemRepository;
  private ShipmentRepository shipmentRepository;
  private ProductRepository productRepository;
  private OrderRepository orderRepository;

  public LineItemService(OrderLineItemRepository orderLineItemRepository,
      ShipmentRepository shipmentRepository, ProductRepository productRepository, OrderRepository orderRepository) {

    this.orderLineItemRepository = orderLineItemRepository;
    this.shipmentRepository = shipmentRepository;
    this.productRepository = productRepository;
    this.orderRepository = orderRepository;
  }

  public Iterable<OrderLineItem> getAll(Long shipmentId, Long orderId) {
    return orderLineItemRepository.getAllByShipmentIdOrOrderId(shipmentId, orderId);
  }

  public OrderLineItem getOne(Long itemId) {
    if (orderLineItemRepository.findById(itemId).isPresent()) {
      return orderLineItemRepository.findById(itemId).get();
    } else {
      return null;
    }
  }

  public OrderLineItem create(Long shipmentId, Long productId, Long orderId, OrderLineItem item) {
    Optional<Shipment> shipment = shipmentRepository.findById(shipmentId);
    Optional<Order> order = orderRepository.findById(orderId);
    Product product = productRepository.findById(productId).get();

    item.setShipment(shipment.orElse(null));
    item.setOrder(order.orElse(null));
    item.setProduct(product);
    item.setPrice(product.getPrice());
    item.genTotalPrice();

    return orderLineItemRepository.save(item);
  }

  public OrderLineItem update(Long shipmentId, Long itemId, Long productId, Long orderId, OrderLineItem item) {
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
