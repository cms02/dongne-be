package com.dongne.dongnebe.domain.board.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BoardType {
    NORMAL("일반"), EVENT("행사");

    private String value;
}
