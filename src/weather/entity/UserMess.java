package weather.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;



@Entity
@Table(name="UserMess")
public class UserMess {

	@Email(message="Insert correct format Email!")
	private String userMail;
	
	@NotBlank(message="Insert name!")
	private String name;
	
	@NotBlank(message="Insert phone!")
	private String phone;
	
	@NotBlank(message="Insert message!")
	private String message;
	
	@Id
	@GeneratedValue
	private int id;
		
	public UserMess(){}

	public UserMess(String userMail, String name, String phone, String message, int id) {
		super();
		this.userMail = userMail;
		this.name = name;
		this.phone = phone;
		this.message = message;
		this.id = id;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getId() { 
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
