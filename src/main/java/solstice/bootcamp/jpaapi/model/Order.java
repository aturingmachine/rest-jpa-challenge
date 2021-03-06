package solstice.bootcamp.jpaapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "orders", uniqueConstraints = {
    @UniqueConstraint(name = "order_number", columnNames = "orderNumber")
})
@Getter
@Setter
@NoArgsConstructor
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  @Column(unique = true)
  private String orderNumber;
  private String orderDate;
  private double total;
  @ManyToOne
  @JoinColumn(name = "account_id")
  private Account account;
  @ManyToOne
  @JoinColumn(name = "address_id")
  private Address shippingAddress;
  @OneToMany
  @JoinColumn(name = "order_id")
  @JsonIgnoreProperties({"order", "shipment"})
  private Set<OrderLineItem> lineItems;

  public void generateTotal() {
    if (this.lineItems != null) {
      this.lineItems.forEach(item -> this.total += item.getPrice() * item.getQuantity());
    }
  }
}
