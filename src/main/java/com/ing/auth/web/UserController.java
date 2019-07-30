package com.ing.auth.web;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.ing.auth.model.Accounts;
import com.ing.auth.model.RestClock;
import com.ing.auth.model.User;
import com.ing.auth.service.AccountsService;
import com.ing.auth.service.SecurityService;
import com.ing.auth.service.UserService;
import com.ing.auth.validator.UserValidator;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private AccountsService accountService;

	@Autowired
	private UserValidator userValidator;

	LocalTime startTime = LocalTime.of(8, 0);
	LocalTime endTime = LocalTime.of(17, 0);

	@GetMapping("/registration")
	public String registration(Model model) {
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
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");
		model.addAttribute("userForm", new User());
		return "login";
	}

	@GetMapping({ "/", "/welcome" })
	public String welcome(Model model) {
		LocalTime lt = LocalTime.now();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User u = userService.findByUsername(auth.getName());
		System.out.println("welcome     " + auth.getName());
		Accounts acc = accountService.findByUsername(auth.getName());
		if (acc != null) {
			u.setSavingAccount(acc.getAccountId());
		}
		model.addAttribute("currentUser", u);
		model.addAttribute("time", lt.toString());

		return "welcome";
	}

	@PostMapping("/opensavingaccount")
	public String openSavingAcc(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LocalTime lt = LocalTime.now();
		if (lt.isAfter(startTime) && lt.isBefore(endTime)) {
			Accounts acc = new Accounts();
			acc.setAccountId(UUID.randomUUID().toString());
			acc.setUsername(auth.getName());
			accountService.save(acc);
		}
		return "redirect:/welcome";

	}

}
