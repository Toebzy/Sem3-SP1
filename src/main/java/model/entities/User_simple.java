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
public class User_simple
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
    @ManyToOne
    private Address address;
    @OneToMany(mappedBy = "userSimple")
    private Set<HobbyUser> hobbies = new HashSet<>();

    public User_simple(String name, int age, String phonenumber, String email, String password)
    {
        this.name = name;
        this.age = age;
        this.phonenumber = phonenumber;
        this.email = email;
        this.password = password;
    }
    public void addHobbyUser(HobbyUser hobbyUser)
    {
        hobbies.add(hobbyUser);
        if(hobbyUser != null)
        {
            hobbyUser.setUserSimple(this);
        }
    }
}
