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
public class Hobby {
    @Id
    @GeneratedValue
    @Column(name="hobby_id")
    private int hobbyId;
    private String name;
    private String description;
    @OneToMany(mappedBy = "hobby")
    private Set<HobbyUser> users = new HashSet<>();
    @OneToMany(mappedBy = "club")
    private Set<HobbyClub> clubs = new HashSet<>();
}