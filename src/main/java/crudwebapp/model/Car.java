//package crudwebapp.model;
//
//import java.util.Collection;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToMany;
//import javax.persistence.Table;
//
///**
// * Created by arnoldas on 17.6.12.
// */
//
//@Entity
//@Table(name = "car")
//public class Car {
//
//    //brand (bmw, audi.zopel) // model (A6) //color
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//    @Column
//    private String plateNumber;
//
//
//
//    @ManyToMany(mappedBy = "carCollection") //non-owning
//    private Collection<Human> users;
//
//
//    /*
//
//
//    @ManyToOne
//    @JoinColumn(name = "singleCandidate") // referencedColumnName = "candidateID"
//    private Candidate singleCandidate = new Candidate();
//
//    @ManyToOne
//    @JoinColumn(name = "singleDistrict") // referencedColumnName = "id"
//    private District singleDistrict = new District();
//     */
//
//
//    public Car() {
//    }
//
//    public Car(String plateNumber, Collection<Human> users) {
//        this.plateNumber = plateNumber;
//        this.users = users;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getPlateNumber() {
//        return plateNumber;
//    }
//
//    public void setPlateNumber(String plateNumber) {
//        this.plateNumber = plateNumber;
//    }
//
//    public Collection<Human> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Collection<Human> users) {
//        this.users = users;
//    }
//}
