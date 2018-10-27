package course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

import course.entity.User;
import course.repository.UserRepository;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserRepository repository;

    @RequestMapping(value = "signup")
    public String signup(Model model) {
        model.addAttribute("signup", new User());
        return "signup";
    }

    @RequestMapping(value = "saveUser", method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, @RequestParam(value = "id", required = false) Long userId) {
        String view;
        if (user.getRole() == null) {
            view = "user";
        } else {
            view = "redirect:/users";
        }
        if (!bindingResult.hasErrors()) { // validation errors
            if (user.getPassword().equals(user.getPasswordCheck())) { // check password match		
                String pwd = user.getPassword();
                BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
                String hashPwd = bc.encode(pwd);
                user.setPassword(hashPwd);
                if (userId == null) {
                    if (repository.findByUsername(user.getUsername()) == null) {
                        if (user.getRole() == null) {
                            user.setRole("USER");
                            repository.save(user);
                            return "redirect:/login";
                        } else {
                            repository.save(user);
                            return view;
                        }
                    } else {
                        bindingResult.rejectValue("username", "error.userexists", "Username already exists");
                        return view;
                    }
                } else {
                    repository.save(user);
                    return view;
                }
            } else {
                bindingResult.rejectValue("passwordCheck", "error.pwdmatch", "Passwords does not match");
                return view;
            }
        } else {
            return view;
        }
    }

    @RequestMapping("/users")
    public String index(Model model) {
        List<User> users = (List<User>) repository.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @RequestMapping(value = "addUser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @RequestMapping(value = "/editUser/{id}")
    public String editUser(@PathVariable("id") Long userId, Model model) {
        model.addAttribute("user", repository.findById(userId));
        return "editUser";
    }

    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") Long userId, Model model) {
        repository.deleteById(userId);
        return "redirect:/users";
    }
}
