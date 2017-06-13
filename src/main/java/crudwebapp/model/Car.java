package crudwebapp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by arnoldas on 17.6.12.
 */

@Entity
@Table(name = "car")
public class Car {

    //brand (bmw, audi.zopel) // model (A6) //color

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String plateNumber;

    private List<Human> users;




    public Car() {
    }





}
