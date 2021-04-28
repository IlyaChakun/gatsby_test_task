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
      1.all positions are mated
        2. had more positions than trades -> extra positions they are extra
        3. had fewer positions than trades -> missing + trade in not match
        four.

        ///// there is a spread of 12 and there is just a trade of 5 normal with one leg (normal = long) in total, 17 long & 12 short are needed
// // if 10 long arrived, then trade for 5 is collected, the trade is not collected and all that is left half of the collection (12 short and 5 long) is missing
// // same symbol
// // in the spread both long and short compensate
// // generator for the class (make a spread, make a regular trade)
// // add expected for tests

     */
}
