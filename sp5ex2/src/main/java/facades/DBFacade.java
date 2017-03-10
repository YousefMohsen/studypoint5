/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entity.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Yousinho
 */
public class DBFacade {
  EntityManagerFactory emf;
           EntityManager em ;
    public DBFacade() {
      Persistence.generateSchema("pu",null);
         emf = Persistence.createEntityManagerFactory("pu");
         em = emf.createEntityManager();
           

    }
    
    
  public Person getPersonWithID(int id){
    Query q = em.createQuery("SELECT u FROM Person u WHERE u.id = :aId");
    q.setParameter("aId", id);
     Person result = (Person) q.getSingleResult();
        
      System.out.println(result.getFname());
        return result;
  
  } 
public List<Person> getAllPersons() {
          
   Query q = em.createQuery("SELECT u FROM Person u");
        
      List<Person> result = q.getResultList();
     
              
  return result;
      }


public void updatePerson(Person p){
    Person x = getPersonWithID(p.getId());

     try {
            em.getTransaction().begin();
       
       x.updateMe(p);
            
            
            em.getTransaction().commit();
            System.out.println("Person " + p.getFname() + " added to database");
        } finally {
            em.close();
        }
    

}
  
  
    public void createPerson(Person p) {

        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            System.out.println("Person " + p.getFname() + " added to database");
        } finally {
            em.close();
        }

    }
    
    public void deletePerson(int id){
          try {
   Person p = getPersonWithID(id);
      em.getTransaction().begin();
      em.remove(p);
      em.getTransaction().commit();
    
        } finally {
            em.close();
        }
    
    }
    
public static void main(String[] args) {
 DBFacade dbf = new DBFacade();
Person p = dbf.getPersonWithID(4);
   Person test = new Person(p.getFname(),"Yuuuupppp",p.getPhone());
   
   test.setId(4);
   dbf.updatePerson(test);
    //System.out.println(p.toString());
         //dbf.createPerson(new Person("Coca","Cola","4356789"));
      //System.out.println(dbf.getPersonWithID(4).toString());
        // TODO code application logic here
    }


}
