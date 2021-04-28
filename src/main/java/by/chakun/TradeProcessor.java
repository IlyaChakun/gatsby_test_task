package by.chakun;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TradeProcessor {

    public static ApiResponse parseTradesAndPositions(List<PositionJson> positionJsonList,
                                                      List<TradeJson> tradeJsonList) {
        final ApiResponse apiResponse = new ApiResponse();
        //if we have positions but do not have any trades => we EXTRA all positions
        if (isTradeListEmpty(positionJsonList, tradeJsonList)) {
            System.out.println("if we have positions but do not have any trades => we EXTRA all positions");
            apiResponse.setExtraPositions(positionJsonList);
            return apiResponse;
        }
        //if we have tradeJsons but do not have any positions => all  trades are NON MATCHED
        if (isPositionListEmpty(positionJsonList, tradeJsonList)) {
            System.out.println("if we have tradeJsons but do not have any positions => all  trades are NON MATCHED");
            apiResponse.setNotMatchedTrades(tradeJsonList);
            return apiResponse;
        }
        tradeJsonList = sortTradesByLegsCount(tradeJsonList);
        findMissingQuantity(positionJsonList, tradeJsonList, apiResponse);
        System.out.println(apiResponse);
        return apiResponse;
    }

    private static List<TradeJson> sortTradesByLegsCount(List<TradeJson> tradeJsonList) {
        System.out.println("trades with multiple legs must be first");
        System.out.println("before sorting: ");
        tradeJsonList.forEach(tradeJson -> {
            System.out.println("tradeJsonId:" + tradeJson.getId() + ", size=" + tradeJson.getLegs().size());
            System.out.println(tradeJson.getLegs());
        });

        tradeJsonList = tradeJsonList
                .stream()
                .sorted((object1, object2) -> object1.getLegs().size() > object2.getLegs().size() ? -1 : 1)
                .collect(Collectors.toList());

        System.out.println("after sort: ");
        tradeJsonList.forEach(tradeJson -> {
            System.out.println("tradeJsonId:" + tradeJson.getId() + ", size=" + tradeJson.getLegs().size());
            System.out.println(tradeJson.getLegs());
        });
        return tradeJsonList;
    }

    private static boolean isPositionListEmpty(List<PositionJson> positionJsonList,
                                               List<TradeJson> tradeJsonList) {
        return positionJsonList.isEmpty() && !tradeJsonList.isEmpty();
    }


    private static void findMissingQuantity(List<PositionJson> positionJsonList,
                                            List<TradeJson> tradeJsonList,
                                            ApiResponse apiResponse) {
        for (TradeJson tradeJson : tradeJsonList) {
            System.out.println("\n tradeJsonId=" + tradeJson.getId() +
                    ", quantity=" + tradeJson.getQuantity() +
                    ", legsCount=" + tradeJson.getLegs().size());
            int matchedLegsCount = 0;

            for (TradeJsonLeg tradeJsonLeg : tradeJson.getLegs()) {
                tradeJsonLeg.setLeftQuantity(tradeJson.getQuantity());
                System.out.println("tradeJsonLegSymbol:" + tradeJsonLeg.getSymbol());
                List<PositionJson> positionsToRemove = new ArrayList<>();

                determineIsTradeSpread(tradeJson, tradeJsonLeg);

                for (PositionJson positionJson : positionJsonList) {
                    System.out.println("positionSymbol=" + positionJson.getSymbol() +
                            ", positionType=" + positionJson.getType() +
                            ", positionQuantity=" + positionJson.getQuantity());
                    if (tradeJsonLeg.isSpreadTrade() && tradeJsonLeg.getSymbol().equals(positionJson.getSymbol())) {
                        if (tradeJsonLeg.isLongMatched() && tradeJsonLeg.isShortMatched()) {
                            break;
                        }
                        if (!tradeJsonLeg.isShortMatched() && positionJson.getType().equals(PositionType.SHORT)) {
                            doLegParsingForShortPositionType(tradeJsonLeg, positionsToRemove, positionJson);
                        } else if (!tradeJsonLeg.isLongMatched() && positionJson.getType().equals(PositionType.LONG)) {
                            doLegParsingForLongPositionType(tradeJsonLeg, positionsToRemove, positionJson);
                        }
                    } else if (tradeJsonLeg.getType().equals(positionJson.getType())
                            && tradeJsonLeg.getSymbol().equals(positionJson.getSymbol())) {
                        processSimpleTrade(tradeJson, tradeJsonLeg, positionsToRemove, positionJson);
                        break;
                    }
                }

                removeEmptyPositions(positionJsonList, positionsToRemove);
                matchedLegsCount = getMatchedLegsCount(matchedLegsCount, tradeJsonLeg);
                System.out.println("\n");
            }

            chooseIsTradeMatchedOrNotMatched(apiResponse, tradeJson, matchedLegsCount);
        }
        setExtraPositions(positionJsonList, apiResponse);
        setMissingPositions(apiResponse);
    }

    private static void setMissingPositions(ApiResponse apiResponse) {
        apiResponse.getNotMatchedTrades().forEach(nonMatchedTrade -> {

            for (TradeJsonLeg tradeJsonLeg : nonMatchedTrade.getLegs()) {
                PositionJson positionJson = new PositionJson();
                positionJson.setId(IdGenerator.getRandomId());
                positionJson.setQuantity(tradeJsonLeg.getLeftQuantity());
                positionJson.setSymbol(tradeJsonLeg.getSymbol());
                positionJson.setType(tradeJsonLeg.getType());

                apiResponse.getMissingPositions().add(positionJson);
            }
        });
    }

    private static void setExtraPositions(List<PositionJson> positionJsonList, ApiResponse apiResponse) {
        apiResponse.setExtraPositions(
                positionJsonList.stream().filter(positionJson -> positionJson.getQuantity() > 0).collect(Collectors.toList())
        );
    }

    private static void chooseIsTradeMatchedOrNotMatched(ApiResponse apiResponse, TradeJson tradeJson, int matchedLegsCount) {
        if (tradeJson.getLegs().size() == matchedLegsCount) {//matched trade
            System.out.println("legsCount=" + tradeJson.getLegs().size() + ",matchedLegsCount=" + matchedLegsCount);
            apiResponse.getMatchedTrades().add(tradeJson);
        } else {
            apiResponse.getNotMatchedTrades().add(tradeJson);
        }
    }

    private static void determineIsTradeSpread(TradeJson tradeJson, TradeJsonLeg tradeJsonLeg) {
        if (tradeJsonLeg.getType().equals(PositionType.SHORT)) {
            System.out.println("WE HAVE SPREAD TRADE");
            tradeJsonLeg.setSpreadTrade(true);
            tradeJsonLeg.setShortLeftQuantity(tradeJson.getQuantity());
            tradeJsonLeg.setLongLeftQuantity(tradeJson.getQuantity());
        }
    }

    private static void doLegParsingForShortPositionType(TradeJsonLeg tradeJsonLeg, List<PositionJson> positionsToRemove, PositionJson positionJson) {
        final int tradeJsonQuantity = tradeJsonLeg.getShortLeftQuantity() - positionJson.getQuantity();
        System.out.println("shortLeftQuantity=" + tradeJsonQuantity);


        if (tradeJsonQuantity > 0) {//not enough quantity of position
            System.out.println("not enough quantity from this position");
            positionsToRemove.add(positionJson);

            tradeJsonLeg.setShortLeftQuantity(tradeJsonQuantity);

        } else if (tradeJsonQuantity < 0) {//too much quantity of position
            System.out.println("too much quantity from this position, but trade is matched");
            int newQuantity = tradeJsonQuantity * -1;
            System.out.println("new quantity for position= " + newQuantity);
            positionJson.setQuantity(newQuantity);

            tradeJsonLeg.setShortMatched(true);
            tradeJsonLeg.setShortLeftQuantity(0);

        } else {//matched   (tradeJsonQuantity == 0)
            System.out.println("trade SHORT leg is matched");
            positionsToRemove.add(positionJson);
            tradeJsonLeg.setLeftQuantity(tradeJsonQuantity);

            tradeJsonLeg.setShortMatched(true);
            tradeJsonLeg.setShortLeftQuantity(0);
        }
    }

    private static void doLegParsingForLongPositionType(TradeJsonLeg tradeJsonLeg, List<PositionJson> positionsToRemove, PositionJson positionJson) {
        final int tradeJsonQuantity = tradeJsonLeg.getLongLeftQuantity() - positionJson.getQuantity();
        System.out.println("LongLeftQuantity=" + tradeJsonQuantity);
        if (tradeJsonQuantity > 0) {//not enough quantity of position
            System.out.println("not enough quantity from this position");
            positionsToRemove.add(positionJson);
            tradeJsonLeg.setLongLeftQuantity(tradeJsonQuantity);
        } else if (tradeJsonQuantity < 0) {//too much quantity of position
            System.out.println("too much quantity from this position, but trade is matched");
            int newQuantity = tradeJsonQuantity * -1;
            System.out.println("new quantity for position= " + newQuantity);
            positionJson.setQuantity(newQuantity);
            System.out.println("trade LONG leg is matched");
            tradeJsonLeg.setLongMatched(true);
            tradeJsonLeg.setLongLeftQuantity(0);
        } else {//matched   (tradeJsonQuantity == 0)
            System.out.println("trade LONG leg is matched");
            positionsToRemove.add(positionJson);
            tradeJsonLeg.setLeftQuantity(tradeJsonQuantity);
            tradeJsonLeg.setLongMatched(true);
            tradeJsonLeg.setLongLeftQuantity(0);
        }
    }

    private static int getMatchedLegsCount(int matchedLegsCount, TradeJsonLeg tradeJsonLeg) {
        if (tradeJsonLeg.isSpreadTrade() && tradeJsonLeg.isShortMatched() && tradeJsonLeg.isLongMatched()) {
            matchedLegsCount++;
        } else if (!tradeJsonLeg.isSpreadTrade() && tradeJsonLeg.getLeftQuantity() == 0) {
            System.out.println("tradeJsonLegSymbol=" + tradeJsonLeg.getSymbol() + " is matched");
            matchedLegsCount++;
        }
        return matchedLegsCount;
    }

    private static void removeEmptyPositions(List<PositionJson> positionJsonList, List<PositionJson> positionsToRemove) {
        if (!positionsToRemove.isEmpty()) {
            positionJsonList.removeAll(positionsToRemove);
        }
    }

    private static void processSimpleTrade(TradeJson tradeJson, TradeJsonLeg tradeJsonLeg, List<PositionJson> positionsToRemove, PositionJson positionJson) {
        final int tradeJsonQuantity = tradeJson.getQuantity() - positionJson.getQuantity();
        System.out.println("tradeJsonQuantity=" + tradeJsonQuantity);
        //

        if (tradeJsonQuantity > 0) {//not enough quantity of position
            System.out.println("not enough quantity from this position");
            positionsToRemove.add(positionJson);
            tradeJsonLeg.setLeftQuantity(tradeJsonQuantity);
        } else if (tradeJsonQuantity < 0) {//too much quantity of position
            System.out.println("too much quantity from this position, but trade is matched");
            int newQuantity = tradeJsonQuantity * -1;
            System.out.println("new quantity for position= " + newQuantity);
            positionJson.setQuantity(newQuantity);
            tradeJsonLeg.setLeftQuantity(0);
        } else {//matched   (tradeJsonQuantity == 0)
            System.out.println("trade leg is matched");
            positionsToRemove.add(positionJson);
            tradeJsonLeg.setLeftQuantity(tradeJsonQuantity);
        }
    }

    private static boolean isTradeListEmpty(List<PositionJson> positionJsonList,
                                            List<TradeJson> tradeJsonList) {

        return tradeJsonList.isEmpty() && !positionJsonList.isEmpty();
    }
}
