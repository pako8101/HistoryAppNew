package HistoryAppGradleSecurity.seeders;

import HistoryAppGradleSecurity.service.CategoryService;
import HistoryAppGradleSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Seeder implements CommandLineRunner {
private final CategoryService categoryService;
private final UserService userService;


    @Autowired
    public Seeder(CategoryService categoryService, UserService userService){
        this.categoryService = categoryService;
        this.userService = userService;

    }

    @Override
    public void run(String... args) throws Exception {
       categoryService.seedCategories();
//       userService.seedUsers();
    }
}