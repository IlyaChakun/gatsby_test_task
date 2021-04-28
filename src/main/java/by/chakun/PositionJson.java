package by.chakun;

import lombok.*;

import java.util.Objects;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PositionJson {

    private Long id;
    private String symbol;
    private PositionType type;
    private int quantity;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PositionJson)) return false;
        PositionJson that = (PositionJson) o;
        return Objects.equals(symbol, that.symbol) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, type);
    }

}
