/**
 * 
 */
package com.yg.service.impl;

import java.io.InputStream;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.yg.config.SpringMongoConfig;
import com.yg.model.User;
import com.yg.service.UserRegistrationService;

/**
 * @author yves.gakuba Implementation of {@link UserRegistrationService}
 * 
 */
public class UserRegistrationServiceImpl implements UserRegistrationService {
	private MongoOperations mongoOperation;

	public UserRegistrationServiceImpl() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
	}

	public UserRegistrationServiceImpl(InputStream configFile) {
		GenericXmlApplicationContext appContext = new GenericXmlApplicationContext();
		appContext.setValidating(false);
		appContext.load(new InputStreamResource(configFile));
		appContext.refresh();
		ApplicationContext ctx = appContext;
		mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
	}

	@Override
	public void saveOrUpdateUser(User user) {
		if (null == user.getId())
			mongoOperation.save(user);
		else
			updateUser(user);
	}

	@Override
	public void deleteUser(String id) {
		Query searchUserQuery = new Query(Criteria.where("id").is(id));
		mongoOperation.remove(searchUserQuery, User.class);
	}

	@Override
	public User getUserById(String id) {
		Query searchUserQuery = new Query(Criteria.where("id").is(id));
		User user = mongoOperation.findOne(searchUserQuery, User.class);

		return user;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> listUser = mongoOperation.findAll(User.class).subList(0, 100);

		return listUser;
	}

	private void updateUser(User u) {
		Query searchUserQuery = new Query(Criteria.where("id").is(u.getId()));
		Update upd = new Update();
		upd.set("firstname", u.getFirstname());
		upd.set("lastname", u.getLastname());
		upd.set("username", u.getUsername());
		upd.set("email", u.getEmail());
		upd.set("age", u.getAge());
		upd.set("registrationDate", u.getRegistrationDate());

		mongoOperation.updateFirst(searchUserQuery, upd, User.class);
	}

}
