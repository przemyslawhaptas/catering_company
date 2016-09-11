package com.model;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
 
@Entity
@Table(name = "USERS")
@NamedQuery(name="User.findUserByEmail", query="select u from User u where u.email = :email")
public class User {
 
    public static final String FIND_BY_EMAIL = "User.findUserByEmail";
 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
 
    @Column(unique = true)
    private String email;
    private String password;
    private String firstName;
    private String lastName;
 	private String role;
 	
 	@OneToOne
 	@JoinColumn(name = "payment_info_id")
 	private PaymentInfo paymentInfo;
 	
 	@OneToOne
 	@JoinColumn(name = "address_info_id")
 	private AddressInfo addressInfo; 
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
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
 
    public String getFirstName() {
        return firstName;
    }
 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
 
    public String getRole() {
        return role.toLowerCase();
    }
 
    public void setRole(String role) {
        this.role = role;
    }
 
    public PaymentInfo getPaymentInfo() {
		return paymentInfo;
	} 

	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}
	
    public AddressInfo getAddressInfo() {
		return addressInfo;
	} 

	public void setAddressInfo(AddressInfo addressInfo) {
		this.addressInfo = addressInfo;
	}

	@Override
    public int hashCode() {
        return getId();
    }
 
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof User){
            User user = (User) obj;
            return user.getEmail().equals(getEmail());
        }
 
        return false;
    }
}
