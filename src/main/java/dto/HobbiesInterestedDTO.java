package dto;

import lombok.ToString;
import model.entities.Hobby;

import java.util.List;
import java.util.Map;
@ToString
public class HobbiesInterestedDTO
{
    private Map<Hobby, Integer> interested;

    public HobbiesInterestedDTO(Map<Hobby, Integer> interested)
    {
        this.interested = interested;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hobbies Interested Count:\n");

        for (Map.Entry<Hobby, Integer> entry : interested.entrySet()) {
            Hobby hobby = entry.getKey();
            Integer count = entry.getValue();
            sb.append("Hobby: ").append(hobby.getName()).append(", Interrested: ").append(count).append("\n");
        }

        return sb.toString();
    }
}
