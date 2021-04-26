package by.chakun;

public class PositionJsonProxy {


    public PositionJsonProxy(int actualQuantity,
                             int receivedQuantity) {
        this.receivedQuantity = receivedQuantity;
        this.actualQuantity = actualQuantity;
        this.quantity = actualQuantity - receivedQuantity;
    }

    public PositionJsonProxy(PositionJson positionJson,
                             int actualQuantity) {
        this(actualQuantity, 0);
        this.positionJson = positionJson;
    }

    public PositionJsonProxy(PositionJson positionJson,
                             int actualQuantity,
                             int receivedQuantity) {
        this(actualQuantity, receivedQuantity);
        this.positionJson = positionJson;
    }

    private PositionJson positionJson;

    private int quantity;

    private int receivedQuantity;
    private int actualQuantity;


    public int getReceivedQuantity() {
        return receivedQuantity;
    }

    public void setReceivedQuantity(int receivedQuantity) {
        this.receivedQuantity = receivedQuantity;
    }

    public int getActualQuantity() {
        return actualQuantity;
    }

    public void setActualQuantity(int actualQuantity) {
        this.actualQuantity = actualQuantity;
    }

    public PositionJson getPositionJson() {
        return positionJson;
    }

    public void setPositionJson(PositionJson positionJson) {
        this.positionJson = positionJson;
    }

    public int getQuantity() {
        return quantity;
    }


    @Override
    public String toString() {
        return "PositionJsonProxy{" +
                "positionJson=" + positionJson +
                ", quantity=" + quantity +
                ", receivedQuantity=" + receivedQuantity +
                ", actualQuantity=" + actualQuantity +
                '}';
    }
}
