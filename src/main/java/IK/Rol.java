package IK;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Rol")
public class Rol {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "rol_adi")
    private String rolAdi;

    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL)
    private List<Personel> personels = new ArrayList<>();

    public Rol(String rolAdi) {
        this.rolAdi = rolAdi;
    }
    public Rol() {
    }
    public int getId() {
        return id;
    }

    public String getRolAdi() {
        return rolAdi;
    }

    public List<Personel> getPersonels() {
        return personels;
    }

    @Override
    public String toString() {
        return rolAdi;
    }
}
