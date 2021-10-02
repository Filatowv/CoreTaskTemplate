package jm.task.core.jdbc;



import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {


    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Ivan", "Sudakov", (byte) 21);
        System.out.println("User с именем - Ivan добавлен в базу данных");
        userService.saveUser("Mark", "Ivanov", (byte) 27);
        System.out.println("User с именем - " + "Mark" + " добавлен в базу данных");
        userService.saveUser("Anton", "Petrov", (byte) 35);
        System.out.println("User с именем - " + "Anton" + " добавлен в базу данных");
        userService.saveUser("Boris", "Volkov", (byte) 60);
        System.out.println("User с именем - " + "Boris" + " добавлен в базу данных");

        List<User> users = userService.getAllUsers();
        users.forEach(x -> System.out.println(x.toString()));

        userService.cleanUsersTable();
//        userService.dropUsersTable();
    }
}
