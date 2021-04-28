package by.chakun;

import java.util.List;
import java.util.Objects;

public class TradeProcessor {


    public static void doLogic() {

    }


    public static ApiResponse parseTradesAndPositions(List<PositionJson> positionJsonList,
                                                      List<TradeJson> tradeJsonList) {

        ApiResponse apiResponse = new ApiResponse();

        //if we have positions but do not have any trades => we EXTRA all positions
        if (isTradeListEmpty(positionJsonList, tradeJsonList)) {
            System.out.println("if we have positions but do not have any trades => we EXTRA all positions");
//            apiResponse.setExtraPositions(
//                    positionJsonList.stream()
//                            .map(positionJson -> new PositionJsonProxy(positionJson, positionJson.getQuantity()))
//                            .collect(Collectors.toList())
//            );
            apiResponse.setExtraPositions(positionJsonList);
            return apiResponse;
        }

        //if we have tradeJsons but do not have any positions => all  trades are NON MATCHED
        if (isPositionListEmpty(positionJsonList, tradeJsonList)) {
            System.out.println("if we have tradeJsons but do not have any positions => all  trades are NON MATCHED");
            apiResponse.setNotMatchedTrades(tradeJsonList);
            return apiResponse;
        }


        findMissingQuantity(positionJsonList, tradeJsonList, apiResponse);

        System.out.println(apiResponse);

        return apiResponse;
    }

    private static boolean isPositionListEmpty(List<PositionJson> positionJsonList,
                                               List<TradeJson> tradeJsonList) {
        return positionJsonList.isEmpty() && !tradeJsonList.isEmpty();
    }


    private static void findMissingQuantity(List<PositionJson> positionJsonList,
                                            List<TradeJson> tradeJsonList,
                                            ApiResponse apiResponse) {

        //position
        //сортируем по symbol
        //сортируем по quantity

        //trade
        //сортируем сначала по количеству legs
        //потом сортируем по Symbol
        //потом сортируем по quantity
        //missing

        // МОЯ ЛОГИКА: ИЩЕМ ВСЕ TRADE С ОДИНАКОВЫЙ SYMBOL И СКЛАДЫВАЕМ ИХ QUANTITY
        // ИЩЕМ СОВПАДАЮЩИЙ POSITION -> ЕСЛИ ЕСТЬ ТО MATCHED, ЕСЛИ НЕТ ->

        //List<TradeJson> workingTradeJsonList = wo
        for (TradeJson tradeJson : tradeJsonList) {
            System.out.println("tradeJsonId=" + tradeJson.getId() + ", quantity=" + tradeJson.getQuantity() + ", legsCount=" + tradeJson.getLegs().size());

            for (TradeJsonLeg tradeJsonLeg : tradeJson.getLegs()) {

                Long positionToRemove = null;

                for (PositionJson positionJson : positionJsonList) {
                    if (tradeJsonLeg.getType().equals(positionJson.getType())
                            && tradeJsonLeg.getSymbol().equals(positionJson.getSymbol())) {

                        //тут ндао отнять от трейнда количество и отнтяь от позиции и сдачу отсавтиь на след раз
                        //
                        // if (positionJson.getQuantity() >= tradeJson.getQuantity()) {

                        tradeJson.setQuantity(
                                tradeJson.getQuantity() - positionJson.getQuantity()
                        );
                        //      }
                        //
                        positionToRemove = positionJson.getId();

                        apiResponse.getMatchedTrades().add(tradeJson);

                        break;
                    }
                }

                if (Objects.nonNull(positionToRemove)) {
                    Long finalPositionToRemove = positionToRemove;
                    positionJsonList.removeIf(tradeJson2 -> tradeJson2.getId().equals(finalPositionToRemove));
                }
                //validate trade here
            }

            if (tradeJson.getQuantity() == 0) {
                apiResponse.getMatchedTrades().add(tradeJson);
            }

//            for (TradeJsonLeg tradeJsonLeg : tradeJson.getLegs()) {
//
//                if (tradeJsonLeg.getType().equals(PositionType.LONG)) {
//                    System.out.println("process base trade with type Long");
//                    for (PositionJson positionJson : positionJsonList) {
//                        if (tradeJsonLeg.getType().equals(positionJson.getType())
//                                && tradeJsonLeg.getSymbol().equals(positionJson.getSymbol())) {
//
//                            if (positionJson.getQuantity() == tradeJson.getQuantity()) {
//                                apiResponse.getMatchedTrades().add(tradeJson);
//                                break;
//                            } else if (positionJson.getQuantity() > tradeJson.getQuantity()) {
//                                apiResponse.getMatchedTrades().add(tradeJson);
//                                positionJson.setQuantity(positionJson.getQuantity() - tradeJson.getQuantity());
//                                apiResponse.getExtraPositions().add(positionJson);
//                            }
//                        }
//                    }
//                } else {//spread
//                    System.out.println("process spread trade with type short");
//                    for (PositionJson positionJson : positionJsonList) {//we need long  = quantity trade
//                        if (
//                                (
//                                        PositionType.LONG.equals(positionJson.getType())
//                                && tradeJsonLeg.getSymbol().equals(positionJson.getSymbol())
//                                )
//                                ||
//                                        (
//                                                PositionType.SHORT.equals(positionJson.getType())
//                                                        && tradeJsonLeg.getSymbol().equals(positionJson.getSymbol())
//                                                )
//                        )
//                        {
//
//                            if (positionJson.getQuantity() == tradeJson.getQuantity()) {
//                                apiResponse.getMatchedTrades().add(tradeJson);
//                                break;
//                            } else if (positionJson.getQuantity() > tradeJson.getQuantity()) {
//                                apiResponse.getMatchedTrades().add(tradeJson);
//                                positionJson.setQuantity(positionJson.getQuantity() - tradeJson.getQuantity());
//                                apiResponse.getExtraPositions().add(positionJson);
//                            }
//
//                        }
//                    }
////                    for (PositionJson positionJson : positionJsonList) {
////                        if (PositionType.SHORT.equals(positionJson.getType())
////                                && tradeJsonLeg.getSymbol().equals(positionJson.getSymbol())) {
////
////
////                        }
////                    }
//                }
//            }
        }


//        for (PositionJson positionJson : positionJsonList) {
//
//            int positionQuantity = positionJson.getQuantity();
//            int tradeQuantity = 0;
//            boolean tradePresented = false;
//
//            //checks is position json present
//            //add legs calculation!!
//            //FULL
//            for (TradeJson tradeJson : tradeJsonList) {
//                for (TradeJsonLeg tradeJsonLeg : tradeJson.getLegs()) {
//                    if (positionJson.getType().equals(tradeJsonLeg.getType())
//                            && positionJson.getSymbol().equals(tradeJsonLeg.getSymbol())) {
//                        tradeQuantity += tradeJson.getQuantity();
//
//                        if (!tradePresented) {
//                            tradePresented = true;
//                        }
//                    }
//                }
//            }
//
//
//            System.out.println("actualQuantity=" + positionQuantity + ", receivedQuantity=" + tradeQuantity);
//
//            if (positionQuantity > tradeQuantity) {//is missing
//                System.out.println("missing:  " + positionJson.getSymbol() + " " + "actualQuantity=" + positionQuantity + ", receivedQuantity=" + tradeQuantity);
//                addPositionToMissingList(apiResponse, positionJson, positionQuantity, tradeQuantity);
//            } else if (
//                    (positionQuantity < tradeQuantity) && tradePresented // if trade have more quantity than position but trade presented ) {
//            ) {
//                System.out.println("extra cause trade have bigger quantity than position");
//                System.out.println("position=" + positionJson.getSymbol() + "" +
//                        ", actualQuantity=" + positionQuantity + "   receivedQuantity=" + tradeQuantity);
//                addPositionToExtraList(apiResponse, positionJson, positionJson.getQuantity());
//            }
//
//            if (positionQuantity != tradeQuantity
//                    && !apiResponse
//                    .getExtraPositions().contains(new PositionJsonProxy(positionJson, positionJson.getQuantity()))
//                    && !apiResponse
//                    .getMissingPositions().contains(new PositionJsonProxy(positionJson, positionJson.getQuantity()))
//            ) {
//                System.out.println("extra:  " + positionJson.getSymbol() + " " + "actualQuantity=" + positionQuantity + ", receivedQuantity=" + tradeQuantity);
//                addPositionToExtraList(apiResponse, positionJson, positionJson.getQuantity());
//            }
//
//        }
//
//        for (TradeJson tradeJson : tradeJsonList) {
//            boolean positionPresented = false;
//
//            for (PositionJson positionJson : positionJsonList) {
//                for (TradeJsonLeg tradeJsonLeg : tradeJson.getLegs()) {
//                    if (positionJson.getType().equals(tradeJsonLeg.getType())
//                            && positionJson.getSymbol().equals(tradeJsonLeg.getSymbol())) {
//                        if (!positionPresented) {
//                            positionPresented = true;
//                            break;
//                        }
//                    }
//                }
//            }
//
//        }

    }

//    private static void addPositionToExtraList(ApiResponse apiResponse, PositionJson positionJson, Integer quantity) {
//        apiResponse
//                .getExtraPositions()
//                .add(
//                        new PositionJsonProxy(positionJson, quantity)
//                );
//    }
//
//    private static void addPositionToMissingList(ApiResponse apiResponse, PositionJson positionJson, int actualQuantity, int receivedQuantity) {
//        apiResponse.getMissingPositions().add(
//                new PositionJsonProxy(
//                        positionJson,
//                        actualQuantity,
//                        receivedQuantity
//                )
//        );
//    }

    private static boolean isTradeListEmpty(List<PositionJson> positionJsonList,
                                            List<TradeJson> tradeJsonList) {

        return tradeJsonList.isEmpty() && !positionJsonList.isEmpty();
    }
}
