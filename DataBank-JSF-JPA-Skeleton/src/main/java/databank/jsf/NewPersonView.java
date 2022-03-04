/*****************************************************************
 * File: PersonPojo.java Course materials (22W) CST8277
 *
 * @author Teddy Yap
 * @author Shahriar (Shawn) Emami
 * @author (original) Mike Norman
 */
package databank.jsf;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.faces.annotation.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import databank.model.PersonPojo;

/**
 * This class represents the scope of adding a new person to the DB.
 * 
 * TODO 09 - This class is a managed been. Use the name "newPerson".<br>
 * TODO 10 - Unlike previous assignment where PersonPojo was view scoped, now this class is.<br>
 * TODO 11 - Add the missing variables and their getters and setters. Have in mind dates and version are internal
 * only.<br>
 * 
 */
@Named("newPerson")
@ViewScoped
public class NewPersonView implements Serializable {
	/** explicit set serialVersionUID */
	private static final long serialVersionUID = 1L;

	private int id;
	protected String lastName;
	protected String firstName;
	private String email;
	private String phoneNumber;
	private String city;
	private LocalDateTime updated;
	private LocalDateTime created;	
	private int version = 1;	
	private boolean editable;

	@Inject
	@ManagedProperty("#{personController}")
	protected PersonController personController;

	public NewPersonView() {
	}

	public int getId() {
		return id;
	}
	/**
	 * @param id new value for id
	 */
	public void setId( int id) {
		this.id = id;
	}
	/**
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param LastName LastName to set
	 */
	public void setLastName( String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName firstName to set
	 */
	public void setFirstName( String firstName) {
		this.firstName = firstName;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail( String email) {
		this.email = email;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber( String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity( String city) {
		this.city = city;
	}

	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated( LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getUpdated() {
		return updated;
	}
	public void setUpdated( LocalDateTime updated) {
		this.updated = updated;
	}

	public int getVersion() {
		return version;
	}
	public void setVersion( int version) {
		this.version = version;
	}
	
	public boolean getEditable() {
		return editable;
	}
	
	public void setEditable( boolean editable) {
		this.editable = editable;
	}
	

	public void addPerson() {
		if ( allNotNullOrEmpty(id, firstName, lastName, email, phoneNumber,city, created, updated,
				version, editable/* TODO 11 - don't forget the other variables */)) {
			PersonPojo theNewPerson = new PersonPojo();
			theNewPerson.setFirstName( getFirstName());
			theNewPerson.setLastName( getLastName());
			theNewPerson.setEmail( getEmail());
			theNewPerson.setPhoneNumber( getPhoneNumber());
			theNewPerson.setCity( getCity());
			theNewPerson.setCreated(LocalDateTime.ofInstant(Instant.now(), ZoneId.of("America/New_York")));
			theNewPerson.setUpdated(LocalDateTime.ofInstant(Instant.now(), ZoneId.of("America/New_York")));
			theNewPerson.setVersion(1);
			theNewPerson.setEditable(true);
			//TODO 12 - call other setters
			personController.addNewPerson( theNewPerson);
			//clean up
			personController.toggleAdding();
			setFirstName( null);
			setLastName( null);
			setEmail( null);
			setPhoneNumber( null);
			setCity( null);
			setCreated( null);
			setUpdated( null);
			//TODO 13 - set everything to null
		}
	}

	static boolean allNotNullOrEmpty( final Object... values) {
		if ( values == null) {
			return false;
		}
		for ( final Object val : values) {
			if ( val == null) {
				return false;
			}
			if ( val instanceof String) {
				String str = (String) val;
				if ( str.isEmpty()) {
					return false;
				}
			}
		}
		return true;
	}
}