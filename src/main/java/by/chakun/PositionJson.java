package by.chakun;

//Position JSON:
//{"id": 1234, "symbol":"ACOR1 210416C00001000", "type": "short/long", "quantity": 12}
public class PositionJson {

    private Long id;
    private String symbol;
    private String type;
    private Integer quantity;

    public PositionJson() {
    }

    public PositionJson(String symbol, String type, Integer quantity) {
        this.symbol = symbol;
        this.type = type;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "PositionJson{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", type='" + type + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
