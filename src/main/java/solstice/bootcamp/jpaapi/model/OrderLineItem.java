package solstice.bootcamp.jpaapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
  @JsonIgnoreProperties({"account", "shippingAddress", "orderLineItems"})
  private Shipment shipment;
  @ManyToOne
  @JoinColumn(name = "order_id")
  @JsonIgnoreProperties({"lineItems", "shippingAddress", "account"})
  private Order order;

  public double getPrice() {
    return this.product.getPrice();
  }

  public double getTotalPrice() {
    return this.product.getPrice() * this.getQuantity();
  }


  public void genTotalPrice() {
    this.totalPrice = this.price * this.quantity;
  }
}
