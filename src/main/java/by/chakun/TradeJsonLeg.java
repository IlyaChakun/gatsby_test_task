package by.chakun;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class TradeJsonLeg {

    private String symbol;
    private PositionType type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TradeJsonLeg)) return false;
        TradeJsonLeg that = (TradeJsonLeg) o;
        return Objects.equals(symbol, that.symbol) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, type);
    }
}
