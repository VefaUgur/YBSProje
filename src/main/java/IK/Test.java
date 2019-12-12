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

        PersonelRepository personelRepository = new PersonelRepository(entityManager);
        BirimRepository birimRepository = new BirimRepository(entityManager);
        RolRepository rolRepo = new RolRepository(entityManager);


     //   Birim uretim = new Birim("Üretim","Avcılar");
       Birim uretim = birimRepository.findByName("Üretim");
    //    birimRepository.save(uretim);
     //   Rol rol1 = new Rol("Araştırman");
     //   Rol rol2 = new Rol("Yönetici");
     //   rolRepo.save(rol1);
    //    rolRepo.save(rol2);
        Rol rol1 = rolRepo.findByName("Araştırman");
        Rol rol2 = rolRepo.findByName("Yönetici");

        Personel student = new Personel("mahmut","sda",uretim,rol1,"10.01.2014","sdadsa@gmail.com","54219321",20000);
        personelRepository.save(student);
        personelRepository.save(new Personel("erete","tte",uretim,rol2,"10.01.2014","sdadsa@gmail.com","54219321",10000));

        List<Personel> personels = personelRepository.findAll();
        System.out.println("Personels in database:");
        personels.forEach(System.out::println);

        List<Birim> birims =birimRepository.findAll();
        System.out.println("Birims in database:");
        birims.forEach(System.out::println);

       /* Birim uretim=birimRepository.findByID(1);
        System.out.println(uretim.getBirimAdi()+"'nin calisanlari");
        uretim.getPersonels().forEach(k-> System.out.println(k.getFirstName()));*/

        entityManager.close();
        entityManagerFactory.close();
    }
}
