package com.roof.fpa.cardtestresult.entity;

public class SimilerResult {
    private GeneralCardTestCustomerResult myResult;
    private GeneralCardTestCustomerResult friendResult;
    private String similerDefn;
    private float degreeScore;
    private float compleScore;

    public GeneralCardTestCustomerResult getMyResult() {
        return myResult;
    }

    public void setMyResult(GeneralCardTestCustomerResult myResult) {
        this.myResult = myResult;
    }

    public GeneralCardTestCustomerResult getFriendResult() {
        return friendResult;
    }

    public void setFriendResult(GeneralCardTestCustomerResult friendResult) {
        this.friendResult = friendResult;
    }

    public String getSimilerDefn() {
        return similerDefn;
    }

    public void setSimilerDefn(String similerDefn) {
        this.similerDefn = similerDefn;
    }

    public float getDegreeScore() {
        return degreeScore;
    }

    public void setDegreeScore(float degreeScore) {
        this.degreeScore = degreeScore;
    }

    public float getCompleScore() {
        return compleScore;
    }

    public void setCompleScore(float compleScore) {
        this.compleScore = compleScore;
    }
}
