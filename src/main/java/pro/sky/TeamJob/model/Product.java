package pro.sky.TeamJob.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Класс описывающий продукт
 * @author Daniil Topchiy & Ivan Kovalev
 * @version 1.0
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

    /** id */
    @Id
    private String id;

    /** Тип продукта */
    private String type;

    /** Имя продукта */
    private String name;

    /** Пользователи продукта */
    @ManyToMany
    private List<User> users;

}
