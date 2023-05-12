package com.golem.core.schemas.basicAbstractions;

import com.golem.core.schemas.basicInterfaces.Cell;

import java.util.List;

public abstract class AbstractCommand implements Cell {
    private String answer;
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }
    public abstract AbstractCommand useSignature (List<String> signature);
}
