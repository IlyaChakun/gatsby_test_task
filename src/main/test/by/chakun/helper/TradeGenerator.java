package by.chakun.helper;

import by.chakun.IdGenerator;
import by.chakun.PositionType;
import by.chakun.TradeJson;
import by.chakun.TradeJsonLeg;

import java.util.ArrayList;
import java.util.List;

public class TradeGenerator {


    public static TradeJson getSpreadTrade(String symbol, PositionType positionType, int quantity) {
        return doGetSimpleTrade(quantity, getLegs(symbol, positionType));
    }

    private static List<TradeJsonLeg> getLegs(String symbol, PositionType positionType) {
        List<TradeJsonLeg> tradeJsonLegs = new ArrayList<>();
        tradeJsonLegs.add(new TradeJsonLeg(symbol, positionType));
        return tradeJsonLegs;
    }


    public static TradeJson getSimpleTrade(int quantity, List<TradeJsonLeg> legs) {
        return doGetSimpleTrade(quantity, legs);
    }

    public static TradeJson getSimpleTrade(String symbol, int quantity) {
        return doGetSimpleTrade(quantity, getLegs(symbol));
    }

    private static List<TradeJsonLeg> getLegs(String symbol) {
        List<TradeJsonLeg> tradeJsonLegs = new ArrayList<>();
        tradeJsonLegs.add(new TradeJsonLeg(symbol, PositionType.LONG));

        return tradeJsonLegs;
    }


    private static TradeJson doGetSimpleTrade(int quantity, List<TradeJsonLeg> legs) {
        TradeJson tradeJson = new TradeJson();

        tradeJson.setId(IdGenerator.getRandomId());
        tradeJson.setQuantity(quantity);
        tradeJson.setLegs(legs);

        return tradeJson;
    }

}
