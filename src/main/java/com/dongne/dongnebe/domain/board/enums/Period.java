package com.dongne.dongnebe.domain.board.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Period {
    DAY(1L), WEEK(7L);

    private Long value;
}
