package crudwebapp.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Created by arnoldas on 17.6.12.
 */

@Entity
@Table(name = "human")
public class Human {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private int age;


//    @ManyToMany(targetEntity = Car.class, cascade = CascadeType.ALL) //owning
//    private Collection<Car> ownedCars;



    public Human() {
    }

    public Human(
            String name,
            String surname,
            int age/*,
            Collection<Car> ownedCars*/) {
        this.name = name;
        this.surname = surname;
        this.age = age;
//        this.ownedCars = ownedCars;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

//    public Collection<Car> getOwnedCars() {
//        return ownedCars;
//    }
//
//    public void setOwnedCars(Collection<Car> ownedCars) {
//        this.ownedCars = ownedCars;
//    }
}
