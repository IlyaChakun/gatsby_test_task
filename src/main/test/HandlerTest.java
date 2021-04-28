//import by.chakun.*;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class HandlerTest {
//
//    @Test
//    public void testCorrectWithMultipleLegs() {
//
//        List<TradeJson> tradeJsonList = new ArrayList<>();
//
//        //1 will all rigth with quantity
//        TradeJson tradeJson1 = new TradeJson();
//        tradeJson1.setId(1235L);
//        tradeJson1.setQuantity(7);
//        List<TradeJsonLeg> tradeJsonLegs = new ArrayList<>();
//        TradeJsonLeg tradeJsonLeg = new TradeJsonLeg();
//        tradeJsonLeg.setSymbol("ACOR1 210416C00001000");
//        tradeJsonLeg.setType("short");
//        tradeJsonLegs.add(tradeJsonLeg);
//        tradeJson1.setLegs(tradeJsonLegs);
//        tradeJsonList.add(tradeJson1);
//        //1.2 will all rigth with quantity
//        TradeJson tradeJson1_2 = new TradeJson();
//        tradeJson1_2.setId(12365L);
//        tradeJson1_2.setQuantity(4);
//        List<TradeJsonLeg> tradeJsonLegs1_2 = new ArrayList<>();
//        TradeJsonLeg tradeJsonLeg1_2 = new TradeJsonLeg();
//        tradeJsonLeg1_2.setSymbol("ACOR1 210416C00001000");
//        tradeJsonLeg1_2.setType("short");
//        tradeJsonLegs1_2.add(tradeJsonLeg1_2);
//        tradeJson1_2.setLegs(tradeJsonLegs1_2);
//        tradeJsonList.add(tradeJson1_2);
//        //1.3 will all rigth with quantity
//        TradeJson tradeJson1_3 = new TradeJson();
//        tradeJson1_3.setId(123245L);
//        tradeJson1_3.setQuantity(1);
//        List<TradeJsonLeg> tradeJsonLegs1_3 = new ArrayList<>();
//        TradeJsonLeg tradeJsonLeg1_3 = new TradeJsonLeg();
//        tradeJsonLeg1_3.setSymbol("ACOR1 210416C00001000");
//        tradeJsonLeg1_3.setType("short");
//        tradeJsonLegs1_3.add(tradeJsonLeg1_3);
//        tradeJson1_3.setLegs(tradeJsonLegs1_3);
//        tradeJsonList.add(tradeJson1_3);
//
//        ////
//        List<PositionJson> positionJsonList = new ArrayList<>();
//        //1
//        PositionJson positionJson = new PositionJson();
//        positionJson.setId(1234L);
//        positionJson.setSymbol("ACOR1 210416C00001000");
//        positionJson.setType("short");
//        positionJson.setQuantity(12);
//        positionJsonList.add(positionJson);
//
//
//        ApiResponse apiResponse = TradeProcessor.parseTradesAndPositions(positionJsonList, tradeJsonList);
//
//        int missingQuantity = 0;
//        int extraCount = 0;
//
//        Assertions.assertNotNull(apiResponse);
//
//        Assertions.assertEquals(missingQuantity, apiResponse.getMissingPositions().size());
//        Assertions.assertEquals(extraCount, apiResponse.getExtraPositions().size());
//    }
//
//
//    @Test
//    public void testCorrectWithMultipleLegs_InvalidTradeCount_Missing() {
//
//        List<TradeJson> tradeJsonList = new ArrayList<>();
//
//        //1 will all rigth with quantity
//        TradeJson tradeJson1 = new TradeJson();
//        tradeJson1.setId(1235L);
//        tradeJson1.setQuantity(7);
//        List<TradeJsonLeg> tradeJsonLegs = new ArrayList<>();
//        TradeJsonLeg tradeJsonLeg = new TradeJsonLeg();
//        tradeJsonLeg.setSymbol("ACOR1 210416C00001000");
//        tradeJsonLeg.setType("short");
//        tradeJsonLegs.add(tradeJsonLeg);
//        tradeJson1.setLegs(tradeJsonLegs);
//        tradeJsonList.add(tradeJson1);
//        //1.2 will all rigth with quantity
//        TradeJson tradeJson1_2 = new TradeJson();
//        tradeJson1_2.setId(12365L);
//        tradeJson1_2.setQuantity(4);
//        List<TradeJsonLeg> tradeJsonLegs1_2 = new ArrayList<>();
//        TradeJsonLeg tradeJsonLeg1_2 = new TradeJsonLeg();
//        tradeJsonLeg1_2.setSymbol("ACOR1 210416C00001000");
//        tradeJsonLeg1_2.setType("short");
//        tradeJsonLegs1_2.add(tradeJsonLeg1_2);
//        tradeJson1_2.setLegs(tradeJsonLegs1_2);
//        tradeJsonList.add(tradeJson1_2);
//        //1.3 will all rigth with quantity
//        TradeJson tradeJson1_3 = new TradeJson();
//        tradeJson1_3.setId(123245L);
//        tradeJson1_3.setQuantity(1);
//        List<TradeJsonLeg> tradeJsonLegs1_3 = new ArrayList<>();
//        TradeJsonLeg tradeJsonLeg1_3 = new TradeJsonLeg();
//        tradeJsonLeg1_3.setSymbol("ACOR1 210416C00001000");
//        tradeJsonLeg1_3.setType("short");
//        tradeJsonLegs1_3.add(tradeJsonLeg1_3);
//        tradeJson1_3.setLegs(tradeJsonLegs1_3);
//        tradeJsonList.add(tradeJson1_3);
//
//        ////
//        List<PositionJson> positionJsonList = new ArrayList<>();
//        //1
//        PositionJson positionJson = new PositionJson();
//        positionJson.setId(1234L);
//        positionJson.setSymbol("ACOR1 210416C00001000");
//        positionJson.setType("short");
//        positionJson.setQuantity(13);
//        positionJsonList.add(positionJson);
//
//
//        ApiResponse apiResponse = TradeProcessor.parseTradesAndPositions(positionJsonList, tradeJsonList);
//
//        int missingQuantity = 1;
//        int extraCount = 0;
//
//        Assertions.assertNotNull(apiResponse);
//
//        Assertions.assertEquals(missingQuantity, apiResponse.getMissingPositions().size());
//        Assertions.assertEquals(extraCount, apiResponse.getExtraPositions().size());
//    }
//
//
//    @Test
//    public void testCorrectWithMultipleLegs_InvalidTradeCount_Extra() {
//
//        List<TradeJson> tradeJsonList = new ArrayList<>();
//
//        //1 will all rigth with quantity
//        TradeJson tradeJson1 = new TradeJson();
//        tradeJson1.setId(1235L);
//        tradeJson1.setQuantity(7);
//        List<TradeJsonLeg> tradeJsonLegs = new ArrayList<>();
//        TradeJsonLeg tradeJsonLeg = new TradeJsonLeg();
//        tradeJsonLeg.setSymbol("ACOR1 210416C00001000");
//        tradeJsonLeg.setType("short");
//        tradeJsonLegs.add(tradeJsonLeg);
//        tradeJson1.setLegs(tradeJsonLegs);
//        tradeJsonList.add(tradeJson1);
//        //1.2 will all rigth with quantity
//        TradeJson tradeJson1_2 = new TradeJson();
//        tradeJson1_2.setId(12365L);
//        tradeJson1_2.setQuantity(4);
//        List<TradeJsonLeg> tradeJsonLegs1_2 = new ArrayList<>();
//        TradeJsonLeg tradeJsonLeg1_2 = new TradeJsonLeg();
//        tradeJsonLeg1_2.setSymbol("ACOR1 210416C00001000");
//        tradeJsonLeg1_2.setType("short");
//        tradeJsonLegs1_2.add(tradeJsonLeg1_2);
//        tradeJson1_2.setLegs(tradeJsonLegs1_2);
//        tradeJsonList.add(tradeJson1_2);
//        //1.3 will all rigth with quantity
//        TradeJson tradeJson1_3 = new TradeJson();
//        tradeJson1_3.setId(123245L);
//        tradeJson1_3.setQuantity(2);
//        List<TradeJsonLeg> tradeJsonLegs1_3 = new ArrayList<>();
//        TradeJsonLeg tradeJsonLeg1_3 = new TradeJsonLeg();
//        tradeJsonLeg1_3.setSymbol("ACOR1 210416C00001000");
//        tradeJsonLeg1_3.setType("short");
//        tradeJsonLegs1_3.add(tradeJsonLeg1_3);
//        tradeJson1_3.setLegs(tradeJsonLegs1_3);
//        tradeJsonList.add(tradeJson1_3);
//
//        ////
//        List<PositionJson> positionJsonList = new ArrayList<>();
//        //1
//        PositionJson positionJson = new PositionJson();
//        positionJson.setId(1234L);
//        positionJson.setSymbol("ACOR1 210416C00001000");
//        positionJson.setType("short");
//        positionJson.setQuantity(12);
//        positionJsonList.add(positionJson);
//
//
//        ApiResponse apiResponse = TradeProcessor.parseTradesAndPositions(positionJsonList, tradeJsonList);
//
//        int missingQuantity = 0;
//        int extraCount = 1;
//
//        Assertions.assertNotNull(apiResponse);
//
//        Assertions.assertEquals(missingQuantity, apiResponse.getMissingPositions().size());
//        Assertions.assertEquals(extraCount, apiResponse.getExtraPositions().size());
//    }
//    //
//
//    @Test
//    public void testMissingPositionsByQuantity() {
//
//        List<TradeJson> tradeJsonList = new ArrayList<>();
//
//        TradeJson tradeJson1 = new TradeJson();
//        tradeJson1.setId(1235L);
//        tradeJson1.setQuantity(7);
//        List<TradeJsonLeg> tradeJsonLegs = new ArrayList<>();
//        TradeJsonLeg tradeJsonLeg = new TradeJsonLeg();
//        tradeJsonLeg.setSymbol("ACOR1 210416C00001000");
//        tradeJsonLeg.setType("short");//short/long
//        tradeJsonLegs.add(tradeJsonLeg);
//        tradeJson1.setLegs(tradeJsonLegs);
//        tradeJsonList.add(tradeJson1);
//
//        List<PositionJson> positionJsonList = new ArrayList<>();
//        PositionJson positionJson = new PositionJson();
//        positionJson.setId(1234L);
//        positionJson.setSymbol("ACOR1 210416C00001000");
//        positionJson.setType("short");//short/long
//        positionJson.setQuantity(12);
//        positionJsonList.add(positionJson);
//
//        ApiResponse apiResponse = TradeProcessor.parseTradesAndPositions(positionJsonList, tradeJsonList);
//
//        int missingQuantity = 5;
//
//        Assertions.assertNotNull(apiResponse);
//
//        Assertions.assertEquals(missingQuantity, apiResponse.getMissingPositions().get(0).getQuantity());
//    }
//
////type in enum
//
//    @Test
//    public void testMissingPositionByType() {
//
//        List<TradeJson> tradeJsonList = new ArrayList<>();
//
//        TradeJson tradeJson1 = new TradeJson();
//        tradeJson1.setId(1235L);
//        tradeJson1.setQuantity(7);
//        List<TradeJsonLeg> tradeJsonLegs = new ArrayList<>();
//        TradeJsonLeg tradeJsonLeg = new TradeJsonLeg();
//        tradeJsonLeg.setSymbol("ACOR1 210416C00001000");
//        tradeJsonLeg.setType("long");
//        tradeJsonLegs.add(tradeJsonLeg);
//        tradeJson1.setLegs(tradeJsonLegs);
//        tradeJsonList.add(tradeJson1);
//
//        List<PositionJson> positionJsonList = new ArrayList<>();
//        PositionJson positionJson = new PositionJson();
//        positionJson.setId(1234L);
//        positionJson.setSymbol("ACOR1 210416C00001000");
//        positionJson.setType("short");
//        positionJson.setQuantity(12);
//        positionJsonList.add(positionJson);
//
//        ApiResponse apiResponse = TradeProcessor.parseTradesAndPositions(positionJsonList, tradeJsonList);
//
//        int missingQuantity = 12;
//
//        Assertions.assertNotNull(apiResponse);
//
//        Assertions.assertEquals(missingQuantity, apiResponse.getMissingPositions().get(0).getQuantity());
//    }
//
//
//    @Test
//    public void testMissingPositionByNotEqualSymbol() {
//
//        List<TradeJson> tradeJsonList = new ArrayList<>();
//
//        TradeJson tradeJson1 = new TradeJson();
//        tradeJson1.setId(1235L);
//        tradeJson1.setQuantity(7);
//        List<TradeJsonLeg> tradeJsonLegs = new ArrayList<>();
//        TradeJsonLeg tradeJsonLeg = new TradeJsonLeg();
//        tradeJsonLeg.setSymbol("ACOR1 210416C00001000");
//        tradeJsonLeg.setType("long");
//        tradeJsonLegs.add(tradeJsonLeg);
//        tradeJson1.setLegs(tradeJsonLegs);
//        tradeJsonList.add(tradeJson1);
//
//        List<PositionJson> positionJsonList = new ArrayList<>();
//        PositionJson positionJson = new PositionJson();
//        positionJson.setId(1234L);
//        positionJson.setSymbol("ACOR2 210416C00001000");
//        positionJson.setType("short");
//        positionJson.setQuantity(12);
//        positionJsonList.add(positionJson);
//
//        ApiResponse apiResponse = TradeProcessor.parseTradesAndPositions(positionJsonList, tradeJsonList);
//
//        int missingQuantity = 12;
//
//        Assertions.assertNotNull(apiResponse);
//
//        Assertions.assertEquals(missingQuantity, apiResponse.getMissingPositions().get(0).getQuantity());
//    }
//
//
//    @Test
//    public void testExtraPosition() {
//
//        List<TradeJson> tradeJsonList = new ArrayList<>();
//
//        TradeJson tradeJson1 = new TradeJson();
//        tradeJson1.setId(1235L);
//        tradeJson1.setQuantity(7);
//        List<TradeJsonLeg> tradeJsonLegs = new ArrayList<>();
//        TradeJsonLeg tradeJsonLeg = new TradeJsonLeg();
//        tradeJsonLeg.setSymbol("ACOR1 210416C00001000");
//        tradeJsonLeg.setType("long");
//        tradeJsonLegs.add(tradeJsonLeg);
//        tradeJson1.setLegs(tradeJsonLegs);
//        tradeJsonList.add(tradeJson1);
//
//        List<PositionJson> positionJsonList = new ArrayList<>();
//
//        ApiResponse apiResponse = TradeProcessor.parseTradesAndPositions(positionJsonList, tradeJsonList);
//
//        int extraPosition = 1;
//
//        Assertions.assertNotNull(apiResponse);
//
//        Assertions.assertEquals(extraPosition, apiResponse.getExtraPositions().size());
//    }
//
//    @Test
//    public void test_ExtraPosition_with_MissingPosition() {
//
//        List<TradeJson> tradeJsonList = new ArrayList<>();
//
//
//
//        TradeJson tradeJson1 = new TradeJson();//extra
//        tradeJson1.setId(1235L);
//        tradeJson1.setQuantity(12);
//        List<TradeJsonLeg> tradeJsonLegs = new ArrayList<>();
//        TradeJsonLeg tradeJsonLeg = new TradeJsonLeg();
//        tradeJsonLeg.setSymbol("ACOR1 210416C00001000");
//        tradeJsonLeg.setType("long");
//
//
////        TradeJsonLeg tradeJsonLeg = new TradeJsonLeg();
////        tradeJsonLeg.setSymbol("ACOR1 210416C00001000");
////        tradeJsonLeg.setType("short");
////        tradeJsonLegs.add(tradeJsonLeg);
////        tradeJson1.setLegs(tradeJsonLegs);
//        tradeJsonList.add(tradeJson1);
//
///// есть спред на 12 и есть просто трейд на 5 обычный с одной ногой  (обычный = long) в сумме надо 17 long & 12 short
//        //если пришло 10 long то trade на 5 собирается, трейд сперд не собирается и все что осталось полсе сбора (12 short и 5 long) в missing
//        // один и тот же symbol
//        // в спреде и long и short взаимокомпенсация
//        // генератор для класса (сделать спред, сделать обычны трейд)
//        // добавить expected для тестов
//        //lombok
////        TradeJson tradeJson1 = new TradeJson();//extra
////        tradeJson1.setId(1235L);
////        tradeJson1.setQuantity(5);
////        List<TradeJsonLeg> tradeJsonLegs = new ArrayList<>();
////        TradeJsonLeg tradeJsonLeg = new TradeJsonLeg();
////        tradeJsonLeg.setSymbol("ACOR1 210416C00001000");
////        tradeJsonLeg.setType("long");
//
//
//        //если нескольок legs
//
//
//        List<PositionJson> positionJsonList = new ArrayList<>();
//        PositionJson positionJson = new PositionJson();//missing
//        positionJson.setId(1234L);
//        positionJson.setSymbol("ACOR1 210416C00001000");
//        positionJson.setType("long");
//        positionJson.setQuantity(12);
//
//        PositionJson positionJson2 = new PositionJson();//missing
//        positionJson.setId(1234L);
//        positionJson.setSymbol("ACOR1 210416C00001000");
//        positionJson.setType("short");
//        positionJson.setQuantity(12);
//        positionJsonList.add(positionJson);
//        ApiResponse apiResponse = TradeProcessor.parseTradesAndPositions(positionJsonList, tradeJsonList);
//
//        int missingQuantity = 1;
//        int extraPosition = 1;
//
//        Assertions.assertNotNull(apiResponse);
//
//        Assertions.assertEquals(extraPosition, apiResponse.getExtraPositions().size());
//        Assertions.assertEquals(missingQuantity, apiResponse.getMissingPositions().size());
//    }
//
//    @Test
//    public void testMissingPositionList_Extra() {
//
//        List<TradeJson> tradeJsonList = new ArrayList<>();
//
//        TradeJson tradeJson1 = new TradeJson();
//        tradeJson1.setId(1235L);
//        tradeJson1.setQuantity(7);
//        List<TradeJsonLeg> tradeJsonLegs = new ArrayList<>();
//        TradeJsonLeg tradeJsonLeg = new TradeJsonLeg();
//        tradeJsonLeg.setSymbol("ACOR1 210416C00001000");
//        tradeJsonLeg.setType("long");
//        tradeJsonLegs.add(tradeJsonLeg);
//        tradeJson1.setLegs(tradeJsonLegs);
//        tradeJsonList.add(tradeJson1);
//
//        TradeJson tradeJson2 = new TradeJson();
//        tradeJson2.setId(1236L);
//        tradeJson2.setQuantity(8);
//        List<TradeJsonLeg> tradeJsonLegs2 = new ArrayList<>();
//        TradeJsonLeg tradeJsonLeg2 = new TradeJsonLeg();
//        tradeJsonLeg2.setSymbol("ACOR2 210416C00001000");
//        tradeJsonLeg2.setType("long");
//        tradeJsonLegs2.add(tradeJsonLeg2);
//        tradeJson2.setLegs(tradeJsonLegs2);
//        tradeJsonList.add(tradeJson1);
//
//        List<PositionJson> positionJsonList = new ArrayList<>();
//
//        ApiResponse apiResponse = TradeProcessor.parseTradesAndPositions(positionJsonList, tradeJsonList);
//
//        int missingQuantity = 2;
//
//        Assertions.assertNotNull(apiResponse);
//
//        Assertions.assertEquals(missingQuantity, apiResponse.getExtraPositions().size());//missing
//    }
//
//    @Test
//    public void testMatchedTradeList_ExpectedOneTradeMatched() {
//
//        List<TradeJson> tradeJsonList = new ArrayList<>();
//
//        List<PositionJson> positionJsonList = new ArrayList<>();
//        PositionJson positionJson = new PositionJson();
//        positionJson.setId(1234L);
//        positionJson.setSymbol("ACOR2 210416C00001000");
//        positionJson.setType("short");
//        positionJson.setQuantity(12);
//        positionJsonList.add(positionJson); //EXTRA POSITION ПОТОМУ ЧТО НТЕ ТРЕЙНДОВ
//
//        ApiResponse apiResponse = TradeProcessor.parseTradesAndPositions(positionJsonList, tradeJsonList);
//
//        int missingQuantity = 1;
//
//        Assertions.assertNotNull(apiResponse);
//
//        Assertions.assertEquals(missingQuantity, apiResponse.get().size());
//    }
//
//    //////////
//
//
//    @Test
//    public void testCorrectMissingCountExtraCount() {
//
//        List<TradeJson> tradeJsonList = new ArrayList<>();
//
//        //1 will all rigth with quantity
//        TradeJson tradeJson1 = new TradeJson();
//        tradeJson1.setId(1235L);
//        tradeJson1.setQuantity(7);//12
//        List<TradeJsonLeg> tradeJsonLegs = new ArrayList<>();
//        TradeJsonLeg tradeJsonLeg = new TradeJsonLeg();
//        tradeJsonLeg.setSymbol("ACOR1 210416C00001000");
//        tradeJsonLeg.setType("short");
//        tradeJsonLegs.add(tradeJsonLeg);
//        tradeJson1.setLegs(tradeJsonLegs);
//        tradeJsonList.add(tradeJson1);
//        //1.2 will all rigth with quantity
//        TradeJson tradeJson1_2 = new TradeJson();
//        tradeJson1_2.setId(12365L);
//        tradeJson1_2.setQuantity(4);
//        List<TradeJsonLeg> tradeJsonLegs1_2 = new ArrayList<>();
//        TradeJsonLeg tradeJsonLeg1_2 = new TradeJsonLeg();
//        tradeJsonLeg1_2.setSymbol("ACOR1 210416C00001000");
//        tradeJsonLeg1_2.setType("short");
//        tradeJsonLegs1_2.add(tradeJsonLeg1_2);
//        tradeJson1_2.setLegs(tradeJsonLegs1_2);
//        tradeJsonList.add(tradeJson1_2);
//        //1.3 will all rigth with quantity
//        TradeJson tradeJson1_3 = new TradeJson();
//        tradeJson1_3.setId(123245L);
//        tradeJson1_3.setQuantity(1);
//        List<TradeJsonLeg> tradeJsonLegs1_3 = new ArrayList<>();
//        TradeJsonLeg tradeJsonLeg1_3 = new TradeJsonLeg();
//        tradeJsonLeg1_3.setSymbol("ACOR1 210416C00001000");
//        tradeJsonLeg1_3.setType("short");
//        tradeJsonLegs1_3.add(tradeJsonLeg1_3);
//        tradeJson1_3.setLegs(tradeJsonLegs1_3);
//        tradeJsonList.add(tradeJson1_3);
//
//        //2
//        TradeJson tradeJson2 = new TradeJson();
//        tradeJson2.setId(1236L);
//        tradeJson2.setQuantity(10);
//        List<TradeJsonLeg> tradeJsonLegs2 = new ArrayList<>();
//        TradeJsonLeg tradeJsonLeg2 = new TradeJsonLeg();
//        tradeJsonLeg2.setSymbol("ACOR2 210416C00001000");
//        tradeJsonLeg2.setType("short");
//        tradeJsonLegs2.add(tradeJsonLeg2);
//        tradeJson2.setLegs(tradeJsonLegs2);
//        tradeJsonList.add(tradeJson2);
//        //3 will as extra trade
//        TradeJson tradeJson3 = new TradeJson();
//        tradeJson3.setId(1237L);
//        tradeJson3.setQuantity(1);
//        List<TradeJsonLeg> tradeJsonLegs3 = new ArrayList<>();
//        TradeJsonLeg tradeJsonLeg3 = new TradeJsonLeg();
//        tradeJsonLeg3.setSymbol("ACOR3 210416C00001000");
//        tradeJsonLeg3.setType("long");
//        tradeJsonLegs3.add(tradeJsonLeg3);
//        tradeJson3.setLegs(tradeJsonLegs3);
//        tradeJsonList.add(tradeJson3);
//
//        //4  one piece will not enough (quantity = 2, we will offer in trade 1) EXTRA 1 COUNT
//        TradeJson tradeJson4 = new TradeJson();
//        tradeJson4.setId(1238L);
//        tradeJson4.setQuantity(2);
//        List<TradeJsonLeg> tradeJsonLegs4 = new ArrayList<>();
//        TradeJsonLeg tradeJsonLeg4 = new TradeJsonLeg();
//        tradeJsonLeg4.setSymbol("ACOR45 210416C00001000");
//        tradeJsonLeg4.setType("long");
//        tradeJsonLegs4.add(tradeJsonLeg4);
//        tradeJson4.setLegs(tradeJsonLegs4);
//        tradeJsonList.add(tradeJson4);
//
//
//        ////
//        List<PositionJson> positionJsonList = new ArrayList<>();
//        //1
//        PositionJson positionJson = new PositionJson();
//        positionJson.setId(1234L);
//        positionJson.setSymbol("ACOR1 210416C00001000");
//        positionJson.setType("short");
//        positionJson.setQuantity(12);
//        positionJsonList.add(positionJson);
//        //2
//        PositionJson positionJson2 = new PositionJson();
//        positionJson2.setId(1235L);
//        positionJson2.setSymbol("ACOR2 210416C00001000");
//        positionJson2.setType("short");
//        positionJson2.setQuantity(10);
//        positionJsonList.add(positionJson2);
//        //3 as missing
//        PositionJson positionJson3 = new PositionJson();
//        positionJson3.setId(1236L);
//        positionJson3.setSymbol("ACOR12 210416C00001000");
//        positionJson3.setType("long");
//        positionJson3.setQuantity(55);
//        positionJsonList.add(positionJson3);
//        //4  one piece will not enough (quantity = 2, we will offer in trade 1) EXTRA FROM TRADE
//        PositionJson positionJson4 = new PositionJson();
//        positionJson4.setId(1237L);
//        positionJson4.setSymbol("ACOR45 210416C00001000");
//        positionJson4.setType("long");
//        positionJson4.setQuantity(1);
//        positionJsonList.add(positionJson4);
//
//        ApiResponse apiResponse = TradeProcessor.parseTradesAndPositions(positionJsonList, tradeJsonList);
//
//        Assertions.assertNotNull(apiResponse);
//
//        int extaCount = 2;
//        int missingCount = 1;
//        // we have 1 exta
//        Assertions.assertEquals(extaCount, apiResponse.getExtraPositions().size());
//        // we have 1 missing
//        Assertions.assertEquals(missingCount, apiResponse.getMissingPositions().size());
//
//        int positionACOR45MissingCount = 1;
//        Assertions.assertEquals(positionACOR45MissingCount,
//                apiResponse.getExtraPositions()
//                        .stream()
//                        .filter(positionJsonProxy -> positionJsonProxy.getPositionJson().getSymbol().equals("ACOR45 210416C00001000"))
//                        .findFirst()
//                        .get()
//                        .getQuantity()
//        );
//    }
//
//
//}