package ru.pirateway.se.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Roman Kravchenko
 */

@Getter
@Setter
public class ForkDto {

    private String time;

    private String percent;

    private String bookmaker1;

    private String bookmaker2;

    private String gameEvent1;

    private String gameEvent2;

    private String bet1;

    private String bet2;

    private String coefficient1;

    private String coefficient2;

    @Override
    public String toString() {
        return "ForkDto{" +
                "time='" + time + '\'' +
                ", percent='" + percent + '\'' +
                ", bookmaker1='" + bookmaker1 + '\'' +
                ", bookmaker2='" + bookmaker2 + '\'' +
                ", gameEvent1='" + gameEvent1 + '\'' +
                ", gameEvent2='" + gameEvent2 + '\'' +
                ", bet1='" + bet1 + '\'' +
                ", bet2='" + bet2 + '\'' +
                ", coefficient1='" + coefficient1 + '\'' +
                ", coefficient2='" + coefficient2 + '\'' +
                '}';
    }

}
