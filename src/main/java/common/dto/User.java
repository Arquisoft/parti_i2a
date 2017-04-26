package common.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
	private Integer id;

	private String firstName;
	private String lastName;
	private Date birthdate;
	private String address;
	private String email;
	private String password;
	private String nationality;
	private int pollingStation;
	private String dni;

	public User() {
	}

	public User(String dni, String firstName, String lastName, Date birthdate, String address, String email,
			String nationality, int pollingStation) {
		super();
		this.dni = dni;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.address = address;
		this.email = email;
		this.nationality = nationality;
		this.pollingStation = pollingStation;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setSurname(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setDni(String dni) {
		this.dni = dni;

	}

	public String getDni() {
		return dni;
	}

	public int getPollingStation() {
		return pollingStation;
	}

	public void setPollingStation(int pollingStation) {
		this.pollingStation = pollingStation;

	}

	@Override
	public boolean equals(Object obj) {
		return obj.toString().equals(this.toString());
	}

	@Override
	public String toString() {
		String simpleDate = new SimpleDateFormat("dd/MM/yyyy").format(birthdate);

		return "User[Id: " + id + "; Name: " + firstName + "; Surname: " + lastName + "; " + "Email: " + email + "; Birth date: "
				+ simpleDate + "; " + "Address: " + address + "; Nationality: " + nationality + "; DNI: " + dni
				+ "; Polling station: " + pollingStation +"]";

	}

}
