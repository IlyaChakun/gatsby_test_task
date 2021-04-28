package by.chakun;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ApiResponse {

    private List<TradeJson> matchedTrades = new ArrayList<>();

    private List<TradeJson> notMatchedTrades = new ArrayList<>();

    private List<PositionJson> extraPositions = new ArrayList<>();//when it came in position but was not in the trade

    private List<PositionJson> missingPositions = new ArrayList<>();//when it didn’t come in position but there was

}
