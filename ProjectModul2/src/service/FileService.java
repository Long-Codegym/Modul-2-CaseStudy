package service;

import model.Role;
import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    static List<User> userList = new ArrayList<>();

//    public static void saveAccAdmin() {
//        try {
//
//            List<User> users = new ArrayList<>();
//
//            User admin = new User("admin", "admin", Role.ADMIN);
//
//            users.add(admin);
//
//            FileOutputStream f = new FileOutputStream
//                    ("acconusts.txt");
//
//            ObjectOutputStream o = new ObjectOutputStream(f);
//
//            o.writeObject(users);
//
//            o.close();
//            f.close();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            System.out.println("Error " + e);
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("Error " + e);
//        }
//    }

//    public static void saveAdmin(List<User> data) {
//        try {
//
//            FileOutputStream f = new FileOutputStream("acconusts.txt");
//
//            ObjectOutputStream o = new ObjectOutputStream(f);
//
//            o.writeObject(data);
//
//            o.close();
//            f.close();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            System.out.println("Error " + e);
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("Error " + e);
//        }
//    }
    public static void saveFile(List<User> data) {
        try {
            readFile();

            FileOutputStream f = new FileOutputStream("acconusts.txt");

            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(data);

            o.close();
            f.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error " + e);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error " + e);
        }
    }

    public static List<User> readFile() {
        try {

            FileInputStream f = new FileInputStream("acconusts.txt");

            ObjectInputStream o = new ObjectInputStream(f);

            List<User> newUserList = (List<User>) o.readObject();

            f.close();
            o.close();
            userList = newUserList;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    public static User login(String usernameOrID , String password) {
        userList = FileService.readFile();
        for (User users : userList) {
            if (users != null && users.getUsername().equals(usernameOrID)|| users.getId() == Integer.parseInt(usernameOrID) && users.getPassword().equals(password)) {
                return users;
            }
        }
        return null;
    }


}
