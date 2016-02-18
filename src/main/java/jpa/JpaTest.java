package jpa;

import dao.*;
import domain.*;
import factory.*;
import java.util.*;
import javax.persistence.*;

public class JpaTest {
    
	public static void main(String[] args) {
                testPersonDao();
                //getPerson(1);
                //findAllPerson();
                System.out.println("done...");
                System.exit(0);   
        }
        
        /*
         * This function create a person with his friends and his home in which there are smart devices
         */
        public static void testPersonDao(){
            IDao dao = DaoFactory.getPersonDao();
            
            Person pers = new Person("koudou", "yves", "ky@gmail.com");
            
            List<Person> friends = new ArrayList<Person>();
            Person friend1 = new Person("yao","kouassi","yk@gmail.com");
            Person friend2 = new Person("zago","bagnon","zb@gmail.com");
            friends.add(friend1);
            friends.add(friend2);
            
            pers.setFriends(friends);
            
            List<Home> homes = new ArrayList<Home>();
            Home home = new Home("12m�", 4, "villa koudou");
            homes.add(home);
            
            pers.setHomes(homes);
            SmartDevice sd = new Heater("1600w");
            SmartDevice ed = new ElectronicDevice("350w");
            home.getSmartDevices().add(sd);
            home.getSmartDevices().add(ed);
            
            dao.create(pers);
            pers = (Person)dao.find(pers.getId());
            System.out.println(pers.toString());
            
        }
        
        /*
         * this function retrieve a person by his primary key
         */
        public static void getPerson(long id){
            EntityManager em = singleton.EntityManager.getInstance();
            Query q = em.createNamedQuery("person.find.by.id");
            q.setParameter("person_id", id);
            Person p = (Person)q.getSingleResult();
            System.out.println(p.toString());
        }
        /*
         * this function retrieve all the persons with criteria query
         */
        public static void findAllPerson(){
            IDao dao = DaoFactory.getPersonDao();
            List<Person> persons = (List<Person>) dao.findAll();
            for(Person p : persons)
                System.out.println(p.toString());
        }
}
