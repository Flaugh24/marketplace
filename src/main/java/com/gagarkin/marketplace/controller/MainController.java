package com.gagarkin.marketplace.controller;

import com.gagarkin.marketplace.model.User;
import com.gagarkin.marketplace.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class MainController {

    private static final Logger LOG = Logger.getLogger(MainController.class.getName());

    @Value("${spring.profiles.active}")
    private String profile;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String main(Model model, @AuthenticationPrincipal User user){

        HashMap<Object, Object> data = new HashMap<>();
        data.put("profile", user);
        data.put("products", productRepository.findAll());

        model.addAttribute("frontentData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));

        return "index";
    }

}
