package solstice.bootcamp.jpaapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class OrderDetails {

  private String orderNumber;
  private Address shippingAddress;
  private double totalPrice;
  private Iterable<OrderLineItem> items;
  @JsonIgnoreProperties({"account", "shippingAddress", "orderLineItems"})
  private ArrayList<Shipment> shipment;

  public void genTotalOrderPrice() {
    this.items.forEach(item -> this.totalPrice += item.getTotalPrice());
  }
}
