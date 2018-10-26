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

import course.entity.SignupForm;
import course.entity.User;
import course.repository.UserRepository;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

    @Autowired
    private UserRepository repository;

    @RequestMapping(value = "signup")
    public String signup(Model model) {
        model.addAttribute("signupForm", new SignupForm());
        return "signup";
    }

    @RequestMapping(value = "saveUser", method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute("signupForm") SignupForm signupForm, BindingResult bindingResult) {
        System.out.println(bindingResult.toString());
        String view;
        if (signupForm.getRole() == null) {
            view = "signup";
        } else {
            view = "redirect:/users";
        }
        if (!bindingResult.hasErrors()) { // validation errors
            if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match		
                String pwd = signupForm.getPassword();
                BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
                String hashPwd = bc.encode(pwd);

                User newUser = new User();
                newUser.setPasswordHash(hashPwd);
                newUser.setUsername(signupForm.getUsername());
                if (signupForm.getRole() == null) {
                    newUser.setRole("USER");
                } else {
                    newUser.setRole(signupForm.getRole());
                }

                if (repository.findByUsername(signupForm.getUsername()) == null) {
                    repository.save(newUser);
                } else {
                    bindingResult.rejectValue("username", "error.userexists", "Username already exists");
                    return view;
                }
            } else {
                bindingResult.rejectValue("passwordCheck", "error.pwdmatch", "Passwords does not match");
                return view;
            }
        } else {
            return view;
        }
        return "redirect:/login";
    }

    @RequestMapping("/users")
    public String index(Model model) {
        List<User> users = (List<User>) repository.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @RequestMapping(value = "addUser")
    public String addUser(Model model) {
        model.addAttribute("signupForm", new SignupForm());
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
