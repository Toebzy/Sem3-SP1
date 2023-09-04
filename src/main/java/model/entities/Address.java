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
    private Set<User_simple> userSimples = new HashSet<>();

    public void addUser(User_simple user_simple)
    {
        userSimples.add(user_simple);
        if(user_simple != null)
        {
            user_simple.setAddress(this);
        }
    }
}
