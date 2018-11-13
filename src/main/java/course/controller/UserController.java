package course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

import course.entity.User;
import course.service.UserServiceImpl;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "saveUser", method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, @RequestParam(value = "id", required = false) Long userId, Model model) {
        if (!bindingResult.hasErrors()) { // validation errors
            if (user.getPassword() != null) {
                // encrypt password
                userService.encryptPassword(user);
            }
            if (userId == null) { // check if new user
                if (userService.findByUsername(user.getUsername()) == null) { // validate username
                    userService.saveUser(user);
                } else {
                    bindingResult.rejectValue("username", "error.userexists", "Username already exists");
                }
            } else {
                userService.saveUser(user);
            }
        } else {
            String title = (userId == null) ? "Add User" : "Edit User";
            model.addAttribute("title", title);
            return "userForm";
        }
        return "redirect:/users";
    }

    @RequestMapping("users")
    public String index(Model model) {
        List<User> users = (List<User>) userService.findAllByOrderByUsernameAsc();
        model.addAttribute("users", users);
        return "users";
    }

    @RequestMapping(value = "user/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("title", "Add User");
        return "userForm";
    }

    @RequestMapping(value = "user/edit/{id}")
    public String editUser(@PathVariable("id") Long userId, Model model) {
        model.addAttribute("user", userService.findUserById(userId));
        model.addAttribute("title", "Edit User");
        return "userForm";
    }

    @RequestMapping("user/{id}")
    public String showUser(@PathVariable("id") Long userId, Model model) {
        model.addAttribute("user", userService.findUserById(userId));
        model.addAttribute("title", "Show User");
        return "userShow";
    }

    @RequestMapping(value = "user/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") Long userId, Model model) {
        userService.deleteUserById(userId);
        return "redirect:/users";
    }
}
