package com.matthew.essexstudent.activities.populaters;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by matthew on 27/12/15.
 */
public class StudentEducation {

    private String fullName;
    private String regNumber;
    private String degree;
    private String currentYear;
    private String tutor;
    private Elements span;

    public void getStudentEducation(Document education) {

        Elements fullNameElement = education.select("#ContentMain_Marks_lblName");
        Elements regNumberElement = education.select("#ContentMain_Marks_lblRegNo");
        Elements degreeElement = education.select("#ContentMain_Marks_lblDegreeScheme");
        Elements currentYearElement = education.select("#ContentMain_Marks_lblYearOfStudy");
        Elements tutorElement = education.select("#ContentMain_course_lblPersonalTutor");

        fullName = fullNameElement.text();
        regNumber = regNumberElement.text();
        degree = degreeElement.text();
        currentYear = currentYearElement.text();
        tutor = tutorElement.text();

        Elements currentModulesElement = education.select("#divThisYearModules");
        span = currentModulesElement.select("span");
    }

    public String getFullName() {
        return fullName;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public String getDegree() {
        return degree;
    }

    public String getCurrentYear() {
        return currentYear;
    }

    public String getTutor() {
        return tutor;
    }

    public void /*String*/ getModules() {
        for (Element info:span) {
            System.out.println(info.text());
        }
    }
}

