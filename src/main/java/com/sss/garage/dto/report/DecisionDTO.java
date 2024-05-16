package com.sss.garage.dto.report;

public class DecisionDTO {
    private Long id;

    private Integer penaltySeconds;

    private Integer penaltyPoints;

    private String decisionDescription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPenaltySeconds() {
        return penaltySeconds;
    }

    public void setPenaltySeconds(Integer penaltySeconds) {
        this.penaltySeconds = penaltySeconds;
    }

    public Integer getPenaltyPoints() {
        return penaltyPoints;
    }

    public void setPenaltyPoints(Integer penaltyPoints) {
        this.penaltyPoints = penaltyPoints;
    }

    public String getDecisionDescription() {
        return decisionDescription;
    }

    public void setDecisionDescription(String decisionDescription) {
        this.decisionDescription = decisionDescription;
    }


}
