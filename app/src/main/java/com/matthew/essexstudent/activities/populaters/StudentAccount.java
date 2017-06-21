package com.matthew.essexstudent.activities.populaters;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class StudentAccount {

    private String firstName;
    private String surname;
    private String email;
    private String telephone;
    private String mobile;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String county;
    private String postCode;

    public void getStudentDetails(Document details) {
        Elements firstNameElement = details.select("#ContentMain_updateAddress1_tbOtherNames");
        Elements surnameElement = details.select("#ContentMain_updateAddress1_tbSurname");
        Elements emailElement = details.select("#lblName");
        Elements telephoneElement = details.select("#ContentMain_updateAddress1_tbTermTimeTelephone");
        Elements mobileElement = details.select("#ContentMain_updateAddress1_tbTermTimeMobileNumber");
        Elements addressLine1Element = details.select("#ContentMain_updateAddress1_tbTermTimeAddress1");
        Elements addressLine2Element = details.select("#ContentMain_updateAddress1_tbTermTimeAddress2");
        Elements cityElement = details.select("#ContentMain_updateAddress1_tbTermTimeAddress3");
        Elements countyElement = details.select("#ContentMain_updateAddress1_tbTermTimeAddress4");
        Elements postCodeElement = details.select("#ContentMain_updateAddress1_tbTermTimePostcode");

        firstName = firstNameElement.attr("value");
        surname = surnameElement.attr("value");
        email = emailElement.text();
        telephone = telephoneElement.attr("value");
        mobile = mobileElement.attr("value");
        addressLine1 = addressLine1Element.attr("value");
        addressLine2 = addressLine2Element.attr("value");
        city = cityElement.attr("value");
        county = countyElement.attr("value");
        postCode = postCodeElement.attr("value");
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getCity() {
        return city;
    }

    public String getCounty() {
        return county;
    }

    public String getPostCode() {
        return postCode;
    }
}

