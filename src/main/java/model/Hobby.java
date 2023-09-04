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
public class Hobby {
    @Id
    @GeneratedValue
    @Column(name="hobby_id")
    private int hobbyId;
    private String name;
    private String description;
}
