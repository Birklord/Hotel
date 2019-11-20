package com.myapp.hotel.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Customer {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private  Long id;

@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
private Set<Reservation> reservations;

private  String firstName;
private  String lastName;
private  String email;
private  String phoneNo;
private  String address;

}
