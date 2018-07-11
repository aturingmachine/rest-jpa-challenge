package solstice.bootcamp.jpaapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(uniqueConstraints = {
    @UniqueConstraint(name = "emailAddress", columnNames = "emailAddress")
})
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String firstName;
  private String lastName;
  private String emailAddress;
  @OneToMany
  @JoinColumn(name = "account_id")
//  @JsonManagedReference
  private Set<Address> addresses;

  public Account(String firstName, String lastName, String emailAddress) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.emailAddress = emailAddress;
  }
}
