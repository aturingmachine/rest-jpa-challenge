package solstice.bootcamp.jpaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import solstice.bootcamp.jpaapi.model.Address;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

  List<Address> findAllByAccountId(Long id);

}
