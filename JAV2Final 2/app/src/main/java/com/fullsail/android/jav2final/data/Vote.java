// Ryan Backa
// JAV2 - 1612
// Vote

package com.fullsail.android.jav2final.data;


public class Vote {

    private String mChamber;
    private String mQuestion;
    private String mSession;
    private String mOutcome;

    public Vote() {
        mChamber = mQuestion =
                mSession = mOutcome = "";
    }

    public Vote(String chamber, String question, String session, String outcome) {
        mChamber = chamber;
        mQuestion = question;
        mSession = session;
        mOutcome = outcome;
    }

    public String getChamber() {
        return mChamber;
    }

    public void setChamber(String chamber) {
        mChamber = chamber;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public String getSession() {
        return mSession;
    }

    public void setSession(String session) {
        mSession = session;
    }

    public String getOutcome() {
        return mOutcome;
    }

    public void setOutcome(String outcome) {
        mOutcome = outcome;
    }
}
