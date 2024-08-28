package com.ericsson.testreporttool.database;

/**
 * Note: This can be refactored, I just called it Projects for now.
 */
public class Projects {

    //'CSA', 'CRA', 'CNA', 'CXP', 'Number', 'RPM', 'TAF', 'Test urls'
    private String CSA; // Seperate table (name/number)
    private String CRA; // Seperate table (name/number)
    private String CNA; // Seperate table (name/number)
    private String CXP; // Seperate table (all fields)
    private String number;
    private String RPM; //repoLink
    private String TAF;

    /**
     * Projects Constructer
     * @param CSA
     * @param CRA
     * @param CNA Project
     * @param CXP Product
     * @param number : Project Number
     * @param RPM : Repository Link
     * @param TAF
     */
    public Projects(String CSA, String CRA, String CNA, String CXP, String number, String RPM, String TAF) {
        this.CSA = CSA;
        this.CRA = CRA;
        this.CNA = CNA;
        this.CXP = CXP;
        this.number = number;
        this.RPM = RPM;
        this.TAF = TAF;
    }

    /**
     * Default Constructor
     */
    public Projects() {}

    public String getCSA() {
        return CSA;
    }

    public void setCSA(String CSA) {
        this.CSA = CSA;
    }

    public String getCRA() {
        return CRA;
    }

    public void setCRA(String CRA) {
        this.CRA = CRA;
    }

    public String getCNA() {
        return CNA;
    }

    public void setCNA(String CNA) {
        this.CNA = CNA;
    }

    public String getCXP() {
        return CXP;
    }

    public void setCXP(String CXP) {
        this.CXP = CXP;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRPM() {
        return RPM;
    }

    public void setRPM(String RPM) {
        this.RPM = RPM;
    }

    public String getTAF() {
        return TAF;
    }

    public void setTAF(String TAF) {
        this.TAF = TAF;
    }

    @Override
    public String toString() {
        return "Projects{" +
                "CSA='" + CSA + '\'' +
                ", CRA='" + CRA + '\'' +
                ", CNA='" + CNA + '\'' +
                ", CXP='" + CXP + '\'' +
                ", number='" + number + '\'' +
                ", RPM='" + RPM + '\'' +
                ", TAF='" + TAF + '\'' +
                '}';
    }
}
