package course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, @RequestParam(value = "id", required = false) Long userId) {
        if (!bindingResult.hasErrors()) { // validation errors
            if (user.getPassword().equals(user.getPasswordCheck())) { // check password match		
                if (userService.findByUsername(user.getUsername()) == null) { // validate username
                    String pwd = user.getPassword();
                    BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
                    String hashPwd = bc.encode(pwd);
                    user.setPassword(hashPwd);
                    userService.saveUser(user);
                    return "redirect:/users";
                } else {
                    bindingResult.rejectValue("username", "error.userexists", "Username already exists");
                }
            } else {
                bindingResult.rejectValue("passwordCheck", "error.pwdmatch", "Passwords does not match");
            }
        }
        return "userForm";
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
