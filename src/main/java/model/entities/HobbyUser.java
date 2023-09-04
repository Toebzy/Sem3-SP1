package model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class HobbyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hobby_user_id")
    private int hobbyUserId;
    @ManyToOne
    private User_simple userSimple;
    @ManyToOne
    private Hobby hobby;


}
