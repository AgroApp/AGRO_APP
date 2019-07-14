package com.example.sha.agro;

public class IndianbankView {
    private String Scheme;
    private String Purpose;
    private String Eligibility;
    private String Margin;
    private String Security;
    private String Amount_of_Loan;
    private String Interest_Rate;
    private String Repayment;

    public IndianbankView(){}


    public IndianbankView(String scheme, String purpose, String eligibility, String margin, String security, String amount_of_Loan, String repayment, String interest_Rate) {
        this.Scheme = scheme;
        this.Purpose = purpose;
        this.Eligibility = eligibility;
        this.Amount_of_Loan = amount_of_Loan;
        this.Margin = margin;
        this.Interest_Rate = interest_Rate;
        this.Repayment = repayment;
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

    public String getAmount_of_Loan() {
        return Amount_of_Loan;
    }

    public void setAmount_of_Loan(String amount_of_Loan) {
        Amount_of_Loan = amount_of_Loan;
    }

    public String getRepayment() {
        return Repayment;
    }

    public void setRepayment(String repayment) {
        Repayment = repayment;
    }

    public String getInterest_Rate() {
        return Interest_Rate;
    }

    public void setInterest_Rate(String interest_Rate) {
        Interest_Rate = interest_Rate;
    }
}
