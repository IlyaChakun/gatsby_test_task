package by.chakun;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
public class TradeJsonLeg {

    private boolean isMatched;
    private int leftQuantity;

    @NonNull
    private String symbol;
    @NonNull
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
