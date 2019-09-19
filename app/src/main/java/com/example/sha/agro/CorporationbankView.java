package com.example.sha.agro;

public class CorporationbankView {

    private String Scheme;
    private String Purpose;
    private String Eligibility;
    private String Margin;
    private String Security;

    public CorporationbankView(){}


    public CorporationbankView(String scheme, String purpose, String eligibility, String margin, String security) {
        this.Scheme = scheme;
        this.Purpose = purpose;
        this.Eligibility = eligibility;
        this.Margin = margin;
        this.Security = security;
    }

    public String getScheme() {
        return Scheme;
    }

    public void setScheme(String scheme) {
        Scheme = scheme;
    }

    public String getPurpose() {
        return Purpose;
    }

    public void setPurpose(String purpose) {
        Purpose = purpose;
    }

    public String getEligibility() {
        return Eligibility;
    }

    public void setEligibility(String eligibility) {
        Eligibility = eligibility;
    }

    public String getMargin() {
        return Margin;
    }

    public void setMargin(String margin) {
        Margin = margin;
    }

    public String getSecurity() {
        return Security;
    }

    public void setSecurity(String security) {
        Security = security;
    }
}
