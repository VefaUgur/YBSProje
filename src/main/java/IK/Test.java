package IK;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.logging.Level;

public class Test {
    public static void main(String[] args) {
        org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("IK");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        createDB(entityManager);
        entityManager.close();
        entityManagerFactory.close();
    }
    public static void createDB(EntityManager entityManager){
        PersonelRepository personelRepository = new PersonelRepository(entityManager);
        BirimRepository birimRepository = new BirimRepository(entityManager);
        RolRepository rolRepo = new RolRepository(entityManager);

        Birim ik = new Birim("İnsan Kaynakları","Avcılar",0.2);
        Birim pazarlama = new Birim("Satış-Pazarlama","Kadıköy",0.1);
        Birim muhasebe = new Birim("Muhasebe","Beşiktaş",0.15);
        Birim uretim = new Birim("Üretim","Avcılar",0.25);

        birimRepository.save(ik);
        birimRepository.save(pazarlama);
        birimRepository.save(muhasebe);
        birimRepository.save(uretim);

        Rol rol1 = new Rol("Araştırman",0.2);
        Rol rol2 = new Rol("Yönetici",0.5);
        Rol rol3 = new Rol("Ekip Üyesi",0.25);

        rolRepo.save(rol1);
        rolRepo.save(rol2);
        rolRepo.save(rol3);

        rolRepo.findAll().forEach(k-> System.out.println(k.toString()));
        List<Birim> birims =birimRepository.findAll();
        birims.forEach(System.out::println);

    }
}
