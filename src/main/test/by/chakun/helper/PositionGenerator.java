package by.chakun.helper;

import by.chakun.IdGenerator;
import by.chakun.PositionJson;
import by.chakun.PositionType;

public class PositionGenerator {


    public static PositionJson getPosition(String symbol, PositionType positionType, int quantity) {
        PositionJson positionJson = new PositionJson();

        positionJson.setId(IdGenerator.getRandomId());
        positionJson.setQuantity(quantity);
        positionJson.setSymbol(symbol);
        positionJson.setType(positionType);

        return positionJson;
    }

}
