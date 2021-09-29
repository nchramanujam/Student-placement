package com.student.web;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.student.model.Student;
import com.student.model.User;
import com.student.repository.StudentRepo;
import com.student.service.SecurityService;
import com.student.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
    
    @Autowired
    private StudentRepo studentRepo;
    

    

    @GetMapping("/registration")
    public String registration(Model model) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }
    
	/*
	 * @GetMapping("{tab}") public String tab(@PathVariable String tab) { if
	 * (Arrays.asList("tab1", "tab2", "tab3") .contains(tab)) { return "_" + tab; }
	 * 
	 * return "empty"; }
	 */
    @GetMapping({"/tab1"})
    public String tab1(Model model) {
        return "_tab1";
    }
    @GetMapping({"/tab2"})
    public String tab2(Model model) {
        return "_tab2";
    }
    @GetMapping({"/tab3"})
    public String tab3(Model model) {
        return "_tab3";
    }
    @GetMapping("/tab4")
	public ModelAndView getAllEmployees() {
		ModelAndView mav = new ModelAndView("_tab4");
		mav.addObject("students", studentRepo.findAll());
		return mav;
	}
    @PostMapping("/StudentDetails")
    public String StudentDetails(@ModelAttribute("student") Student student, BindingResult bindingResult) {
        userService.saveStudent(student);
        return "redirect:/welcome";
    }
    @PostMapping("/Company")
    public String Company(Model model) {
        return "redirect:/welcome";
    }
    @PostMapping("/EligibilityCriteria")
    public String Placement(@ModelAttribute("companyname") String comapanyname, @ModelAttribute("percentage") int percentage,  BindingResult bindingResult) {
    	userService.sendEmail(comapanyname,percentage);
        return "redirect:/welcome";
    }

}
