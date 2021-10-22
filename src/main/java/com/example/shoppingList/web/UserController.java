package com.example.shoppingList.web;

import com.example.shoppingList.model.binding.UserLoginBindModel;
import com.example.shoppingList.model.binding.UserRegBindModel;
import com.example.shoppingList.model.service.UserServiceModel;
import com.example.shoppingList.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        if (!model.containsAttribute("userRegBindModel")){
            model.addAttribute("userRegBindModel", new UserRegBindModel())
                    .addAttribute("passwordsNotMatch", false)
                    .addAttribute("usernameExists", false);
        }
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegBindModel userRegBindModel,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        // check for Input validations
        if (bindingResult.hasErrors() ||
                !userRegBindModel.getPassword().equals(userRegBindModel.getConfirmPassword()) ||
                userService.checkExistsByUsername(userRegBindModel.getUsername())) {
            redirectAttributes.addFlashAttribute("userRegBindModel", userRegBindModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegBindModel", bindingResult);
            // check for matching input Passwords
            if (!userRegBindModel.getPassword().equals(userRegBindModel.getConfirmPassword())){
                redirectAttributes.addFlashAttribute("passwordsNotMatch", true);
            }
            // check for existing Username in DB
            if (userService.checkExistsByUsername(userRegBindModel.getUsername())){
                redirectAttributes.addFlashAttribute("usernameExists", true);
            }
            return "redirect:/users/register";
        }
        // save/register the new User in DB
        userService.registerUser(modelMapper.map(userRegBindModel, UserServiceModel.class));
        return "redirect:/";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        if (!model.containsAttribute("userLoginBindModel")) {
            model.addAttribute("userLoginBindModel", new UserLoginBindModel())
                    .addAttribute("notFound", false);
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid UserLoginBindModel userLoginBindModel,
                        BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        // check for Input validations
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindModel", userLoginBindModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindModel", bindingResult);
            return "redirect:/users/login";
        }
        // check for Authentication (username and password)
        if (!userService.authenticate(userLoginBindModel.getUsername(), userLoginBindModel.getPassword())) {
            redirectAttributes.addFlashAttribute("userLoginBindModel", userLoginBindModel)
                    .addFlashAttribute("notFound", true);
            return "redirect:/users/login";
        }
        // login the user (as CurrentUser)
        userService.login(userLoginBindModel.getUsername());
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }
}
