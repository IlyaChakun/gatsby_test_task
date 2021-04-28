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
    public void testSpreadTrade() {

        List<TradeJson> tradeJsonList = new ArrayList<>();
        tradeJsonList.add(TradeGenerator.getSpreadTrade("ACOR2 210416C00001000", PositionType.SHORT,12));
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

    // если текущих позиций
    // трейд - позиция  = останется на следующий
    // missing ТО чего не хватает чтобы собрать трейд , трейд - позиция= мисинг

}