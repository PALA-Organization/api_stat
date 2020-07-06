package fr.pala.accounting.http.controller;

import fr.pala.accounting.user.dao.UserDAO;
import fr.pala.accounting.account.model.AccountModel;
import fr.pala.accounting.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class HomeController implements ErrorController {

    @Autowired
    UserDAO userDAO;

    private static final String PATH = "/error";

    @GetMapping("/")
    public String writeHello() {
        return "Welcome home";
    }

    @GetMapping(path = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserModel> test() {
        Optional<List<UserModel>> list = userDAO.getAllUsers();
        return list.get();
    }

    @RequestMapping(value = PATH)
    public String error() {
        return "Error";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

    @RequestMapping("/testroute")
    public String addUser() {
        ArrayList<AccountModel> accounts = new ArrayList<>();

        AccountModel account = new AccountModel("", 23.3, null);
        accounts.add(account);

        UserModel user = new UserModel(null, "Test", "test@test.fr", new Date(),
                new Date(), accounts);

        userDAO.addUser(user);
        return "Test user created";
    }
}
