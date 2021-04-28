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

    private List<PositionJson> extraPositions = new ArrayList<>();//когда в позиции пришло но в трейде не было

    private List<PositionJson> missingPositions = new ArrayList<>();//когда в позиции не пришло но в трейде было

}
