package by.chakun;

import java.util.List;

//{"id": 4569, "quantity": 5, "legs":[{"symbol": "ACOR1 210416C00001000" , "type":"short/long"}]}
public class TradeJson {

    private Long id;
    private Integer quantity;
    private List<TradeJsonLeg> legs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<TradeJsonLeg> getLegs() {
        return legs;
    }

    public void setLegs(List<TradeJsonLeg> legs) {
        this.legs = legs;
    }



}
