package solstice.bootcamp.jpaapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String street;
  private String building;
  private String city;
  private String state;
  private String zip;
  private String country;
  @ManyToOne
  private Account account;
}
