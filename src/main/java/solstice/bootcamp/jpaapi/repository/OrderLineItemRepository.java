package solstice.bootcamp.jpaapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import solstice.bootcamp.jpaapi.model.OrderLineItem;

import javax.transaction.Transactional;

@Repository
public interface OrderLineItemRepository extends CrudRepository<OrderLineItem, Long> {

  Iterable<OrderLineItem> getAllByShipmentIdOrOrderId(Long shipmentId, Long orderId);

  @Transactional
  void deleteByShipmentIdOrOrderIdAndId(Long shipmentId, Long oderId, Long id);
}
