package com.revature.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.revature.ecommerce.entities.dtos.enums.Role;
import com.revature.ecommerce.entities.junctions.Cart;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    private String id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private char[] password;

    @Column(name = "given_name", nullable = false)
    private String givenName;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name="role", nullable = false)
    @Enumerated
    private Role role;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name="exp_date")
    @Temporal(TemporalType.DATE)
    private Date expirationDate;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user"
    )
    @JsonManagedReference(value="user-addresses")
    private List<Address> addresses;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "user"
    )
    @JsonManagedReference(value="user-cart")
    private List<Cart> cart;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user"
    )
    @JsonManagedReference(value="user-orders")
    private List<Order> orders;

    public User() {
        super();
    }

    public User(String id, String email, char[] password, String givenName, String surname) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.givenName = givenName;
        this.surname = surname;
        this.role = Role.User;
    }

    public User(String id, String email, char[] password, String givenName, String surname, Role role, String cardNumber,
                Date expirationDate, List<Address> addresses, List<Cart> cart, List<Order> orders) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.givenName = givenName;
        this.surname = surname;
        this.role = role;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.addresses = addresses;
        this.cart = cart;
        this.orders = orders;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Cart> getCart() {
        return cart;
    }

    public void setCart(List<Cart> cart) {
        this.cart = cart;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", givenName='" + givenName + '\'' +
                ", surname='" + surname + '\'' +
                ", role=" + role +
                ", cardNumber='" + cardNumber + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
