package pro.sky.TeamJob.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Recommendation {

    private String name;
    private String text;

    @Override
    public String toString() {
        return "Рекомендация: " + name + "\n"
                + text;
    }

}
