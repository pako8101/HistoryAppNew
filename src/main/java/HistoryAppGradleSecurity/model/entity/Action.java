package HistoryAppGradleSecurity.model.entity;


import HistoryAppGradleSecurity.model.enums.ActionEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "actions")
public class Action extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(name = "action")
    private ActionEnum action;

    public Action() {
    }

    public ActionEnum getAction() {
        return action;
    }

    public void setAction(ActionEnum function) {
        this.action = function;
    }
}

