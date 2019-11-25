package com.myapp.hotel.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Customer {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private  Long id;
private  String name;
private  String email;
private  String phoneNo;
private  String address;


    public Customer() {
    }


}
