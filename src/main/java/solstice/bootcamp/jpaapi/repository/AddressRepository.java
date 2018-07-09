package solstice.bootcamp.jpaapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import solstice.bootcamp.jpaapi.model.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
}
