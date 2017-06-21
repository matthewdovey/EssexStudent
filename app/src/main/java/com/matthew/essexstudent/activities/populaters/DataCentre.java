package com.matthew.essexstudent.activities.populaters;

public class DataCentre {

    private String fullName;
    private String email;
    private String degree;
    private String regNumb;
    private String year;
    private String tutor;
    private String mobile;
    private String address1;
    private String address2;
    private String city;
    private String county;
    private String postcode;

    public DataCentre(String name, String email, String degree,
                                    String regNumb, String year, String tutor,
                                    String mobile, String address1, String address2,
                                    String city, String county, String postcode) {
        this.fullName = name;
        this.email = email;
        this.degree = degree;
        this.regNumb = regNumb;
        this.year = year;
        this.tutor = tutor;
        this.mobile = mobile;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.county = county;
        this.postcode = postcode;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getRegNumb() {
        return regNumb;
    }

    public String getDegree() {
        return degree;
    }

    public String getYear() {
        return year;
    }

    public String getTutor() {
        return tutor;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getCity() {
        return city;
    }

    public String getCounty() {
        return county;
    }

    public String getPostcode() {
        return postcode;
    }
}
