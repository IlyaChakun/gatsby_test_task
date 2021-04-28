package by.chakun.helper;

import by.chakun.PositionType;
import by.chakun.TradeJson;
import by.chakun.TradeJsonLeg;

import java.util.ArrayList;
import java.util.List;

public class TradeGenerator {


    public static TradeJson getSpreadTrade(String symbol, PositionType positionType, int quantity) {
        TradeJson tradeJson = new TradeJson();

        tradeJson.setId(IdGenerator.getRandomId());
        tradeJson.setQuantity(quantity);
        tradeJson.setLegs(getLegs(symbol, positionType));

        return tradeJson;
    }

    private static List<TradeJsonLeg> getLegs(String symbol, PositionType positionType) {
        List<TradeJsonLeg> tradeJsonLegs = new ArrayList<>();
        tradeJsonLegs.add(new TradeJsonLeg(symbol, positionType));
        return tradeJsonLegs;
    }


    public static TradeJson getSimpleTrade(String symbol, int quantity) {
        TradeJson tradeJson = new TradeJson();

        tradeJson.setId(IdGenerator.getRandomId());
        tradeJson.setQuantity(quantity);
        tradeJson.setLegs(getLegs(symbol));

        return tradeJson;
    }

    private static List<TradeJsonLeg> getLegs(String symbol) {
        List<TradeJsonLeg> tradeJsonLegs = new ArrayList<>();
        tradeJsonLegs.add(new TradeJsonLeg(symbol, PositionType.LONG));
        return tradeJsonLegs;
    }
}
