import by.chakun.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class HandlerTest {


//    static List<PositionJson> positionJsonList;
//    static List<TradeJson> tradeJsonList;
//
//    @BeforeAll
//    public static void beforeClass() {
//        positionJsonList = getPositionJsons();
//        tradeJsonList = getTradeJsons();
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
//        tradeJsonLeg2.setSymbol("ACOR1 210416C00001000");
//        tradeJsonLeg2.setType("short");//short/long
//        tradeJsonLegs2.add(tradeJsonLeg2);
//        tradeJson2.setLegs(tradeJsonLegs2);
//
//        tradeJsonList.add(tradeJson1);
//        //tradeJsonList.add(tradeJson2);
//
//        return tradeJsonList;
//    }


    private static List<PositionJson> getPositionJsons() {
        List<PositionJson> positionJsonList = new ArrayList<>();
        PositionJson positionJson = new PositionJson();
        positionJson.setId(1234L);
        positionJson.setSymbol("ACOR1 210416C00001000");
        positionJson.setType("long");//short/long
        positionJson.setQuantity(12);
        positionJsonList.add(positionJson);
        return positionJsonList;
    }

    @Test
    public void testMissingPositionsByQuantity() {

        List<TradeJson> tradeJsonList = new ArrayList<>();

        TradeJson tradeJson1 = new TradeJson();
        tradeJson1.setId(1235L);
        tradeJson1.setQuantity(7);
        List<TradeJsonLeg> tradeJsonLegs = new ArrayList<>();
        TradeJsonLeg tradeJsonLeg = new TradeJsonLeg();
        tradeJsonLeg.setSymbol("ACOR1 210416C00001000");
        tradeJsonLeg.setType("short");//short/long
        tradeJsonLegs.add(tradeJsonLeg);
        tradeJson1.setLegs(tradeJsonLegs);
        tradeJsonList.add(tradeJson1);

        List<PositionJson> positionJsonList = new ArrayList<>();
        PositionJson positionJson = new PositionJson();
        positionJson.setId(1234L);
        positionJson.setSymbol("ACOR1 210416C00001000");
        positionJson.setType("short");//short/long
        positionJson.setQuantity(12);
        positionJsonList.add(positionJson);

        ApiResponse apiResponse = Handler.parseTradesAndPositions(positionJsonList, tradeJsonList);

        int missingQuantity = 5;

        Assertions.assertNotNull(apiResponse);

        Assertions.assertEquals(missingQuantity, apiResponse.getMissingPositions().get(0).getQuantity());
    }


    @Test
    public void testMissingPositionByType() {

        List<TradeJson> tradeJsonList = new ArrayList<>();

        TradeJson tradeJson1 = new TradeJson();
        tradeJson1.setId(1235L);
        tradeJson1.setQuantity(7);
        List<TradeJsonLeg> tradeJsonLegs = new ArrayList<>();
        TradeJsonLeg tradeJsonLeg = new TradeJsonLeg();
        tradeJsonLeg.setSymbol("ACOR1 210416C00001000");
        tradeJsonLeg.setType("long");
        tradeJsonLegs.add(tradeJsonLeg);
        tradeJson1.setLegs(tradeJsonLegs);
        tradeJsonList.add(tradeJson1);

        List<PositionJson> positionJsonList = new ArrayList<>();
        PositionJson positionJson = new PositionJson();
        positionJson.setId(1234L);
        positionJson.setSymbol("ACOR1 210416C00001000");
        positionJson.setType("short");
        positionJson.setQuantity(12);
        positionJsonList.add(positionJson);

        ApiResponse apiResponse = Handler.parseTradesAndPositions(positionJsonList, tradeJsonList);

        int missingQuantity = 12;

        Assertions.assertNotNull(apiResponse);

        Assertions.assertEquals(missingQuantity, apiResponse.getMissingPositions().get(0).getQuantity());
    }


    @Test
    public void testMissingPositionByNotEqualSymbol() {

        List<TradeJson> tradeJsonList = new ArrayList<>();

        TradeJson tradeJson1 = new TradeJson();
        tradeJson1.setId(1235L);
        tradeJson1.setQuantity(7);
        List<TradeJsonLeg> tradeJsonLegs = new ArrayList<>();
        TradeJsonLeg tradeJsonLeg = new TradeJsonLeg();
        tradeJsonLeg.setSymbol("ACOR1 210416C00001000");
        tradeJsonLeg.setType("long");
        tradeJsonLegs.add(tradeJsonLeg);
        tradeJson1.setLegs(tradeJsonLegs);
        tradeJsonList.add(tradeJson1);

        List<PositionJson> positionJsonList = new ArrayList<>();
        PositionJson positionJson = new PositionJson();
        positionJson.setId(1234L);
        positionJson.setSymbol("ACOR2 210416C00001000");
        positionJson.setType("short");
        positionJson.setQuantity(12);
        positionJsonList.add(positionJson);

        ApiResponse apiResponse = Handler.parseTradesAndPositions(positionJsonList, tradeJsonList);

        int missingQuantity = 12;

        Assertions.assertNotNull(apiResponse);

        Assertions.assertEquals(missingQuantity, apiResponse.getMissingPositions().get(0).getQuantity());
    }




    @Test
    public void testExtraPosition() {

        List<TradeJson> tradeJsonList = new ArrayList<>();

        TradeJson tradeJson1 = new TradeJson();
        tradeJson1.setId(1235L);
        tradeJson1.setQuantity(7);
        List<TradeJsonLeg> tradeJsonLegs = new ArrayList<>();
        TradeJsonLeg tradeJsonLeg = new TradeJsonLeg();
        tradeJsonLeg.setSymbol("ACOR1 210416C00001000");
        tradeJsonLeg.setType("long");
        tradeJsonLegs.add(tradeJsonLeg);
        tradeJson1.setLegs(tradeJsonLegs);
        tradeJsonList.add(tradeJson1);

        List<PositionJson> positionJsonList = new ArrayList<>();

        ApiResponse apiResponse = Handler.parseTradesAndPositions(positionJsonList, tradeJsonList);

        int missingQuantity = 1;

        Assertions.assertNotNull(apiResponse);

        Assertions.assertEquals(missingQuantity, apiResponse.getExtraPositions().size());
    }


    @Test
    public void testMissingPositionList_Extra() {

        List<TradeJson> tradeJsonList = new ArrayList<>();

        TradeJson tradeJson1 = new TradeJson();
        tradeJson1.setId(1235L);
        tradeJson1.setQuantity(7);
        List<TradeJsonLeg> tradeJsonLegs = new ArrayList<>();
        TradeJsonLeg tradeJsonLeg = new TradeJsonLeg();
        tradeJsonLeg.setSymbol("ACOR1 210416C00001000");
        tradeJsonLeg.setType("long");
        tradeJsonLegs.add(tradeJsonLeg);
        tradeJson1.setLegs(tradeJsonLegs);
        tradeJsonList.add(tradeJson1);

        TradeJson tradeJson2 = new TradeJson();
        tradeJson2.setId(1236L);
        tradeJson2.setQuantity(8);
        List<TradeJsonLeg> tradeJsonLegs2 = new ArrayList<>();
        TradeJsonLeg tradeJsonLeg2 = new TradeJsonLeg();
        tradeJsonLeg2.setSymbol("ACOR2 210416C00001000");
        tradeJsonLeg2.setType("long");
        tradeJsonLegs2.add(tradeJsonLeg2);
        tradeJson2.setLegs(tradeJsonLegs2);
        tradeJsonList.add(tradeJson1);

        List<PositionJson> positionJsonList = new ArrayList<>();

        ApiResponse apiResponse = Handler.parseTradesAndPositions(positionJsonList, tradeJsonList);

        int missingQuantity = 2;

        Assertions.assertNotNull(apiResponse);

        Assertions.assertEquals(missingQuantity, apiResponse.getExtraPositions().size());
    }

    @Test
    public void testMissingTradeList() {

        List<TradeJson> tradeJsonList = new ArrayList<>();

        List<PositionJson> positionJsonList = new ArrayList<>();
        PositionJson positionJson = new PositionJson();
        positionJson.setId(1234L);
        positionJson.setSymbol("ACOR2 210416C00001000");
        positionJson.setType("short");
        positionJson.setQuantity(12);
        positionJsonList.add(positionJson);

        ApiResponse apiResponse = Handler.parseTradesAndPositions(positionJsonList, tradeJsonList);

        int missingQuantity = 1;

        Assertions.assertNotNull(apiResponse);

        Assertions.assertEquals(missingQuantity, apiResponse.getMissingPositions().size());
    }

    //////////




    @Test
    public void testCorrectMissingCountExtraCount() {

        List<TradeJson> tradeJsonList = new ArrayList<>();

        TradeJson tradeJson1 = new TradeJson();
        tradeJson1.setId(1235L);
        tradeJson1.setQuantity(7);
        List<TradeJsonLeg> tradeJsonLegs = new ArrayList<>();
        TradeJsonLeg tradeJsonLeg = new TradeJsonLeg();
        tradeJsonLeg.setSymbol("ACOR1 210416C00001000");
        tradeJsonLeg.setType("long");
        tradeJsonLegs.add(tradeJsonLeg);
        tradeJson1.setLegs(tradeJsonLegs);
        tradeJsonList.add(tradeJson1);

        List<PositionJson> positionJsonList = new ArrayList<>();
        PositionJson positionJson = new PositionJson();
        positionJson.setId(1234L);
        positionJson.setSymbol("ACOR1 210416C00001000");
        positionJson.setType("short");
        positionJson.setQuantity(12);
        positionJsonList.add(positionJson);

        ApiResponse apiResponse = Handler.parseTradesAndPositions(positionJsonList, tradeJsonList);

        int missingQuantity = 12;

        Assertions.assertNotNull(apiResponse);

        Assertions.assertEquals(missingQuantity, apiResponse.getMissingPositions().get(0).getQuantity());
    }














}