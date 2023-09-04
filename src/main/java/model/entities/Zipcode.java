package model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Zipcode
{
    @Id
    private int zipcode;
    @Column(name = "city_name")
    private String cityName;
    @OneToMany(mappedBy = "zipcode")
    private Set<HobbyClub> clubs = new HashSet<>();
    @OneToMany(mappedBy = "zipcode")
    private Set<Address> addresses = new HashSet<>();

    public Zipcode(int zipcode, String cityName)
    {
        this.zipcode = zipcode;
        this.cityName = cityName;
    }

    public void addHobbyClub(HobbyClub hobbyClub) {
        clubs.add(hobbyClub);

        if(hobbyClub != null)
        {
            hobbyClub.setZipcode(this);
        }
    }
    public void addAddress(Address address)
    {
        addresses.add(address);
        if(address != null)
        {
            address.setZipcode(this);
        }
    }
}