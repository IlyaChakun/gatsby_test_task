package by.chakun;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TradeProcessor {


    public static void doLogic() {

    }


    public static ApiResponse parseTradesAndPositions(List<PositionJson> positionJsonList,
                                                      List<TradeJson> tradeJsonList) {

        ApiResponse apiResponse = new ApiResponse();

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
            System.out.println("tradeJsonId:" + tradeJson.getId());
            System.out.println(tradeJson.getLegs());
        });

        tradeJsonList = tradeJsonList
                .stream()
                .sorted((object1, object2) -> object1.getLegs().size() > object2.getLegs().size() ? -1 : 1)
                .collect(Collectors.toList());

        System.out.println("after sort: ");
        tradeJsonList.forEach(tradeJson -> {
            System.out.println("tradeJsonId:" + tradeJson.getId());
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
            System.out.println("\n tradeJsonId=" + tradeJson.getId() + ", quantity=" + tradeJson.getQuantity() + ", legsCount=" + tradeJson.getLegs().size());


            int matchedLegsCount = 0;

            for (TradeJsonLeg tradeJsonLeg : tradeJson.getLegs()) {
                tradeJsonLeg.setLeftQuantity(tradeJson.getQuantity());

                System.out.println("tradeJsonLegSymbol:" + tradeJsonLeg.getSymbol());
                Long positionToRemove = null;

                for (PositionJson positionJson : positionJsonList) {
                    if (tradeJsonLeg.getType().equals(positionJson.getType())
                            && tradeJsonLeg.getSymbol().equals(positionJson.getSymbol())) {

                        final int tradeJsonQuantity = tradeJson.getQuantity() - positionJson.getQuantity();
                        //
                        System.out.println("positionSymbol=" + positionJson.getSymbol() + ", positionQuantity=" + positionJson.getQuantity());
                        System.out.println("tradeJsonQuantity=" + tradeJsonQuantity);

                        if (tradeJsonQuantity > 0) {//not enough quantity of position
                            System.out.println("not enough quantity from this position");
                            positionToRemove = positionJson.getId();
                            tradeJsonLeg.setLeftQuantity(tradeJsonQuantity);
                        } else if (tradeJsonQuantity < 0) {//too much quantity of position
                            System.out.println("too much quantity from this position, but trade is matched");
                            int newQuantity = tradeJsonQuantity * -1;
                            System.out.println("new quantity for position= " + newQuantity);
                            positionJson.setQuantity(newQuantity);
                            tradeJsonLeg.setLeftQuantity(0);
                        } else {//matched   (tradeJsonQuantity == 0)
                            System.out.println("trade leg is matched");
                            positionToRemove = positionJson.getId();
                            tradeJsonLeg.setLeftQuantity(tradeJsonQuantity);
                        }
                        break;
                    }
                }

                if (Objects.nonNull(positionToRemove)) {//delete used position
                    Long finalPositionToRemove = positionToRemove;
                    positionJsonList.removeIf(tradeJson2 -> tradeJson2.getId().equals(finalPositionToRemove));
                }
                if (tradeJsonLeg.getLeftQuantity() == 0) {
                    System.out.println("tradeJsonLegSymbol=" + tradeJsonLeg.getSymbol() + " is matched");
                    matchedLegsCount++;
                }
            }

            if (tradeJson.getLegs().size() == matchedLegsCount) {//matched trade

                System.out.println("legsCount=" + tradeJson.getLegs().size() + ",matchedLegsCount=" + matchedLegsCount);
                apiResponse.getMatchedTrades().add(tradeJson);
            } else {
                apiResponse.getNotMatchedTrades().add(tradeJson);
            }

        }

        apiResponse.setExtraPositions(
                positionJsonList.stream().filter(positionJson -> positionJson.getQuantity() > 0).collect(Collectors.toList())
        );

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

    private static boolean isTradeListEmpty(List<PositionJson> positionJsonList,
                                            List<TradeJson> tradeJsonList) {

        return tradeJsonList.isEmpty() && !positionJsonList.isEmpty();
    }
}
