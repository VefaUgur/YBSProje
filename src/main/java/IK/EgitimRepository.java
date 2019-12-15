package IK;

import IK.Egitim;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class EgitimRepository {
    private EntityManager entityManager;

    public EgitimRepository(javax.persistence.EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Egitim> save(Egitim egitim) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(egitim);
            entityManager.getTransaction().commit();
            return Optional.of(egitim);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void delete(int id){
        entityManager.getTransaction().begin();
        Egitim egitim = entityManager.find(Egitim.class, id);
        entityManager.remove(egitim);
        entityManager.getTransaction().commit();
    }
    public List<Egitim> findAll() {
        return entityManager.createQuery("from Egitim").getResultList();
    }
    public void clearTable(){
        try{
            List<Egitim> egitims = findAll();
            entityManager.getTransaction().begin();
            for(Egitim egitim:egitims){
                entityManager.remove(egitim);
            }
            entityManager.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void deleteById(int id){
        entityManager.getTransaction().begin();
        Egitim egitim = entityManager.find(Egitim.class, id);
        entityManager.remove(egitim);
        entityManager.getTransaction().commit();
    }
}
