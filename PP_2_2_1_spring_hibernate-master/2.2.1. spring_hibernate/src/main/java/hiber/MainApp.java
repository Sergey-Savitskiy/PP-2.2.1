package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car redCar = new Car("Ferrari", 1);
      Car blueCar = new Car("Porsche", 2);
      Car whiteCar = new Car("Honda", 3);
      Car blackCar = new Car("Toyota", 1);

      User user1 = userService.add(new User("Sergey", "Savickiy", "pochta@mail.ru"));
      User user2 = userService.add(new User("Roman", "Milkovskiy", "romi@mail.ru"));
      User user3 = userService.add(new User("Mihail", "Minin", "bfuwerf@mail.ru"));
      User user4 = userService.add(new User("Aleksey", "Bichkov", "brgr@mail.ru"));

      userService.add(user1.setCar(redCar).setUser(user1));
      userService.add(user2.setCar(blueCar).setUser(user2));
      userService.add(user3.setCar(whiteCar).setUser(user3));
      userService.add(user4.setCar(blackCar).setUser(user4));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      System.out.println(userService.getUserByCar(blueCar.getModel(), blueCar.getSeries()));
      System.out.println(userService.getUserByCar("Porsche", 2));

      context.close();
   }
}
