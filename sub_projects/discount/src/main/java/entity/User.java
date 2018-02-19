package entity;

//import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

//@Entity
//@Table(name = "Users")
//@NamedQueries
//({
//    @NamedQuery(name = "getUser", query = "Select u from User u where u.login = :login and u.password = :password"),
//    @NamedQuery(name = "getUserByLogin", query = "Select u from User u where u.login = :login"),
//})
@XmlRootElement(name="User")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

    //@Id
    @XmlElement private String login;
    @XmlElement private String address;
    @XmlElement private String phone;
    @XmlElement private Boolean isAdmin = false; // default value
    @XmlElement private String password;
    @XmlElement private BigDecimal balance = new BigDecimal("2000"); // default value
    @XmlElement private Integer discount = 3; // default value
    @XmlElement private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getDiscount() {
        return discount;
    }
    public boolean isAdmin() {
        return isAdmin;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("address = %s, phone = %s, isAdmin = %b, login = %s, password = %s, balance = %s, discount = %d, name = %s",
                address, phone, isAdmin, login, password, balance, discount, name);
    }
}
