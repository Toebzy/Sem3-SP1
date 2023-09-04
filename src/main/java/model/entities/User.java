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
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;
    private String name;
    private int age;
    private String phonenumber;
    private String email;
    private String password;
    @Column(name = "address_id")
    @ManyToOne
    private Address address;
    @OneToMany(mappedBy = "user")
    private Set<HobbyUser> hobbies = new HashSet<>();

}
