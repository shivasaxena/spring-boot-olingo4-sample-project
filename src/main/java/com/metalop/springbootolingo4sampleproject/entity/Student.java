package com.metalop.springbootolingo4sampleproject.entity;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Student {

    String studentID;
    String name;
    LocalDateTime dateOfJoining;
    int marks;
    private byte[] picture;
    private String longTextDescription;

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDateTime dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getLongTextDescription() {
        return longTextDescription;
    }

    public void setLongTextDescription(String longTextDescription) {
        this.longTextDescription = longTextDescription;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID='" + studentID + '\'' +
                ", name='" + name + '\'' +
                ", dateOfJoining=" + dateOfJoining +
                ", marks=" + marks +
                ", picture=" + Arrays.toString(picture) +
                ", longTextDescription='" + longTextDescription + '\'' +
                '}';
    }
}
