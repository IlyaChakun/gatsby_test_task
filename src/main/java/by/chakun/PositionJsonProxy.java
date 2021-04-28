package by.chakun;

import java.util.Objects;

public class PositionJsonProxy {

    private PositionJson positionJson;

    private final int quantity;

    private int receivedQuantity;
    private int actualQuantity;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PositionJsonProxy)) return false;
        PositionJsonProxy that = (PositionJsonProxy) o;
        return Objects.equals(positionJson, that.positionJson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionJson);
    }

    @Override
    public String toString() {
        return "PositionJsonProxy{" +
                "positionJson=" + positionJson.getSymbol() +
                ", quantity=" + quantity +
                ", receivedQuantity=" + receivedQuantity +
                ", actualQuantity=" + actualQuantity +
                '}';
    }
}
