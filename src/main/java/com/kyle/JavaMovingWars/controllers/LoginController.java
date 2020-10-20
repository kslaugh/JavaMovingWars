package com.kyle.JavaMovingWars.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kyle.JavaMovingWars.models.User;
import com.kyle.JavaMovingWars.services.UserService;
import com.kyle.JavaMovingWars.validators.UserValidator;

@RestController
public class LoginController {
	private final UserService userService;
    private final UserValidator userValidator;
    
    public LoginController(UserService userService, UserValidator u) {
        this.userService = userService;
        this.userValidator = u;
    }
    
    @GetMapping("/")
    public String index(Model model, @ModelAttribute("register") User user) {
    	List<String> states= new ArrayList<>(Arrays.asList(
    			"AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VA", "VT", "WA", "WI", "WV", "WY"));
    	model.addAttribute("states", states);
    	return "Login";
    }    
    @PostMapping("/")
    public String registerUser(Model model, @Valid @ModelAttribute("register") User user, BindingResult result, HttpSession session) {
    	userValidator.validate(user, result);
    	if(result.hasErrors()) {
    		List<String> states= new ArrayList<>(Arrays.asList(
        			"AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VA", "VT", "WA", "WI", "WV", "WY"));
        	model.addAttribute("states", states);
    		return "login.jsp";
    	}
    	User u=userService.registerUser(user);
    	session.setAttribute("user_id", u.getId());
    	return "redirect:/dashboard";
    }
    
    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, RedirectAttributes red) {
    	if(userService.authenticateUser(email, password)) {
    		User user=userService.findByEmail(email);
    		session.setAttribute("user_id", user.getId());
    		return "redirect:/dashboard";    		
    	} else {
    		red.addFlashAttribute("error", "Invalid Credentials");
    		return "redirect:/";
    	}
    } 
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }
}