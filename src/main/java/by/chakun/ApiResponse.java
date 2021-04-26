package by.chakun;

import java.util.ArrayList;
import java.util.List;

public class ApiResponse {

    private List<PositionJsonProxy> extraPositions = new ArrayList<>();

    private List<PositionJsonProxy> missingPositions = new ArrayList<>();


    public List<PositionJsonProxy> getExtraPositions() {
        return extraPositions;
    }

    public void setExtraPositions(List<PositionJsonProxy> extraPositions) {
        this.extraPositions = extraPositions;
    }

    public List<PositionJsonProxy> getMissingPositions() {
        return missingPositions;
    }

    public void setMissingPositions(List<PositionJsonProxy> missingPositions) {
        this.missingPositions = missingPositions;
    }


    @Override
    public String toString() {
        return "ApiResponse{" +
                "extraPositions=" + extraPositions +
                ", missingPositions=" + missingPositions +
                '}';
    }
}
