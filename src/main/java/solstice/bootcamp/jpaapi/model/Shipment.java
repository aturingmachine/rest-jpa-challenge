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
public class Shipment {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String shippedDate;
  private String deliveredDate;
  @ManyToOne
  @JoinColumn(name = "account_id")
  private Account account;
  @ManyToOne
  @JoinColumn(name = "address_id")
  private Address shippingAddress;
  @OneToMany
  @JoinColumn(name = "order_line_item_id")
  private Set<OrderLineItem> orderLineItems;
}
