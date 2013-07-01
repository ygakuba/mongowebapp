/**
 * 
 */
package com.yg.service;

import java.util.List;

import com.yg.model.User;

/**
 * @author yves.gakuba Defines the service layer of the application
 */
public interface UserRegistrationService {

	/**
	 * Saves or updates the user
	 * 
	 * @param u
	 *            The user to save or update
	 */
	public void saveOrUpdateUser(User u);

	/**
	 * Deletes an existing user from the database
	 * 
	 * @param id
	 *            The unique id to identify the user to be deleted
	 */
	public void deleteUser(String id);

	/**
	 * Gets a user from the database
	 * 
	 * @param id
	 *            The unique id to identify the user to be returned
	 * @return {@link User}
	 */
	public User getUserById(String id);

	/**
	 * Gets a list of users from the database
	 * 
	 * @return A list of {@link User}
	 */
	public List<User> getAllUsers();

}
