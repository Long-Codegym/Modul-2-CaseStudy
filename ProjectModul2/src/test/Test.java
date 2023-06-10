package test;
import controller.UserRights;
import model.Role;
import model.Status;
import model.User;
import service.FileService;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import controller.UserRights;

import java.util.Scanner;

public class Test {
    static Scanner scanner = new Scanner(System.in);
    private static UserRights userRights = new UserRights();

    public static void login(){
        List<User> userList = FileService.readFile();
        boolean checkAdmin = false;
        for (User user:userList) {
            if ( user.getUsername().equals("ADMIN")){
                checkAdmin = true;
                break;
            }
        }
        if (!checkAdmin){
            userRights.createAccAdmin();
        }

        System.out.println("Đăng nhập ");
        System.out.println("Nhập username: ");
        String usernameOrID = scanner.nextLine().trim();
        System.out.println("Nhập password: ");
        String password = scanner.nextLine().trim();
        User user = FileService.login(usernameOrID, password);
        if (user != null) {
            // kiểm tra quyền đăng nhập
            if (user.getRole().name().equals(Role.ADMIN.name())) {
                // câu chào khi đăng nhập
                System.out.println(" Đăng nhập thành công \n"
                        + " Xin chào: Admin \n"
                        + "Với quyền truy cập: "
                        + user.getRole().name());
                // gọi đến các chức năng của admin
                menuAdmin();
            }
            if (user.getRole().name().equals(Role.TEACHER.name()) && user.getStatus().name().equals(Status.ACTIVE.name())) {
                System.out.println(" Đăng nhập thành công \n"
                        + " Xin chào:" + user.getName() +"\n"
                        + "Với quyền truy cập: "
                        + user.getRole().name());

                // user đăng nhập là giáo viên
                menuTeacher(user.getCode());
            }
            if (user.getRole().name().equals(Role.STUDENT.name()) && user.getStatus().name().equals(Status.ACTIVE.name())) {
                System.out.println(" Đăng nhập thành công \n"
                        + " Xin chào:" + user.getName() +"\n"
                        + "Với quyền try cập: "
                        + user.getRole().name());

                // user đăng nhập là sinh viên
                menuStudent(usernameOrID);
            }
            if (user.getRole().name().equals(Role.STUDENT.name()) && user.getStatus().name().equals(Status.PENDING.name())){
                System.out.println(" Tài khoản chưa được kích hoạt.");
                login();
            }
            if (user.getRole().name().equals(Role.STUDENT.name()) && user.getStatus().name().equals(Status.DELETE.name())) {
                System.out.println("");
                //Tài khoản bị xóa hoạc bị thu hồi
                System.out.println(" Tài khoản đã bị thu hồi.");
                login();
            }
        } else {
            System.out.println("Tài khoản hoặc mật khẩu không chính xác.");
            login();
        }


    }

    // tạo ra admin
//    private static void createAccAdmin() {
////        FileService.saveAccAdmin();
//        User userAdmin = new User("ADMIN","ADMIN",Role.ADMIN);
//
//    }





    // Menu
    private static void menuAdmin() {
        boolean check = true;
        while (check) {
            System.out.println("------------------Menu-------------------\n" +
                    "1. Thêm giáo viên.\n" +
                    "2. Hiển thị giáo viên đang dạy.\n" +
                    "3. Hiển thị sinh viên đang theo học và chuẩn bị học. \n" +
                    "4. Hiển thị các tài khoản chưa được kích hoạt.\n" +
                    "5. Kích hoạt tài khoản của sinh viên.\n" +
                    "6. Xóa tài khoản sinh viên.\n" +
                    "7. Xóa tài khoản giáo viên.\n" +
                    "8. Đăng xuất.");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    // Tao ra acc giao vien
                    userRights.addTeacher();
                    //FileService.saveFile(user);
//                List<User> users =  FileService.readFile();
                    break;
                case "2":
                    // Hiển thị giáo viên
                    System.out.println("Giáo viên đang dạy học");
                    userRights.showTeacher();
                    break;
                case "3":
                    // hiển thị học sinh
                    System.out.println("Hiển thị học sinh vẫn còn đang học và chuẩn bị vào học");
                case "4":
                    // Hiển thị các tài khoản chưa được kích hoạt của sinh viên
                    userRights.accPendingStudent();
                    break;
                case "5":
                    // Kich hoat tai khoan sv
                    System.out.println("Nhập danh sách id sinh viên cần kích hoạt," +
                            " lưu ý các ID cách nhau bằng dấu cách");
                    String inputId = scanner.nextLine();
                    String[] ids = inputId.split(" ");
                    for (String id : ids) {
                        userRights.activateAccountStudent(Integer.parseInt(id));
                    }
//                userRights.activateAccountStudent(Integer.parseInt(Arrays.toString(ids)));
                    break;
                case "6":
                    // xóa tài khoản sinh viên khi được yêu cầu
                    userRights.delleteAccountStudent();
                    break;
                case "7":
                    //xóa tài khoản giáo viên khi giáo viên đó nghỉ
                    userRights.delleteAccountTeacher();
                    break;
                case "8":
                    // đăng xuất tài khoản
                    login();
                    check = false;
                    break;
                default:

            }
        }
    }



    //menu sinh viên
    private static void menuTeacher(String codeClass) {
        boolean check = true;
        while (check) {
            System.out.println("------------------Menu---------------\n" +
                    "1. Hiển thị danh sách học sinh trong lớp.\n" +
                    "2. Hiển thị sinh viên theo ID \n" +
                    "3. Thêm sinh viên cho lớp học.\n" +
                    "4. Sửa thông tin  cho sinh viên.\n" +
                    "5. Sửa điểm môn học. \n" +
                    "6. Gửi yêu cầu thu hồi tài khoản đó nếu sinh viên đã tốt nghiệp hoặc đã nghỉ học.\n" +
                    "7. Đăng xuất.");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":// hiển thị danh sách sinh viên
                    userRights.showStudents(codeClass);
                    break;
                case "2": // tìm kiếm sinh viên theo ID
                    userRights.finalStudentByID(codeClass);
                    break;
                case "3": //Thêm sinh viên nhưng tài khoản chưa được kích hoạt
                    userRights.addStudent();
                    break;
                case "4": // Sửa thông tin
                    userRights.editInforStudent(codeClass);
                    break;
                case "5": // Sửa điểm cho sinh viên
                    userRights.editListSubject(codeClass);
                    break;
                case "6": // Yêu cầu xóa tài khoản sinh viên
                    userRights.delAccountStudent(codeClass);
                    break;
                case "7": // đăng xuất
                    login();
                    check = false;
                    break;
                default:
            }
        }
    }

    // menu của sinh viên
    private static void menuStudent(String userNameOrID) {
        boolean check = true;
        while (check) {
            System.out.println("------------------Menu---------------\n" +
                    "1. Hiển thị ra thông tin của mình.\n" +
                    "2. Đổi mật khẩu tài khoản.\n" +
                    "3. Đăng xuất.");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1": // xem thông tin của mình
                    userRights.seeMyInfor(userNameOrID);
                    break;
                case "2":// đổi mật khẩu
                    System.out.println("Nhập mật khẩu cũ:");
                    String passwordOld = scanner.nextLine().trim();
                    userRights.changePasswrod(userNameOrID,passwordOld);
                    break;
                case "3":// đăng xuất tài khoản
                    login();
                    check = false;
                    break;
                default:
            }
        }
    }
}
