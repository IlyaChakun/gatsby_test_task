package by.chakun;

public class Main {

    public static void main(String[] args) {
        //Given a list of positions and a list of trades match up and return trades that don't have correct positions.
        //Position JSON:
        //{"id": 1234, "symbol":"ACOR1 210416C00001000", "type": "short/long", "quantity": 10}//12
        //
        //Trade JSON:
        //{"id": 4569, "quantity": 5, "legs":[{"symbol": "ACOR1 210416C00001000" , "type":"short/long"}]}



        //{"id": 4567, "quantity": 7, "legs":[{"symbol": "ACOR1 210416C00001000" , "type":"short/long"}]}


        //use cases not hiding: what happedns trade and second trade  7
        //12 short and 12 long pos separate
        //diff leg which trades are missing position Trade Id
        //order of list
        //trades with more legs must be first complex trade
        //

        //create function list of position and list of trades
        //return back a list of extra positions and list of positionos thas are missing

        TradeProcessor.doLogic();
    }


//    private static List<PositionJsonProxy> parseTradesAndPositions(List<PositionJson> positionJsonList,
//                                                               List<TradeJson> tradeJsonList) {
//
//        List<PositionJsonProxy> missingPositionsJson = new ArrayList<>();
//
//        tradeJsonList.forEach(tradeJson -> {
//            tradeJson.getLegs()
//                    .forEach(
//                            tradeJsonLeg -> {
//                                positionJsonList.forEach(positionJson -> {
//                                    if (tradeJsonLeg.getSymbol().equals(positionJson.getSymbol())) {
//                                        //missing ad position json
//
//
//                                        if (!tradeJson.getQuantity().equals(positionJson.getQuantity())) {
//
//                                            PositionJsonProxy positionJsonProxy = new PositionJsonProxy();
//                                            positionJsonProxy.setQuantity(
//                                                    positionJson.getQuantity() - tradeJson.getQuantity()
//                                            );
//                                            missingPositionsJson.add(positionJsonProxy);
//                                        }
//                                    }
//                                });
//                            }
//                    );
//        });
//
//        return missingPositionsJson;
//    }



    /*
        1. все позиции заматичились
        2. пришлось больше поизций чем трейдов -> лишниее позиции они extra
        3. пришлось меньеш позиций чем трейдов -> missing + trade в not match
        4.
     */
}
