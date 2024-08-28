package com.ericsson.testreporttool.database;

public class JenkinsData {
    private String number;
    private String testurls;
    private int pass;
    private int fail;
    private String date; //might change to Date type if needed
    private double passRate;
    private double failRate;

    public JenkinsData(String number, String testurls, int pass, int fail, String date) {
        this.number = number;
        this.testurls = testurls;
        this.pass = pass;
        this.fail = fail;
        this.passRate = calculatePassRate(pass, fail);
        this.failRate = calculateFailRate(pass, fail);
        this.date = date;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTesturls() {
        return testurls;
    }

    public void setTesturls(String testurls) {
        this.testurls = testurls;
    }

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    public int getFail() {
        return fail;
    }

    public void setFail(int fail) {
        this.fail = fail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPassRate() {
        return passRate;
    }

    public void setPassRate(double passRate) {
        this.passRate = passRate;
    }

    public double getFailRate() { return failRate; }


    private double calculatePassRate(int pass, int fail) {
        try {
            double total = (double) pass + (double) fail;
            return (pass / total) * 100.0;
        } catch (ArithmeticException a) {
            return 100.0;
        }
    }

    private double calculateFailRate(int pass, int fail) {
        if (pass == 0 && fail == 0) return 0;
        try {
            double total = (double) pass + (double) fail;
            return (fail / total) * 100.0;
        } catch (ArithmeticException a) {
            return 0.0;
        }
    }

    @Override
    public String toString() {
        return "JenkinsData{" +
                "number='" + number + '\'' +
                ", testurls='" + testurls + '\'' +
                ", pass=" + pass +
                ", fail=" + fail +
                ", date='" + date + '\'' +
                '}';
    }
}
