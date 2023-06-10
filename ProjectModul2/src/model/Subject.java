package model;

import java.io.Serializable;

public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;
    private String nameCourse;
    private double point;

    public Subject() {
    }

    public Subject(String code, String nameCourse, double point) {
        this.code = code;
        this.nameCourse = nameCourse;
        this.point = point;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return
                " Mã môn học: " + code +
                ", Tên môn học: " + nameCourse +
                ", Điểm: " + point  +
                "\n";
    }
}
