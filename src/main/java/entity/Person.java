package entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "person")
public class Person extends PanacheEntity {
    @Column(name = "first_name")
    public String firstName;
    @Column(name = "last_name")
    public String lastName;
    public String tenant;

    public static Person findByName(String name){
        return find("name", name).firstResult();
    }

    public static List<Person> getPeoples(){
        return listAll();
    }
}
