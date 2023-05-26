package com.golem.core.schemas.basicAbstractions;

import com.golem.core.schemas.basicInterfaces.Cell;

import java.util.List;

public abstract class AbstractCommand implements Cell {
    private List<String> answer;
    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }
    public List<String> getAnswer() {
        return answer;
    }
    public abstract AbstractCommand useSignature (List<String> signature);
}
