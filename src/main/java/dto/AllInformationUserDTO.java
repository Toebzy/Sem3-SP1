package dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.entities.Hobby;
import model.entities.HobbyUser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@ToString
@Setter
@Getter
public class AllInformationUserDTO
{

    private String name;
    private int age;
    private String phonenumber;
    private String email;
    private String street;
    private String number;
    private String floor;
    private int zipcode;
    private String cityname;
    private Set<Hobby> hobbies;

    public AllInformationUserDTO(String name, int age, String phonenumber, String email, String street, String number, String floor, int zipcode, String cityname)
    {
        this.name = name;
        this.age = age;
        this.phonenumber = phonenumber;
        this.email = email;
        this.street = street;
        this.number = number;
        this.floor = floor;
        this.zipcode = zipcode;
        this.cityname = cityname;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\n");
        sb.append("Age: ").append(age).append("\n");
        sb.append("Phonenumber: ").append(phonenumber).append("\n");
        sb.append("Email: ").append(email).append("\n");
        sb.append("Street: ").append(street).append("\n");
        sb.append("Number: ").append(number).append("\n");
        sb.append("Floor: ").append(floor).append("\n");
        sb.append("Zipcode: ").append(zipcode).append("\n");
        sb.append("Cityname: ").append(cityname).append("\n");
        // Include hobbies
        sb.append("Hobbies: [");
        for (Hobby hobby : hobbies) {
            sb.append("{Name: ").append(hobby.getName())
                    .append(", Description: ").append(hobby.getDescription())
                    .append("}, ");
        }
        if (!hobbies.isEmpty()) {
            sb.delete(sb.length() - 2, sb.length()); // Remove the last comma and space
        }
        sb.append("]");
        return sb.toString();
    }
}
