package IK;

import IK.Personel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class PersonelRepository {

    private EntityManager entityManager;

    public PersonelRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Personel> save(Personel author) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(author);
            entityManager.getTransaction().commit();
            return Optional.of(author);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void delete(int id){
        entityManager.getTransaction().begin();
        Personel student = entityManager.find(Personel.class, id);
        entityManager.remove(student);
        entityManager.getTransaction().commit();
    }
    public List<Personel> findAll() {

        return entityManager.createQuery("from Personel").getResultList();
    }

    public void clearTable(){
        try{
            List<Personel> students = findAll();
            entityManager.getTransaction().begin();
            for(Personel student:students){
                entityManager.remove(student);
            }
            entityManager.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public List<Personel> findByName(String name) {
        return entityManager.createQuery(
                "FROM Personel p WHERE p.firstName LIKE :custName")
                .setParameter("custName", name)
                .getResultList();
    }

}