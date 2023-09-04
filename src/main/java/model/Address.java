package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Address
{
    @Id
    @GeneratedValue
    private int addressId;
    private String street;
    private String number;
    private String floor;
    private int zipcode;


}
