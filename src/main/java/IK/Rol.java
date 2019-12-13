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

    @Column(name="maasCarpan")
    private double rolCarpan;

    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL)
    private List<Personel> personels = new ArrayList<>();

    public Rol(String rolAdi,double rolCarpan) {
        this.rolAdi = rolAdi;
        this.rolCarpan = rolCarpan;
    }
    public Rol() {
    }
    public int getId() {
        return id;
    }

    public String getRolAdi() {
        return rolAdi;
    }

    public double getRolCarpan() {
        return rolCarpan;
    }

    public void setRolCarpan(double rolCarpan) {
        this.rolCarpan = rolCarpan;
    }

    public List<Personel> getPersonels() {
        return personels;
    }

    @Override
    public String toString() {
        return rolAdi;
    }
}
