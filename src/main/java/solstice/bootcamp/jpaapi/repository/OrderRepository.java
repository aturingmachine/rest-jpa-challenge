package solstice.bootcamp.jpaapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import solstice.bootcamp.jpaapi.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

  Iterable<Order> findByAccountIdAndShippingAddressId(Long accountId, Long addressId);
}
