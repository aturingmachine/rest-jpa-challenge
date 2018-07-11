package solstice.bootcamp.jpaapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import solstice.bootcamp.jpaapi.model.Shipment;

@Repository
public interface ShipmentRepository extends CrudRepository<Shipment, Long> {

  Iterable<Shipment> getAllByAccountIdAndShippingAddressId(Long accountId, Long addressId);

  Iterable<Shipment> getAllByAccountIdOrderByDeliveredDate(Long accountId);
}
