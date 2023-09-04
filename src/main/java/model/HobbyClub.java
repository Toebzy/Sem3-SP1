package model;

import jakarta.persistence.Column;
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
    private int zipcode;
    private int hobbyId;
}