package domain;

import java.util.*;
import javax.persistence.*;

@Entity
@NamedQueries
({
  @NamedQuery(name="person.remove.all", query="DELETE FROM Person p"),
  @NamedQuery(name="person.find.by.surname", query="SELECT p FROM Person p WHERE p.surname = :surname"),
  @NamedQuery(name="person.delete.by.id", query="UPDATE Person p")
})
public class Person {
    private Long id;
    private String surname;
    private String forename;
    private String mail;
    
    private List<Person> firends = new ArrayList<Person>();
    private List<Home> homes = new ArrayList<Home>();

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name="FK_PERSON_ID", referencedColumnName = "PERSON_ID")
    public List<Home> getHomes() {
        return homes;
    }

    public void setHomes(List<Home> homes) {
        //this.homes.clear();
        //this.homes.addAll(homes);
        this.homes = homes;
    }

    @Id
    @Column(name="PERSON_ID")
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
    @OneToMany(cascade = {CascadeType.PERSIST})
    @JoinColumn(name="FK_PERSON_ID", referencedColumnName = "PERSON_ID")
    public List<Person> getFirends() {
        return firends;
    }

    public void setFirends(List<Person> friends) {
        //this.firends.clear();
        //this.firends.addAll(friends);
        this.firends = friends;
    }
    
    public Person(){
        
    }
    
    public Person(String surname, String forename, String mail){
        this.surname = surname;
        this.forename = forename;
        this.mail = mail;
    }
}
