package pro.sky.TeamJob.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Recommendation {

    private String name;
    private String id;
    private String text;

    @Override
    public String toString() {
        return "Рекомендация: " + name + "\n"
                + text + "\n"
                + "Id продукта: " + id + "\n";
    }
}
