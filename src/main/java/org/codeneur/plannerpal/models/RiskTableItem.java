package org.codeneur.plannerpal.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RiskTableItem {
    public StringProperty risk;
    public StringProperty riskStatus;


    public RiskTableItem(){
        this.risk = new SimpleStringProperty();
        this.riskStatus = new SimpleStringProperty();
    }

    public String getRisk() {
        return risk.get();
    }

    public StringProperty riskProperty() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk.set(risk);
    }

    public String getRiskStatus() {
        return riskStatus.get();
    }

    public StringProperty riskStatusProperty() {
        return riskStatus;
    }

    public void setRiskStatus(String riskStatus) {
        this.riskStatus.set(riskStatus);
    }
}
