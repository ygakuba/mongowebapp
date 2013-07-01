/**
 * 
 */
package com.yg.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author yves.gakuba
 * 
 */
@XmlRootElement(name="user")
@Document(collection = "users")
public class User {

	@Id
	private String id;
	private String username;
	private String firstname;
	private String lastname;
	private Integer age;
	private String email;
	private Date registrationDate;

	public User(){}
	
	public User(String firstname, String lastname, String username, 
			String email, Integer age, Date registrationDate) {
		setUsername(username);
		setFirstname(firstname);
		setLastname(lastname);
		setAge(age);
		setEmail(email);
		setRegistrationDate(registrationDate);
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	@XmlElement
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	@XmlElement
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	@XmlElement
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	@XmlElement
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}

	@XmlElement
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	@XmlElement
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the registrationDate
	 */
	public Date getRegistrationDate() {
		return registrationDate;
	}

	@XmlElement
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Override
	public String toString() {
		return "id: " + id +
				", firstname: " + firstname +
				", lastname: " + lastname +
				", username: " + username +
				", email: " + email + 
				", age: " + age + 
				", registrationDate: " + registrationDate;
	}
}
