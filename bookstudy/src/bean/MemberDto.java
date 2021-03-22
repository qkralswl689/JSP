package bean;

import java.util.Arrays;

public class MemberDto {
	
	private String id;
	private String pw;
	private String name;
	private String address;
	private String[] hobby;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String[] getHobby() {
		return hobby;
	}
	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}
	
	@Override
	public String toString() {
		return "id=" + id + ", pw=" + pw + ", name=" + name + ", adress=" + address + ", hobby=" + Arrays.toString(hobby)
				+ "]";
	}
	
}
