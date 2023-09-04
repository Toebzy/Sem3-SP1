package model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

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
    @ManyToOne
    private Zipcode zipcode;
    @OneToMany(mappedBy = "address")
    private Set<User> users = new HashSet<>();
}
