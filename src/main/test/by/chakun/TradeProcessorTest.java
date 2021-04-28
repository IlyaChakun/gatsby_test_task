package by.chakun;

import by.chakun.helper.PositionGenerator;
import by.chakun.helper.TradeGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class TradeProcessorTest {


    @Test
    public void testExtraPosition_ExpectedOneExtraPosition() {

        List<TradeJson> tradeJsonList = new ArrayList<>();
        //////////
        List<PositionJson> positionJsonList = new ArrayList<>();
        positionJsonList.add(PositionGenerator.getPosition("ACOR2 210416C00001000", PositionType.LONG, 12));
//EXTRA POSITION ПОТОМУ ЧТО НТЕ ТРЕЙНДОВ

        ApiResponse apiResponse = TradeProcessor.parseTradesAndPositions(positionJsonList, tradeJsonList);

        int extraPositionQuantity = 1;

        Assertions.assertNotNull(apiResponse);
        Assertions.assertEquals(extraPositionQuantity, apiResponse.getExtraPositions().size());
    }

    @Test
    public void testNonMatchedTradeWithEmptyPositionList_ExpectedOneNonMatchedTrade() {

        List<TradeJson> tradeJsonList = new ArrayList<>();
        tradeJsonList.add(TradeGenerator.getSimpleTrade("ACOR2 210416C00001000", 11));
        //////////
        List<PositionJson> positionJsonList = new ArrayList<>();

        ApiResponse apiResponse = TradeProcessor.parseTradesAndPositions(positionJsonList, tradeJsonList);

        Assertions.assertNotNull(apiResponse);
        Assertions.assertEquals(1, apiResponse.getNotMatchedTrades().size());
    }


    @Test
    public void testMatchedTradeList_ExpectedOneTradeMatched() {

        List<TradeJson> tradeJsonList = new ArrayList<>();
        tradeJsonList.add(TradeGenerator.getSimpleTrade("ACOR2 210416C00001000", 11));

        List<PositionJson> positionJsonList = new ArrayList<>();
        positionJsonList.add(PositionGenerator.getPosition("ACOR2 210416C00001000", PositionType.LONG, 11));

        ApiResponse apiResponse = TradeProcessor.parseTradesAndPositions(positionJsonList, tradeJsonList);

        int matchedTrade = 1;

        Assertions.assertNotNull(apiResponse);
        Assertions.assertEquals(matchedTrade, apiResponse.getMatchedTrades().size());
    }

    @Test
    public void testMatchedTradeList_ExpectedOneTradeMatched_AndOneExtraPosition() {

        List<TradeJson> tradeJsonList = new ArrayList<>();
        tradeJsonList.add(TradeGenerator.getSimpleTrade("ACOR2 210416C00001000", 11));
        //////////////////
        List<PositionJson> positionJsonList = new ArrayList<>();
        positionJsonList.add(PositionGenerator.getPosition("ACOR2 210416C00001000", PositionType.LONG, 12));

        ApiResponse apiResponse = TradeProcessor.parseTradesAndPositions(positionJsonList, tradeJsonList);

        int matchedTrade = 1;
        int extraPositionQuantity = 1;
        int extraPositionSize = 1;

        Assertions.assertNotNull(apiResponse);
        Assertions.assertTrue(apiResponse.getNotMatchedTrades().isEmpty());
        Assertions.assertTrue(apiResponse.getMissingPositions().isEmpty());
        Assertions.assertEquals(matchedTrade, apiResponse.getMatchedTrades().size());

        Assertions.assertEquals(extraPositionSize, apiResponse.getExtraPositions().size());
        Assertions.assertEquals(extraPositionQuantity, apiResponse.getExtraPositions().get(0).getQuantity());
    }


    @Test
    public void testSpreadTrade_ExpectedOneMatched() {

        List<TradeJson> tradeJsonList = new ArrayList<>();
        tradeJsonList.add(TradeGenerator.getSpreadTrade("ACOR2 210416C00001000", PositionType.SHORT, 12));
        //////////////////
        List<PositionJson> positionJsonList = new ArrayList<>();
        positionJsonList.add(PositionGenerator.getPosition("ACOR2 210416C00001000", PositionType.SHORT, 12));
        positionJsonList.add(PositionGenerator.getPosition("ACOR2 210416C00001000", PositionType.LONG, 12));

        ApiResponse apiResponse = TradeProcessor.parseTradesAndPositions(positionJsonList, tradeJsonList);

        int matchedTrade = 1;

        Assertions.assertNotNull(apiResponse);
        Assertions.assertTrue(apiResponse.getNotMatchedTrades().isEmpty());
        Assertions.assertTrue(apiResponse.getMissingPositions().isEmpty());
        Assertions.assertEquals(matchedTrade, apiResponse.getMatchedTrades().size());

    }

    @Test
    public void testTradeWithMultipleLegs_ExpectedOneMatched() {

        List<TradeJsonLeg> legs = new ArrayList<>();
        legs.add(new TradeJsonLeg("ACOR2 210416C00001000", PositionType.LONG));
        legs.add(new TradeJsonLeg("ACOR1 210416C00001000", PositionType.LONG));

        List<TradeJson> tradeJsonList = new ArrayList<>();
        tradeJsonList.add(TradeGenerator.getSimpleTrade(12, legs));
        //////////////////
        List<PositionJson> positionJsonList = new ArrayList<>();
        positionJsonList.add(PositionGenerator.getPosition("ACOR2 210416C00001000", PositionType.LONG, 12));
        positionJsonList.add(PositionGenerator.getPosition("ACOR1 210416C00001000", PositionType.LONG, 12));

        ApiResponse apiResponse = TradeProcessor.parseTradesAndPositions(positionJsonList, tradeJsonList);

        int matchedTrade = 1;

        Assertions.assertNotNull(apiResponse);

        Assertions.assertTrue(apiResponse.getNotMatchedTrades().isEmpty());
        Assertions.assertTrue(apiResponse.getMissingPositions().isEmpty());
        Assertions.assertTrue(apiResponse.getExtraPositions().isEmpty());
        Assertions.assertEquals(matchedTrade, apiResponse.getMatchedTrades().size());

    }


    @Test
    public void testTradeWithMultipleLegs_ExpectedTwoMatched() {

        List<TradeJsonLeg> legs = new ArrayList<>();
        legs.add(new TradeJsonLeg("ACOR2 210416C00001000", PositionType.LONG));
        legs.add(new TradeJsonLeg("ACOR1 210416C00001000", PositionType.LONG));

        List<TradeJson> tradeJsonList = new ArrayList<>();
        tradeJsonList.add(TradeGenerator.getSimpleTrade("ACOR5 210416C00001000", 5));
        tradeJsonList.add(TradeGenerator.getSimpleTrade(12, legs));
        //////////////////
        List<PositionJson> positionJsonList = new ArrayList<>();
        positionJsonList.add(PositionGenerator.getPosition("ACOR2 210416C00001000", PositionType.LONG, 12));
        positionJsonList.add(PositionGenerator.getPosition("ACOR1 210416C00001000", PositionType.LONG, 12));

        positionJsonList.add(PositionGenerator.getPosition("ACOR5 210416C00001000", PositionType.LONG, 5));

        ApiResponse apiResponse = TradeProcessor.parseTradesAndPositions(positionJsonList, tradeJsonList);

        int matchedTrade = 2;

        Assertions.assertNotNull(apiResponse);

        Assertions.assertTrue(apiResponse.getNotMatchedTrades().isEmpty());
        Assertions.assertTrue(apiResponse.getMissingPositions().isEmpty());
        Assertions.assertTrue(apiResponse.getExtraPositions().isEmpty());
        Assertions.assertEquals(matchedTrade, apiResponse.getMatchedTrades().size());

    }


    @Test
    public void testTradeWithMultipleLegs_CORRECT_SPREAD() {

        List<TradeJsonLeg> legs = new ArrayList<>();
        legs.add(new TradeJsonLeg("ACOR2 210416C00001000", PositionType.SHORT));
        legs.add(new TradeJsonLeg("ACOR1 210416C00001000", PositionType.LONG));

        List<TradeJson> tradeJsonList = new ArrayList<>();
        tradeJsonList.add(TradeGenerator.getSimpleTrade(12, legs));
        //////////////////
        List<PositionJson> positionJsonList = new ArrayList<>();
        positionJsonList.add(PositionGenerator.getPosition("ACOR2 210416C00001000", PositionType.LONG, 12));
        positionJsonList.add(PositionGenerator.getPosition("ACOR2 210416C00001000", PositionType.SHORT, 12));

        positionJsonList.add(PositionGenerator.getPosition("ACOR1 210416C00001000", PositionType.LONG, 12));

        ApiResponse apiResponse = TradeProcessor.parseTradesAndPositions(positionJsonList, tradeJsonList);

        int matchedTrade = 1;

        Assertions.assertNotNull(apiResponse);

        Assertions.assertTrue(apiResponse.getNotMatchedTrades().isEmpty());
        Assertions.assertTrue(apiResponse.getMissingPositions().isEmpty());
        Assertions.assertTrue(apiResponse.getExtraPositions().isEmpty());
        Assertions.assertEquals(matchedTrade, apiResponse.getMatchedTrades().size());

    }


    @Test
    public void testTradeWithMultipleLegs_ExpectedOneMatchedOneNonMatched() {

        List<TradeJsonLeg> legs = new ArrayList<>();
        legs.add(new TradeJsonLeg("ACOR2 210416C00001000", PositionType.LONG));
        legs.add(new TradeJsonLeg("ACOR1 210416C00001000", PositionType.LONG));

        List<TradeJsonLeg> legs2 = new ArrayList<>();
        legs2.add(new TradeJsonLeg("ACOR3 210416C00001000", PositionType.SHORT));
        legs2.add(new TradeJsonLeg("ACOR4 210416C00001000", PositionType.LONG));

        List<TradeJson> tradeJsonList = new ArrayList<>();
        tradeJsonList.add(TradeGenerator.getSimpleTrade(12, legs));
        tradeJsonList.add(TradeGenerator.getSimpleTrade(5, legs2));
        //////////////////
        List<PositionJson> positionJsonList = new ArrayList<>();
        positionJsonList.add(PositionGenerator.getPosition("ACOR2 210416C00001000", PositionType.LONG, 12));
        positionJsonList.add(PositionGenerator.getPosition("ACOR1 210416C00001000", PositionType.LONG, 12));

        positionJsonList.add(PositionGenerator.getPosition("ACOR3 210416C00001000", PositionType.LONG, 4));
        positionJsonList.add(PositionGenerator.getPosition("ACOR3 210416C00001000", PositionType.SHORT, 5));

        positionJsonList.add(PositionGenerator.getPosition("ACOR4 210416C00001000", PositionType.LONG, 5));

        ApiResponse apiResponse = TradeProcessor.parseTradesAndPositions(positionJsonList, tradeJsonList);

        int matchedTrade = 1;
        int nonMatchedTrade = 1;

        Assertions.assertNotNull(apiResponse);

        Assertions.assertFalse(apiResponse.getNotMatchedTrades().isEmpty());
        Assertions.assertFalse(apiResponse.getMissingPositions().isEmpty());
        Assertions.assertTrue(apiResponse.getExtraPositions().isEmpty());
        Assertions.assertEquals(matchedTrade, apiResponse.getMatchedTrades().size());
        Assertions.assertEquals(nonMatchedTrade, apiResponse.getNotMatchedTrades().size());
    }


    @Test
    public void testSpreadTrade_ExpectedOneMatchedOneNonMatched() {

        List<TradeJson> tradeJsonList = new ArrayList<>();
        tradeJsonList.add(TradeGenerator.getSpreadTrade("ACOR2 210416C00001000", PositionType.SHORT, 12));
        tradeJsonList.add(TradeGenerator.getSpreadTrade("ACOR3 210416C00001000", PositionType.SHORT, 5));
        //////////////////
        List<PositionJson> positionJsonList = new ArrayList<>();
        positionJsonList.add(PositionGenerator.getPosition("ACOR2 210416C00001000", PositionType.SHORT, 12));
        positionJsonList.add(PositionGenerator.getPosition("ACOR2 210416C00001000", PositionType.LONG, 12));
        positionJsonList.add(PositionGenerator.getPosition("ACOR3 210416C00001000", PositionType.SHORT, 5));

        ApiResponse apiResponse = TradeProcessor.parseTradesAndPositions(positionJsonList, tradeJsonList);

        int matchedTrade = 1;
        int nonMatchedTrade = 1;

        Assertions.assertNotNull(apiResponse);
        Assertions.assertFalse(apiResponse.getNotMatchedTrades().isEmpty());
        Assertions.assertFalse(apiResponse.getMissingPositions().isEmpty());
        Assertions.assertEquals(matchedTrade, apiResponse.getMatchedTrades().size());
        Assertions.assertEquals(nonMatchedTrade, apiResponse.getNotMatchedTrades().size());

    }

    @Test
    public void testSpreadTrade_ExpectedOneMatchedTwoNonMatchedOneExtraTwoMissing() {

        List<TradeJson> tradeJsonList = new ArrayList<>();
        tradeJsonList.add(TradeGenerator.getSpreadTrade("ACOR2 210416C00001000", PositionType.SHORT, 12));
        tradeJsonList.add(TradeGenerator.getSpreadTrade("ACOR3 210416C00001000", PositionType.SHORT, 5));
        tradeJsonList.add(TradeGenerator.getSpreadTrade("ACOR4 210416C00001000", PositionType.LONG, 8));
        //////////////////
        List<PositionJson> positionJsonList = new ArrayList<>();
        positionJsonList.add(PositionGenerator.getPosition("ACOR2 210416C00001000", PositionType.SHORT, 12));
        positionJsonList.add(PositionGenerator.getPosition("ACOR2 210416C00001000", PositionType.LONG, 12));

        positionJsonList.add(PositionGenerator.getPosition("ACOR3 210416C00001000", PositionType.LONG, 55));
        positionJsonList.add(PositionGenerator.getPosition("ACOR4 210416C00001000", PositionType.LONG, 6));

        ApiResponse apiResponse = TradeProcessor.parseTradesAndPositions(positionJsonList, tradeJsonList);

        int matchedTrade = 1;
        int nonMatchedTrade = 2;
        int extraPositionCount = 1;
        int missingPositionsSize = 2;
        int missingPositionQuantity = 2;

        Assertions.assertNotNull(apiResponse);
        Assertions.assertFalse(apiResponse.getNotMatchedTrades().isEmpty());
        Assertions.assertFalse(apiResponse.getMissingPositions().isEmpty());
        Assertions.assertFalse(apiResponse.getExtraPositions().isEmpty());
        Assertions.assertEquals(matchedTrade, apiResponse.getMatchedTrades().size());
        Assertions.assertEquals(nonMatchedTrade, apiResponse.getNotMatchedTrades().size());
        Assertions.assertEquals(extraPositionCount, apiResponse.getExtraPositions().size());
        Assertions.assertEquals(missingPositionsSize, apiResponse.getMissingPositions().size());
        Assertions.assertEquals(missingPositionQuantity, apiResponse.getMissingPositions().get(1).getQuantity());
    }


}