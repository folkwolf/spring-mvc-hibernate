package mvchibernate.controller;

import mvchibernate.model.User;
import mvchibernate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("userList", userService.getAllUsers());
        return "index";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "userInfo";
    }

    @GetMapping("/add")
    public String addUser(@ModelAttribute("newUser") User newUser) {
        return "newUser";
    }

    @PostMapping("/add")
    public String addNewUser(@ModelAttribute("newUser") User newUser) {
        if(Objects.nonNull(newUser) && newUser.getAge() != 0 && newUser.getName() != null && newUser.getSurname() != null) {
            userService.saveUser(newUser);
        }
        return "redirect:/user";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "editUser";
    }

    @PatchMapping("/{id}")
    public String editUser(@PathVariable("id") int id, @ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/user/"+id;
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/user";
    }
}
