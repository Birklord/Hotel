package com.myapp.hotel.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
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
