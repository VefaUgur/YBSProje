package IK;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class RolRepository {
    private EntityManager entityManager;

    public RolRepository(javax.persistence.EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Rol> save(Rol rol) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(rol);
            entityManager.getTransaction().commit();
            return Optional.of(rol);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void delete(int id){
        entityManager.getTransaction().begin();
        Rol rol = entityManager.find(Rol.class, id);
        entityManager.remove(rol);
        entityManager.getTransaction().commit();
    }
    public List<Rol> findAll() {

        return entityManager.createQuery("from Rol").getResultList();
    }
    public void clearTable(){
        try{
            List<Rol> rols = findAll();
            entityManager.getTransaction().begin();
            for(Rol rol:rols){
                entityManager.remove(rol);
            }
            entityManager.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Rol findByName(String name) {
        return (Rol) entityManager.createQuery(
                "FROM Rol p WHERE p.rolAdi LIKE :custName")
                .setParameter("custName", name)
                .getSingleResult();
    }

    public void updateRolCarpan(String name,String value){
        Rol rol = findByName(name);
        rol.setRolCarpan(Double.parseDouble(value));
        entityManager.getTransaction().begin();
        entityManager.merge(rol);
        entityManager.getTransaction().commit();
    }

}
