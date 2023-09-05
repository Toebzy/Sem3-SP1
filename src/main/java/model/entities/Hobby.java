package model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Hobby {
    @Id
    @GeneratedValue
    @Column(name="hobby_id")
    private int hobbyId;
    private String name;
    private String description;
    @OneToMany(mappedBy = "hobby")
    private Set<HobbyUser> users = new HashSet<>();
    @OneToMany(mappedBy = "hobby")
    private Set<HobbyClub> clubs = new HashSet<>();

    public Hobby(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public void addHobbyUser(HobbyUser hobbyUser)
    {
        users.add(hobbyUser);
        if(hobbyUser != null)
        {
            hobbyUser.setHobby(this);
        }
    }
    public void addHobbyClub(HobbyClub hobbyClub)
    {
        clubs.add(hobbyClub);
        if(hobbyClub != null)
        {
            hobbyClub.setHobby(this);
        }
    }
}