package controller;

import model.Subject;
import model.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListSubject implements Serializable {
    private static final long serialVersionUID = 1L;
    Scanner scanner = new Scanner(System.in);
    List<Subject> subjectList = new ArrayList<>();
//    Subject subject = new Subject();

    // Nhập mã môn học
    public String inputCode() {
        String code;
        System.out.println(" Nhập mã môn học: ");
        code = scanner.nextLine();
        if (code.isEmpty()) {
            System.out.println(" Mã môn học không được để trống.");
            inputCode();
        }
        return code;
    }

    // nhập tên môn học
    public String inputNameCoruse() {
        String nameCourse;
        System.out.println(" Nhập tên môn học:");
        nameCourse = scanner.nextLine();
        if (nameCourse.isEmpty()) {
            System.out.println(" Tên Môn học không được để trống.");
            inputNameCoruse();
        }
        return nameCourse;
    }

    public double inputPoint() {
        double point = 0.0;
        System.out.println("Nhập điểm");
        point = Double.parseDouble(scanner.nextLine());
        return point;
    }

    public List addListSubject() {
        boolean check = true;
        while (check) {
            System.out.println("1. Tiếp tục thêm môn học. \n"
                    + "2. Dừng không thêm môn học.");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    String code = inputCode();
                    String nameCourse = inputNameCoruse();
                    double point = inputPoint();
                    subjectList.add(new Subject(code, nameCourse, point));
                    System.out.println(subjectList.toString());
                    break;
                case "2":
                    check = false;
                    break;
                default:
            }
        }
        return subjectList;
    }


    public List editSubject(List subjecs){
        System.out.println("Nhập mã môn học cần sửa điểm:");
        String codeSub = scanner.nextLine();
        subjectList = subjecs;
        for (int i = 0; i <subjectList.size() ; i++) {
            if (subjectList.get(i).getCode().equals(codeSub))   {
                subjectList.get(i).setPoint(inputPoint());
            }
        }
        return subjectList;
    }
}

