package com.matthew.essexstudent.activities.connections;

import com.matthew.essexstudent.activities.activities.LoginActivity;

import org.apache.commons.codec.binary.Base64;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class UniversityLogin {

    private Document detailsPage;
    private Document educationPage;
    private Document supportPage;

    private String base64login;

    public UniversityLogin(String username, String password) {
        System.out.println("Username: "+username+" Password: "+password);
        String login = username + ":" + password;
        this.base64login = new String(Base64.encodeBase64(login.getBytes()));
    }

    public void login(){
        String url = "https://www.essex.ac.uk/myessex/";
        try {
            detailsPage = Jsoup.connect(url).header("Authorization", "Basic " + base64login).get();

            System.out.println(">> Connection Successful");

        } catch (IOException ex) {

            System.out.println(">> Connection To "+url+" Failed");
        }
    }

    public void education() {
        String educationURL = "https://www.essex.ac.uk/myessex/education.aspx";
        try {
            educationPage = Jsoup.connect(educationURL).header("Authorization", "Basic " + base64login).get();
        } catch (IOException ex) {
            System.out.println("Connection To "+educationURL+" Failed");
        }
    }

    public void support() {
        String supportURL = "https://www.essex.ac.uk/myessex/support.aspx";
        try {
            supportPage = Jsoup.connect(supportURL).header("Authorization", "Basic " + base64login).get();
        } catch (IOException ex) {
            System.out.println("Connection To "+supportURL+" Failed");
        }
    }

    //Returning student
    public Document getStudentDetails() {
        return detailsPage;
    }

    public Document getStudentEducation() {
        return educationPage;
    }

    public Document getStudentSupport() {
        return supportPage;
    }
}
