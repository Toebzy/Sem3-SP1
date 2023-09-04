package model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class HobbyClub
{
    @Id
    @GeneratedValue
    @Column(name = "hobby_club_id")
    private int hobbyClubId;
    private String name;
    private String description;
    private double price;
    private String email;
    @ManyToOne
    private Zipcode zipcode;
    @ManyToOne
    private Hobby hobby;
}
