package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserModel implements Serializable{
	private int id;
	private String email;
	private String password;
	private String name;
	private int isDeleted;
	private Timestamp created_at;
	private Timestamp updated_at;
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getIsDeleted() {
		return this.isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
	public Timestamp getCreatedAt() {
		return this.created_at;
	}
	public void setCreatedAt(Timestamp created_at) {
		this.created_at = created_at;
	}
	
	public Timestamp getUpdatedAt(Timestamp updated_at) {
		return this.updated_at;
	}
	public void setUpdatedAt(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

}