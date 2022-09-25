package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        UserServiceImpl service = new UserServiceImpl();
        service.createUsersTable();
        service.saveUser("Sarah", "Connor", (byte) 35);
        service.saveUser("John", "Connor", (byte) 31);
        service.saveUser("Mila", "Connor", (byte) 39);
        service.saveUser("Jack", "Connor", (byte) 32);
        System.out.println(service.getAllUsers().toString());
        service.cleanUsersTable();
        service.dropUsersTable();

    }
}