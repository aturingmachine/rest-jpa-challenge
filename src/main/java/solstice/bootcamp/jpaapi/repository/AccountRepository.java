package solstice.bootcamp.jpaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import solstice.bootcamp.jpaapi.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
