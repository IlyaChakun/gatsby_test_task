package by.chakun;

public class TradeJsonLeg {

    //{"symbol": "ACOR1 210416C00001000" , "type":"short/long"}

    private String symbol;
    private String type;


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
}
