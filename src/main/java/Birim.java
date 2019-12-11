
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Entity
@Table(name = "Birim")
public class Birim {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "birim_adi")
    private String birimAdi;

    @OneToMany(mappedBy = "birim", cascade = CascadeType.ALL)
    private List<Personel> personels = new ArrayList<>();

    @Column(name = "lokasyon")
    private String lokasyon;

    public Birim(String birimAdi, String lokasyon) {
        this.birimAdi = birimAdi;
        this.lokasyon = lokasyon;
    }

    public Birim() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBirimAdi() {
        return birimAdi;
    }

    public void setBirimAdi(String birimAdi) {
        this.birimAdi = birimAdi;
    }

    public List<Personel> getPersonels() {
        return personels;
    }

    public void setItems(List<Personel> items) {
        this.personels = items;
    }

    public String getLokasyon() {
        return lokasyon;
    }

    public void setLokasyon(String lokasyon) {
        this.lokasyon = lokasyon;
    }

    @Override
    public String toString() {
        return "Birim: [Id="+id + ", firstName=" + birimAdi  + ", lokasyon=" + lokasyon ;
    }
}
