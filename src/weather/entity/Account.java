package weather.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Accounts")
public class Account {

	@Id
	private String accountId;
	private String email;
	private String pass;

	public Account() {

	}

	public Account(String accountId, String email, String pass) {
		super();
		this.accountId = accountId;
		this.email = email;
		this.pass = pass;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
