package fr.pala.accounting.http.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;

import fr.pala.accounting.user.dao.UserDAO;
import fr.pala.accounting.entity.BusinessVolume;
import fr.pala.accounting.user.model.UserModel;

@RestController
public class BusinessVolumeController implements ErrorController {

    private static final String PATH = "/error";
    private final UserDAO userDao;

    public BusinessVolumeController() {
        this.userDao = new UserDAO();
    }

    @GetMapping(path = "business-volume", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<BusinessVolume> getBusinessVolume(@RequestParam String userId) {
        ArrayList<BusinessVolume> allAccountsBusinessVolume = new ArrayList<BusinessVolume>();
        Optional<UserModel> user = userDao.getUserById(userId);

        if(user.isEmpty()) {
            return null;
        }

        return allAccountsBusinessVolume;
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
    
}