package solstice.bootcamp.jpaapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderLineItem {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private int quantity;
  private double price;
  private double totalPrice;
  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;
  @ManyToOne
  @JoinColumn(name = "shipment_id")
  private Shipment shipment;


  public void genTotalPrice() {
    this.totalPrice = this.price * this.quantity;
  }
}
