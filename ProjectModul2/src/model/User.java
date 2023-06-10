package model;

import controller.ListSubject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User implements Serializable{

    private static final long serialVersionUID = 1L;
    private int id;
    private String code;
    private String name;
    private String sex;
    private Date birthDay;
    private String address;
    private String email;
    private String phone;
    private String username;
    private String password;
    private Enum<Role> role;

    private Enum<Status> status;
    private List<ListSubject> subjectList = new ArrayList<ListSubject>();

    public User(int id, String code, String name, String sex, Date birthDay, String address, String email, String phone, String username, String password, Enum<Role> role, Enum<Status> status, List<ListSubject> subjectList) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.sex = sex;
        this.birthDay = birthDay;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
        this.subjectList = subjectList;
    }

    public User(int id, String code, String name, String sex, Date birthDay, String address, String email, String phone, String username, String password, Role role, Status status) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.sex = sex;
        this.birthDay = birthDay;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
    }




    public User(String username, String password, Enum<Role> role) {
        this.id = 1;
        this.code = "1";
        this.name = "ADMIN";
        this.sex = "ko xác định";
        this.address = "ko có";
        this.email = "admin@gmail.com";
        this.phone = "3456789";
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = Status.ACTIVE;
//        this.subjectList = new ArrayList<>(Integer.parseInt("ADMIN"));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Enum<Role> getRole() {
        return role;
    }

    public void setRole(Enum<Role> role) {
        this.role = role;
    }

    public Enum<Status> getStatus() {
        return status;
    }

    public void setStatus(Enum<Status> status) {
        this.status = status;
    }

    public List<ListSubject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<ListSubject> subjectList) {
        this.subjectList = subjectList;
    }


    public String accountAdmin() {
        return "User{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", status=" + status +
                '}';
    }
    public String infoTeacher() {
        return "User{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthDay=" + birthDay +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", role=" + role +
                ", Trạng thái tài khoản" + status +
                '}';
    }
    public String inforStudent() {
        return "Thông tin sinh viên: \n" +
                "id=" + id +
                ", class=" + code +
                ", Tên sinh viên: '" + name +
                ", sex='" + sex +
                ", birthDay=" + birthDay + "\n" +
                ", address='" + address +
                ", email='" + email +
                ", phone='" + phone + "\n" +
                ", username='" + username  +
                ", Trạng thái tài khoảng: " + status +
                ", subjectList=" + subjectList +
                "\n";
    }
    public String inforStudentByID() {
        return "Thông tin sinh viên: \n" +
                "id=" + id +
                ", class=" + code +
                ", Tên sinh viên: '" + name +
                ", sex='" + sex +
                ", birthDay=" + birthDay + "\n" +
                ", address='" + address +
                ", email='" + email +
                ", phone='" + phone + "\n" +
                ", subjectList=" + subjectList +
                "\n";
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthDay=" + birthDay +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", status=" + status +
                ", subjectList=" + subjectList +
                '}';
    }
}
