package by.chakun;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PositionType {

    SHORT("short"),
    LONG("long");

    private final String positionTypeName;

}
