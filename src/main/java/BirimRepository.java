import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
public class BirimRepository {
    private EntityManager entityManager;

    public BirimRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Birim> save(Birim birim) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(birim);
            entityManager.getTransaction().commit();
            return Optional.of(birim);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void delete(int id){
        entityManager.getTransaction().begin();
        Birim student = entityManager.find(Birim.class, id);
        entityManager.remove(student);
        entityManager.getTransaction().commit();
    }
    public List<Birim> findAll() {

        return entityManager.createQuery("from Birim").getResultList();
    }
    public void clearTable(){
        try{
            List<Birim> birims = findAll();
            entityManager.getTransaction().begin();
            for(Birim birim:birims){
                entityManager.remove(birim);
            }
            entityManager.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Birim findByID(int id){
        return entityManager.find(Birim.class, id);
    }

    public Birim findByName(String name) {
        return (Birim) entityManager.createQuery(
                "FROM Birim p WHERE p.birimAdi LIKE :custName")
                .setParameter("custName", name)
                .getSingleResult();
    }
}
