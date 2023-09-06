package model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
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

    public HobbyClub(String name, String description, double price, String email)
    {
        this.name = name;
        this.description = description;
        this.price = price;
        this.email = email;
    }
}
