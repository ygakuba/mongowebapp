/**
 * 
 */
package com.yg.controller;

import java.io.InputStream;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yg.model.User;
import com.yg.service.UserRegistrationService;
import com.yg.service.impl.UserRegistrationServiceImpl;

/**
 * @author yves.gakuba
 *
 */
@Controller
@RequestMapping("/rest")
public class RestRegistrationController {

	@Autowired
	private ServletContext ctx;
	private InputStream configFile;
	private UserRegistrationService urs;

	private void init() {
		configFile = ctx
				.getResourceAsStream("/WEB-INF/spring-mongo-config.xml");
		urs = new UserRegistrationServiceImpl(configFile);
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ResponseBody
	public String createUser(@RequestBody User user) {
		init();
		urs.saveOrUpdateUser(user);

		return "200 OK";
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public @ResponseBody
	User getXMLDetailsForUserById(@PathVariable String id, ModelMap model) {
		init();

		return urs.getUserById(id);
	}
	
}
