package controller;

import model.Role;
import model.Status;
import model.User;
import service.FileService;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserRights {

    private static final String CHECK_CODE = "^[A-Z][0-9]{6}[GHIK]$"; // check mã học viên theo tên lớp học

    private static final String CHECK_NAME = "^([A-Za-zàáãạảăắằẳẵặâấầẩẫậèéẹẻẽêềếểễệđìíĩỉịòóõọỏôốồổỗộơớờởỡợùúũụủưứừửữựỳỵỷỹýÀÁÃẠẢĂẮẰẲẴẶÂẤẦẨẪẬÈÉẸẺẼÊỀẾỂỄỆĐÌÍĨỈỊÒÓÕỌỎÔỐỒỔỖỘƠỚỜỞỠỢÙÚŨỤỦƯỨỪỬỮỰỲỴỶỸÝ]+ )+" +
            "[A-Za-zàáãạảăắằẳẵặâấầẩẫậèéẹẻẽêềếểễệđìíĩỉịòóõọỏôốồổỗộơớờởỡợùúũụủưứừửữựỳỵỷỹýÀÁÃẠẢĂẮẰẲẴẶÂẤẦẨẪẬÈÉẸẺẼÊỀẾỂỄỆĐÌÍĨỈỊÒÓÕỌỎÔỐỒỔỖỘƠỚỜỞỠỢÙÚŨỤỦƯỨỪỬỮỰỲỴỶỸÝ]+$";
    private static final String CHECK_GMAIL = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
    private static final String CHECK_ADDRESS = ".+";

    Scanner scanner = new Scanner(System.in);
    List<User> userLists = new ArrayList<>();
    //     List<User> userListTeacher = new ArrayList<>();
//     List<User> usersPendingStudent = new ArrayList<>();
//     List<User> usersAdmin = new ArrayList<>();
    ListSubject subject = new ListSubject();

    // tạo admin
    public void createAccAdmin() {
//        FileService.saveAccAdmin();
        User userAdmin = new User("ADMIN", "ADMIN", Role.ADMIN);
        userLists.add(userAdmin);
        FileService.saveFile(userLists);
    }


    // ID tự tạo
    public int inputID() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        List<User> users = FileService.readFile();
        if (users != null && users.size() > 0) {
            for (User user : users) {
                if (user.getId() == number) {
                    inputID();
                }
            }
        }
        return number;
    }


    // check code Lớp
    public boolean checkCode(String codeStudent) {
        Pattern pattern = Pattern.compile(CHECK_CODE);
        Matcher matcher = pattern.matcher(codeStudent);
        return matcher.matches();
    }


    // nhập code lớp
    public String inputCode() {
        String code;
        System.out.println(" Nhập mã lớp có ít nhất 1 chữ cái đầu viết hoa và 6 chữ số và giờ lớp học lý thuyết (G,H,I,K) Vd: C032023H");
        code = scanner.nextLine();
        if (code.isEmpty()) {
            System.out.println(" Mã không đuợc để trống.");
            inputCode();
        }
        boolean checkCodes = checkCode(code);
        if (!checkCodes) {
            System.out.println(" nhập sai định dạng mã, nhập lại");
            inputCode();
        } else {
            return code;
        }
        return code;
    }


    // kiểm tra tên nhập vào
    public boolean checkName(String name) {
        Pattern pattern = Pattern.compile(CHECK_NAME);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public String inputName() {
        String name;
        System.out.println(" Nhập tên");
        name = scanner.nextLine();
        if (name.isEmpty()) {
            System.out.println(" Tên không được để trống.");
            return inputName();
        }
        Boolean checkname = checkName(name);
        if (!checkname) {
            System.out.println(" Tên không được chứa các ký tự đặc biệt");
            return inputName();
        } else {
            System.out.println(" tên đúng: " + name);
            return name;
        }
    }


    // giới tính

    public String inputSex() {
        String sex;
        System.out.println(" Nhập giới tính.");
        sex = scanner.nextLine();
        if (sex.isEmpty()) {
            System.out.println(" Không đươợc để trống.");
            inputSex();
        }
        return sex;
    }


    // ngày tháng năm sinh
    public Date inputBrithDay() {
        Date date = null;

        String brithDay;
        System.out.println(" Nhập ngày sinh đ/MM/yyyy");
        brithDay = scanner.nextLine();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            date = dateFormat.parse(brithDay);

        } catch (Exception e) {
            System.out.println(" Ngày nhập không đúng.");
        }
        return date;
    }


    //check địa chỉ
    public boolean checkAddress(String address) {
        Pattern pattern = Pattern.compile(CHECK_ADDRESS);
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
    }

    public String inputAddress() {
        String address;
        System.out.println(" Nhập địa chỉ, lưu ý bắt đầu với số nhà");
        address = scanner.nextLine().trim();
        if (address.isEmpty()) {
            System.out.println(" Địa chỉ không được để trống.");
            return inputAddress();
        }
        Boolean checkAddress = checkAddress(address);
        if (!checkAddress) {
            System.out.println("Tên địa chỉ nhập không đúng");
            return inputAddress();
        } else {
            System.out.println(" tên đúng: " + address);
            return address;
        }
    }


    // kiểm tra email nhập vào
    public boolean checkEmail(String email) {
        Pattern pattern = Pattern.compile(CHECK_GMAIL, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // nhập email
    public String inputEmail() {
        List<User> users = FileService.readFile();
        String email;
        System.out.println(" Nhập địa chỉ email");
        email = scanner.nextLine();
        if (email.isEmpty()) {
            System.out.println(" Địa chỉ không được để chống.");
            inputEmail();
        }
//        if (users != null && users.size() > 0) {
//            for (User user : users) {
//                if (user.getEmail().equals(email)) {
//                    inputEmail();
//                }
//            }
//        }
        boolean checkemail = checkEmail(email);
        if (checkemail) {
            return email;
        } else {
            System.out.println(" Nhập Lại email");
            return inputEmail();
        }
    }


    // nhập số điện thoại
    public String inputPhone() {
        String phone;
        System.out.println(" Nhập số điện thoại.");
        phone = scanner.nextLine();
        if (phone.isEmpty()) {
            System.out.println(" Không được để trống.");
            inputPhone();
        }
        // trước khi return còn phải kiểm tra nhập có đúng ko.
        return phone;
    }

    // tên tài khoản
//    public String inputUsername(String email) {
//        String username = email;
//        return username;
//    }

    // mật khẩu
    public String inputPassword() {
        String password = "abc";
        return password;
    }

    // thêm sinnh viên
    public User addStudent() {
        int id = inputID();
        String code = inputCode();
        String name = inputName();
        String sex = inputSex();
        Date date = inputBrithDay();
        String address = inputAddress();
        String email = inputEmail();
        String phone = inputPhone();
//        String user = inputUsername();
        String password = inputPassword();

        List<ListSubject> subjects = subject.addListSubject();

        User userStudent = new User(id, code, name, sex, date, address, email, phone, email,
                password, Role.STUDENT, Status.PENDING, subjects);
        System.out.println("Thêm thành công sinh viên." + userStudent.getId() + userStudent.getCode() +
                userStudent.getName() + userStudent.getSex() + userStudent.getBirthDay() + userStudent.getAddress() +
                userStudent.getEmail() + userStudent.getPhone() + userStudent.getUsername() + userStudent.getPassword() +
                userStudent.getStatus() + userStudent.getSubjectList());

        userLists.add(userStudent);
        FileService.saveFile(userLists);

        return userStudent;
    }


    // thêm môn học


    // thêm giáo viên
    public User addTeacher() {
        int id = inputID();
        String code = inputCode();
        String name = inputName();
        System.out.println(name);
        String sex = inputSex();
        Date date = inputBrithDay();
        String address = inputAddress();
        String email = inputEmail();
        String phone = inputPhone();
//        String user = inputUsername();
        String password = inputPassword();
//        List<Subject> subjects = subject.addListSubject();
        User user1 = new User(id, code, name, sex, date, address, email, phone, email, password, Role.TEACHER, Status.ACTIVE);
        System.out.println("Thêm thành công giáo viên: " + user1);
        userLists.add(user1);
        FileService.saveFile(userLists);
//        userListTeacher.add(new User(id, code, name, sex, date, address, email, phone, user, password, Role.TEACHER, Status.ACTIVE, subjects));
//        writerFile(4,userListTeacher);
        return user1;
    }


    // tài khoản ADMIN
    public List<User> addAdmin() {
        String userAdmin;
        System.out.println(" Nhập tên tài khoản Admin");
        userAdmin = scanner.nextLine();
        String password = inputPassword();
        userLists.add(new User(userAdmin, password, Role.ADMIN));
//        writerFile(3, usersAdmin);
//        choicePath(1);
        return userLists;
    }

    // hiển thị tất cả sinh viên
    public void adminShowInforStudent() {
        userLists = FileService.readFile();
        for (User user : userLists) {
            if (user.getRole().name().equals(Role.STUDENT.name())) {
                System.out.println(user.inforStudent());
            }
        }
    }

    // Menu ADMIN
    // hiển thị ra các tài khoản sinh viên chưa được kích hoạt
    public void accPendingStudent() {
        int count = 1;
        userLists = FileService.readFile();
        for (User pending : userLists) {
            if (pending.getStatus().name().equals(Status.PENDING.name())) {
                System.out.println(pending);
                count = 0;
            }
        }
        if (count == 1) {
            System.out.println("Ko có tài khoản nào cần kích hoạt.");
        }
    }


    //  nhập mảng ids  duyệt mảng id nhập từ bàn phím để kích hoạt cho các tài khoản sinh viên
    public void activateAccountStudent(int ids) {
        userLists = FileService.readFile();

        // Lấy danh sách tài khoản học sinh chưa kích hoạt
        for (int i = 0; i < userLists.size(); i++) {
            if (userLists.get(i) != null && userLists.get(i).getRole().name().equals(Role.STUDENT.name())
                    && userLists.get(i).getId() == ids) {
                userLists.get(i).setStatus(Status.ACTIVE);
                System.out.println("Kích hoạt tài khoản thành công." + userLists.get(i).getUsername());
            }
        }
        FileService.saveFile(userLists);
//        System.out.println(userLists);
    }


    // hiển thị danh sách tài khoản cần xóa
    public int showAccountStudentDel() {
        int count = 0;
        userLists = FileService.readFile();
        for (User user : userLists) {
            if (user != null && user.getRole().name().equals(Role.STUDENT.name())
                    && user.getStatus().name().equals(Status.DELETE.name())) {
                System.out.println("ID Student: " + user.getId() + "Code Student: " + user.getCode() + "Tên sinh viên: "
                        + user.getName() + "UserName: " + user.getUsername() + "Password: " + user.getPassword());
                count++;
            }
        }
        return count;
    }


    //xóa sinh viên
    public void delleteAccountStudent() { // xóa sinh viên
        int check = showAccountStudentDel();
        userLists = FileService.readFile();
        if (check != 0) {
//            for (User user : userLists) {
//                if (user != null && user.getRole().name().equals(Role.STUDENT.name())
//                        && user.getStatus().name().equals(Status.DELETE.name())) {
//                    userLists.remove(user);
//                }
//            }
            for (int i = 0; i < userLists.size(); i++) {
                if (userLists.get(i).getRole().name().equals(Role.STUDENT.name()) &&
                        userLists.get(i).getStatus().name().equals(Status.DELETE.name())) {
                    userLists.remove(userLists.get(i));
                }
            }
            System.out.println("Xóa thành công");
        } else {
            System.out.println("Không có tài khoản nào cần xóa.");
        }
        FileService.saveFile(userLists);
    }


    // xóa giáo viên
    public void delleteAccountTeacher() { // xóa giáo viên
        showTeacher();
        userLists = FileService.readFile();
        try {
            System.out.println(" nhập id của giáo viên muốn xóa: ");
            int id = Integer.parseInt(scanner.nextLine());
//        for (User user : userLists) {
//            if (user != null && user.getRole().name().equals(Role.TEACHER.name()) && user.getId() == id) {
//                userLists.remove(user);
//            }
//        }
            for (int i = 0; i < userLists.size(); i++) {
                if (userLists.get(i).getRole().name().equals(Role.TEACHER.name()) && userLists.get(i).getId() == id) {
                    userLists.remove(userLists.get(i));
                    System.out.println("Xóa thành công tài khoản giáo viên: " + userLists.get(i).getName());
                }
            }
            FileService.saveFile(userLists);
        } catch (Exception e) {

        }
    }

    // hiển thị sinh viên
    public void showTeacher() {
        userLists = FileService.readFile();
        for (User user : userLists) {
            if (user != null && user.getRole().name().equals(Role.TEACHER.name())) {
                System.out.println("ID của giáo viên:" + user.getId() + " " + "Lớp giáo viên đó quản lý: " + user.getCode() +
                        "\n" + "Tên giáo viên: " + user.getName() + " " + "Quyền của giáo viên: " + user.getRole() + "\n" +
                        "Tên tài khoản: " + user.getUsername() + " " + "Mật khẩu:" + user.getPassword() + "\n");
            }
        }
    }


// menu giáo viên


    // hiển thị sinh viên
    public void showStudents(String codeClass) {
        userLists = FileService.readFile();
        for (User user : userLists) {
            if (user.getCode().equals(codeClass) && user.getRole().name().equals(Role.STUDENT.name())) {
                System.out.println(user.inforStudent());
            }
        }
    }



    // hiển thị sinh viên theo id đã kích hoạt tài khoản
    public void finalStudentByID(String codeClass){
        userLists = FileService.readFile();
        try{
            System.out.println("Nhập ID của sinh viên muốn tìm kiếm:");
            int iD = Integer.parseInt(scanner.nextLine());
            boolean check = false;
            for (int i = 0; i <userLists.size(); i++) {
                if (userLists.get(i).getId() == iD && userLists.get(i).getCode().equals(codeClass)
                        && userLists.get(i).getRole().name().equals(Role.STUDENT.name())
                        && userLists.get(i).getStatus().name().equals(Status.ACTIVE.name())){
                    check = true;
                    System.out.println(userLists.get(i).inforStudentByID());
                }
            }if (!check){
                System.out.println("Không tìm thấy sinh viên có ID: " + iD +
                        " Trong lớp học:" + codeClass + "\n");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    // sửa thông tin sinh viên
    public void editInforStudent(String codeClass) {
        try {
        userLists = FileService.readFile();
        System.out.println("Nhập ID của sinh viên muốn sửa thông tin.");
        int iD = Integer.parseInt(scanner.nextLine());
        boolean check = false;
            for (int i = 0; i < userLists.size(); i++) {
                if (userLists.get(i).getId() == iD && userLists.get(i).getCode().equals(codeClass)
                        && userLists.get(i).getRole().name().equals(Role.STUDENT.name())
                        && userLists.get(i).getStatus().name().equals(Status.ACTIVE.name())) {
                    check = true;
                    userLists.get(i).setCode(inputCode());
                    userLists.get(i).setBirthDay(inputBrithDay());
                    userLists.get(i).setAddress(inputAddress());
                    userLists.get(i).setPhone(inputPhone());
                }
            }if (!check){
                System.out.println("Không tìm thấy sinh viên có ID: " + iD +
                        " Trong lớp học:" + codeClass + "\n");
            }
            FileService.saveFile(userLists);
        } catch (Exception e) {
        }
    }


    // sửa điểm
    public void editListSubject(String codeClass) {
        try {
            System.out.println("Nhập iD học sinh muốn sửa:");
            int iD = Integer.parseInt(scanner.nextLine());
            userLists = FileService.readFile();
            boolean check = false;
            for (int i = 0; i < userLists.size(); i++) {
                if (userLists.get(i).getId() == iD &&
                        userLists.get(i).getCode().equals(codeClass) &&
                        userLists.get(i).getRole().name().equals(Role.STUDENT.name()) &&
                        userLists.get(i).getStatus().name().equals(Status.ACTIVE.name())) {
                    // truyền list subject vào bên hàm sửa
//                subject.editSubject(userLists.get(i).getSubjectList())
                    // luu lại hàm sửa
                    check = true;
                    userLists.get(i).setSubjectList(subject.editSubject(userLists.get(i).getSubjectList()));
                }
            }
            if (!check) {
                System.out.println("Không tìm thấy học sinh có id:" + iD +
                        " Trong lớp học:" + codeClass + "\n");
            }
            FileService.saveFile(userLists);
        } catch (Exception e) {}
    }


    // gửi yêu cầu xóa tài khoản cho Admin
    public void delAccountStudent(String codeClass) {
        try {
            userLists = FileService.readFile();
            System.out.println(" nhập id muốn yêu cầu hủy tài khoản của sinh viên:");
            int id = Integer.parseInt(scanner.nextLine());
            boolean check = false;
            for (int i = 0; i < userLists.size(); i++) {
                if (userLists.get(i).getId() == id
                        && userLists.get(i).getCode().equals(codeClass)
                        && userLists.get(i).getRole().name().equals(Role.STUDENT.name())
                        && userLists.get(i).getStatus().name().equals(Status.ACTIVE.name())) {
                    userLists.get(i).setStatus(Status.DELETE);
                    check = true;
                    System.out.println(" Gửi yêu cầu xóa tài khoản thành công với id và tên học sinh: "
                            + userLists.get(i).getId() + userLists.get(i).getName());
                }
            }
            if (!check) {
                System.out.println("Không tìm thấy sinh viên có ID: " + id
                        + " Trong lớp học: " + codeClass + "\n");
            }
            FileService.saveFile(userLists);
        } catch (Exception e) {

        }
    }


    // menu sinh viên


    // in ra thông tin của sinh viên đó bằng tài khoản sinh viên đó
    public User seeMyInfor(String userNameOrID) { // hiển thị thông tin sinh viên đó
        userLists = FileService.readFile();
        for (User user : userLists) {
            if (user.getUsername().equals(userNameOrID) || user.getId() == Integer.parseInt(userNameOrID)) {
                System.out.println(user);
                return null;
            }
        }
        return null;
    }

    public void changePasswrod(String userNameOrID, String passwrod) {  // đổi mật khẩu
        userLists = FileService.readFile();
        for (int i = 0; i < userLists.size(); i++) {
            if (userLists.get(i).getUsername().equals(userNameOrID) || userLists.get(i).getId() == Integer.parseInt(userNameOrID) && userLists.get(i).getPassword().equals(passwrod)) {
                System.out.println("Nhập mật khẩu mới:");
                String passwordNew = scanner.nextLine().trim();
                userLists.get(i).setPassword(passwordNew);
                System.out.println(" đổi mật khẩu thành công.");
            }
            FileService.saveFile(userLists);
        }
    }

}

