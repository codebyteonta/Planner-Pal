package org.codeneur.plannerpal.models;

public enum RiskStatus {
    SCHEDULE_RISK ("Schedule Risk"),
    BUDGET_RISK ("Budget Risk"),
    OPERATIONAL_RISK ("Operational Risk"),
    TECHNICAL_RISK ("Technical Risk"),
    PROGRAMMATIC_RISK ("Programmatic Risk");

    private String riskStatus;

    RiskStatus(String riskStatus) {
        this.riskStatus = riskStatus;
    }

    public String getRiskStatus() {
        return riskStatus;
    }

    public String[] getRiskStatuses() {
        String[] riskStatuses = new String[RiskStatus.values().length];
        int i = 0;
        for (RiskStatus riskStatus : RiskStatus.values()) {
            riskStatuses[i] = riskStatus.getRiskStatus();
            i++;
        }
        return riskStatuses;
    }
}
