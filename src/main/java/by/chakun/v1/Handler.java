//package by.chakun.v1;
//
//import by.chakun.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class Handler {
//
//
//    public static void doLogic() {
//
//        List<PositionJson> positionJsonList = getPositionJsons();
//        List<TradeJson> tradeJsonList = getTradeJsons();
//
//        parseTradesAndPositions(positionJsonList, tradeJsonList);
//
//    }
//
//    private static List<TradeJson> getTradeJsons() {
//        List<TradeJson> tradeJsonList = new ArrayList<>();
//
//
//        TradeJson tradeJson1 = new TradeJson();
//        tradeJson1.setId(1235L);
//        tradeJson1.setQuantity(7);//5
//        List<TradeJsonLeg> tradeJsonLegs = new ArrayList<>();
//        TradeJsonLeg tradeJsonLeg = new TradeJsonLeg();
//        tradeJsonLeg.setSymbol("ACOR1 210416C00001000");
//        tradeJsonLeg.setType("short");//short/long
//        tradeJsonLegs.add(tradeJsonLeg);
//        tradeJson1.setLegs(tradeJsonLegs);
//
//
//        TradeJson tradeJson2 = new TradeJson();
//        tradeJson2.setId(1236L);
//        tradeJson2.setQuantity(5);//5
//        List<TradeJsonLeg> tradeJsonLegs2 = new ArrayList<>();
//        TradeJsonLeg tradeJsonLeg2 = new TradeJsonLeg();
//        tradeJsonLeg2.setSymbol("ACOR2 210416C00001000");
//        tradeJsonLeg2.setType("long");//short/long
//        tradeJsonLegs2.add(tradeJsonLeg2);
//        tradeJson2.setLegs(tradeJsonLegs2);
//
//        tradeJsonList.add(tradeJson1);
//        tradeJsonList.add(tradeJson2);
//
//        return tradeJsonList;
//    }
//
//
//    private static List<PositionJson> getPositionJsons() {
//        List<PositionJson> positionJsonList = new ArrayList<>();
//        PositionJson positionJson = new PositionJson();
//        positionJson.setId(1234L);
//        positionJson.setSymbol("ACOR1 210416C00001000");
//        positionJson.setType("short");//short/long
//        positionJson.setQuantity(12);
//        positionJsonList.add(positionJson);
//        return positionJsonList;
//    }
//
//
//    public static ApiResponse parseTradesAndPositions(List<PositionJson> positionJsonList,
//                                                      List<TradeJson> tradeJsonList) {
//        //{"id": 1234, "symbol":"ACOR1 210416C00001000", "type": "short/long", "quantity": 12}//12
//        //
//        //Trade JSON:
//        //{"id": 4569, "quantity": 5, "legs":[{"symbol": "ACOR1 210416C00001000" , "type":"short/long"}]}
//        //{"id": 4567, "quantity": 7, "legs":[{"symbol": "ACOR1 210416C00001000" , "type":"short/long"}]}
//        //{"id": 4568, "quantity": 10, "legs":[{"symbol": "ACOR2 210416C00001000" , "type":"long"}]}
//
//        ApiResponse apiResponse = new ApiResponse();
//
//        //if we have positions but do not have any trades => we missed all positions
//        if (isTradeListEmpty(positionJsonList, tradeJsonList)) {
//            System.out.println("we have positions but do not have any trades => we missed all positions");
//            apiResponse.setMissingPositions(
//                    positionJsonList.stream()
//                            .map(positionJson -> new PositionJsonProxy(positionJson, positionJson.getQuantity()))
//                            .collect(Collectors.toList())
//            );
//            return apiResponse;
//        }
//
//        //if we have tradeJsons but do not have any positions => all extra
//        if (isPositionListEmpty(positionJsonList, tradeJsonList)) {
//            System.out.println("we have tradeJsons but do not have any positions => all extra");
//
//            tradeJsonList.forEach(tradeJson ->
//                    tradeJson.getLegs().forEach(tradeJsonLeg -> {
//                        addPositionToExtraList(apiResponse, new PositionJson(tradeJsonLeg.getSymbol(),
//                                tradeJsonLeg.getType(),
//                                tradeJson.getQuantity()), tradeJson.getQuantity());
//                    }));
//            return apiResponse;
//        }
//
//        findMissingQuantity(positionJsonList, tradeJsonList, apiResponse);
//        ///findExtraQuantity(positionJsonList, tradeJsonList, apiResponse);
//
//        System.out.println("final = " + apiResponse);
//
//        System.out.println("extra count = " + apiResponse.getExtraPositions().size());
//        System.out.println("extra = " + apiResponse.getExtraPositions());
//        System.out.println("\n");
//        System.out.println("missing count = " + apiResponse.getMissingPositions().size());
//        System.out.println("missing = " + apiResponse.getMissingPositions());
//        System.out.println("\n");
//        return apiResponse;
//    }
//
//    private static boolean isPositionListEmpty(List<PositionJson> positionJsonList,
//                                               List<TradeJson> tradeJsonList) {
//        return positionJsonList.isEmpty() && !tradeJsonList.isEmpty();
//    }
//
//    private static void findExtraQuantity(List<PositionJson> positionJsonList, List<TradeJson> tradeJsonList, ApiResponse apiResponse) {
//        List<TradeJson> filteredTradeListWithoutMissingTrades = getFilteredTradeListWithoutMissingTrades(tradeJsonList, apiResponse);
//
//        System.out.println("go to find exta new tradeList size = " + filteredTradeListWithoutMissingTrades.size());
//        System.out.println("\n\n");
//        for (TradeJson tradeJson : filteredTradeListWithoutMissingTrades) {
//            boolean takeNextTrade = false;
//            for (TradeJsonLeg tradeJsonLeg : tradeJson.getLegs()) {
//
//                if (takeNextTrade) {
//                    break;
//                }
//                for (PositionJson positionJson : positionJsonList) {
//
//                    System.out.println("positionJson symbol=" + positionJson.getSymbol() + ", type=" + positionJson.getType());
//                    System.out.println("tradeJsonLeg symbol=" + tradeJsonLeg.getSymbol() + ", type=" + tradeJsonLeg.getType());
//
//                    if (!apiResponse
//                            .getExtraPositions().contains(new PositionJsonProxy(positionJson, positionJson.getQuantity()))
//                            && (
//                            !positionJson.getType().equals(tradeJsonLeg.getType())
//                                    || !positionJson.getSymbol().equals(tradeJsonLeg.getSymbol())
//                    )
//                    ) {
//                        addPositionToExtraList(apiResponse, positionJson, positionJson.getQuantity());
//                        takeNextTrade = true;
//                        break;
//                    }
//                }
//            }
//        }
//    }
//
//    private static List<TradeJson> getFilteredTradeListWithoutMissingTrades(List<TradeJson> tradeJsonList, ApiResponse apiResponse) {
//        List<TradeJson> filteredTradeListWithoutMissingTrades;
//
//        System.out.println("in filtered: " + tradeJsonList.size());
//
//        if (!apiResponse.getMissingPositions().isEmpty()) {
//
//            System.out.println("\ncreate trade list with sorting with missing list");
//
//            filteredTradeListWithoutMissingTrades = new ArrayList<>();
//
////            for (TradeJson tradeJson : tradeJsonList) {
////                boolean takeNextTrade = false;
////
////                for (TradeJsonLeg tradeJsonLeg : tradeJson.getLegs()) {
////                    if (takeNextTrade) {
////                        break;
////                    }
////                    for (PositionJsonProxy positionJsonProxy : apiResponse.getMissingPositions()) {
////                        PositionJson positionJson = positionJsonProxy.getPositionJson();
////                        System.out.println("positionJson id= " + positionJson.getId() + ", symbol=" + positionJson.getSymbol() + ", type=" + positionJson.getType());
////                        System.out.println("tradeJsonLeg symbol=" + tradeJsonLeg.getSymbol() + ", type=" + tradeJsonLeg.getType());
////                        System.out.println(filteredTradeListWithoutMissingTrades.contains(tradeJson));
////
////                        if (!filteredTradeListWithoutMissingTrades.contains(tradeJson)
////                                && (!positionJson.getType().equals(tradeJsonLeg.getType())
////                                || !positionJson.getSymbol().equals(tradeJsonLeg.getSymbol()))) {//need equals
////                            filteredTradeListWithoutMissingTrades.add(tradeJson);
////                            System.out.println("add trade to new trade list\n");
////                            takeNextTrade = true;
////                            break;
////                        }
////                    }
////                }
////            }
//
//
//            for (PositionJsonProxy positionJsonProxy : apiResponse.getMissingPositions()) {
//                PositionJson positionJson = positionJsonProxy.getPositionJson();
//
//
//                for (TradeJson tradeJson : tradeJsonList) {
//                    boolean takeNextTrade = false;
//
////                    System.out.println("positionJson id= " + positionJson.getId() + ", symbol=" + positionJson.getSymbol() + ", type=" + positionJson.getType());
////                    System.out.println("tradeJsonLeg symbol=" + tradeJsonLeg.getSymbol() + ", type=" + tradeJsonLeg.getType());
////                    System.out.println(filteredTradeListWithoutMissingTrades.contains(tradeJson));
//
//
//                    for (TradeJsonLeg tradeJsonLeg : tradeJson.getLegs()) {
//
//                        if (//!filteredTradeListWithoutMissingTrades.contains(tradeJson)&&
//                                (!positionJson.getType().equals(tradeJsonLeg.getType())
//                                        || !positionJson.getSymbol().equals(tradeJsonLeg.getSymbol()))) {//need equals
//                            filteredTradeListWithoutMissingTrades.add(tradeJson);
//                            System.out.println("add trade to new trade list\n");
//                            takeNextTrade = true;
//                            break;
//                        }
//                    }
//                    if (takeNextTrade) {
//                        break;
//                    }
//                }
//
//
//            }
//
//
//        } else {
//            ///прям по листу идем
//            System.out.println("create trade list from all trades");
//            filteredTradeListWithoutMissingTrades = new ArrayList<>(tradeJsonList);
//        }
//        return filteredTradeListWithoutMissingTrades;
//    }
//
//    private static void findMissingQuantity(List<PositionJson> positionJsonList,
//                                            List<TradeJson> tradeJsonList,
//                                            ApiResponse apiResponse) {
//
//        List<TradeJson> unprocessedTradeJsons = new ArrayList<>();
//        //missing
//        for (PositionJson positionJson : positionJsonList) {
//
//            int actualQuantity = positionJson.getQuantity();
//            int receivedQuantity = 0;
//            boolean tradePresented = false;
//
//            //checks is position json present
//            //add legs calculation!!
//            //FULL
//            for (TradeJson tradeJson : tradeJsonList) {
//                for (TradeJsonLeg tradeJsonLeg : tradeJson.getLegs()) {
//                    if (positionJson.getType().equals(tradeJsonLeg.getType())
//                            && positionJson.getSymbol().equals(tradeJsonLeg.getSymbol())) {
//                        receivedQuantity += tradeJson.getQuantity();
//
//                        if (!tradePresented) {
//                            tradePresented = true;
//                        }
//                    }
//                }
//            }
//
//
//            System.out.println("actualQuantity=" + actualQuantity + ", receivedQuantity=" + receivedQuantity);
//
//            if (actualQuantity > receivedQuantity) {//is missing
//                System.out.println("missing:  " + positionJson.getSymbol() + " " + "actualQuantity=" + actualQuantity + ", receivedQuantity=" + receivedQuantity);
//                addPositionToMissingList(apiResponse, positionJson, actualQuantity, receivedQuantity);
//            } else if (
//                    (actualQuantity < receivedQuantity) && tradePresented // if trade have more quantity than position but trade presented ) {
//            ) {
//                System.out.println("extra cause trade have bigger quantity than position");
//                System.out.println("position=" + positionJson.getSymbol() + "" +
//                        ", actualQuantity=" + actualQuantity + "   receivedQuantity=" + receivedQuantity);
//                addPositionToExtraList(apiResponse, positionJson, positionJson.getQuantity());
//            }
//
//            if (actualQuantity != receivedQuantity
//                    && !apiResponse
//                    .getExtraPositions().contains(new PositionJsonProxy(positionJson, positionJson.getQuantity()))
//                    && !apiResponse
//                    .getMissingPositions().contains(new PositionJsonProxy(positionJson, positionJson.getQuantity()))
//            ) {
//                System.out.println("extra:  " + positionJson.getSymbol() + " " + "actualQuantity=" + actualQuantity + ", receivedQuantity=" + receivedQuantity);
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
//            if (!positionPresented) {
//                tradeJson.getLegs().forEach(tradeJsonLeg -> {
//                    PositionJson positionJson =
//                            new PositionJson(tradeJsonLeg.getSymbol(), tradeJsonLeg.getType(), tradeJson.getQuantity());
//                    PositionJsonProxy positionJsonProxy = new PositionJsonProxy(positionJson, tradeJson.getQuantity());
//                    if (!apiResponse
//                            .getExtraPositions().contains(positionJsonProxy)
//                            && !apiResponse
//                            .getMissingPositions().contains(positionJsonProxy)) {
//                        addPositionToExtraList(apiResponse, positionJson, tradeJson.getQuantity());
//                    }
//                });
//            }
//        }
//
//    }
//
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
//
//    private static boolean isTradeListEmpty(List<PositionJson> positionJsonList,
//                                            List<TradeJson> tradeJsonList) {
//
//        return tradeJsonList.isEmpty() && !positionJsonList.isEmpty();
//    }
//}
