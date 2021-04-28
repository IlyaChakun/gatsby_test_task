package by.chakun;

import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TradeJson {

    private Long id;
    private Integer quantity;
    private List<TradeJsonLeg> legs;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TradeJson)) return false;
        TradeJson tradeJson = (TradeJson) o;
        return Objects.equals(legs, tradeJson.legs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(legs);
    }


}
