package com.its.samplelearning;

public class StudentModel {

    private String stuId;
    private String stuName;
    private String age;

    public StudentModel(String stuId, String stuName, String age) {
        this.stuId = stuId;
        this.stuName = stuName;
        this.age = age;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
