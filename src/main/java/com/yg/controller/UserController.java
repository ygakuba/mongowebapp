/**
 * 
 */
package com.yg.controller;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.yg.model.User;
import com.yg.service.UserRegistrationService;
import com.yg.service.impl.UserRegistrationServiceImpl;

/**
 * @author yves.gakuba
 * 
 */
@Controller
@RequestMapping("/")
public class UserController {

	@Autowired
	private ServletContext ctx;
	private InputStream configFile;
	private UserRegistrationService urs;

	private void init() {
		configFile = ctx
				.getResourceAsStream("/WEB-INF/spring-mongo-config.xml");
		urs = new UserRegistrationServiceImpl(configFile);
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome() {
		return "redirect:list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getAllUsers(ModelMap model) {
		init();
		model.addAttribute("users", urs.getAllUsers());

		return "list";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String welcomeName(ModelMap model) {
		model.addAttribute("user", null);

		return "userForm";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String addUser(
			@RequestParam String firstname,
			@RequestParam String lastname,
			@RequestParam String username,
			@RequestParam String email,
			@RequestParam Integer age,
			@RequestParam(value = "registrationDate", required = false) Date registrationDate) {
		init();
		User user = new User(firstname, lastname, username, email, age,
				registrationDate);
		urs.saveOrUpdateUser(user);

		return "redirect:list";
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public String getUserById(@PathVariable String id, ModelMap model) {
		init();
		model.addAttribute("user", urs.getUserById(id));

		return "userForm";
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
	public String updateUser(
			@PathVariable String id,
			@RequestParam String firstname,
			@RequestParam String lastname,
			@RequestParam String username,
			@RequestParam String email,
			@RequestParam Integer age,
			@RequestParam(value = "registrationDate", required = false) Date registrationDate) {

		init();

		User u = urs.getUserById(id);
		u.setFirstname(firstname);
		u.setLastname(lastname);
		u.setUsername(username);
		u.setEmail(email);
		u.setAge(age);
		u.setRegistrationDate(registrationDate);
		urs.saveOrUpdateUser(u);

		return "redirect:../list";
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public String deleteUserById(@PathVariable String id) {
		init();
		urs.deleteUser(id);

		return "redirect:../list";
	}

}
