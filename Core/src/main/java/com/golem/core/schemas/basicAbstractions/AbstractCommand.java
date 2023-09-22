package com.golem.core.schemas.basicAbstractions;

import com.golem.core.schemas.basicInterfaces.Cell;

import java.util.List;

public abstract class AbstractCommand implements Cell {
    private String login = null;

    public String getLogin() {
        return login;
    }

    private List<String> answer;
    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }
    public List<String> getAnswer() {
        return answer;
    }
    public boolean exitable () {
        return false;
    }

    @Override
    public void activate() {}

    public abstract AbstractCommand useSignature (List<String> signature);

    public AbstractCommand setLogin (String login) {
        this.login = login;
        return this;
    }
}
