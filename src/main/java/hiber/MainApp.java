package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args)  {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru",
                new Car("Лада", 1118)));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru",
                new Car("Лада", 2107)));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru",
                new Car("Лада Гранта", 2190)));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru",
                new Car("Лада Самара", 2114)));


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = "+user.getId());
            System.out.println("First Name = "+user.getFirstName());
            System.out.println("Last Name = "+user.getLastName());
            System.out.println("Email = "+user.getEmail());
            System.out.println("Car = "+user.getCar());
            System.out.println();
        }

        List<User> userModelSeries = userService.getUsersByCarModelAndSeries("Лада", 2107);
        userModelSeries.forEach(System.out::println);
        context.close();
    }
}