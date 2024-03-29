package IK;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name="maasCarpan")
    private double birimCarpan;

    public Birim(String birimAdi, String lokasyon,double birimCarpan) {
        this.birimAdi = birimAdi;
        this.lokasyon = lokasyon;
        this.birimCarpan = birimCarpan;
    }

    public Birim() {
    }

    public double getBirimCarpan() {
        return birimCarpan;
    }

    public void setBirimCarpan(double birimCarpan) {
        this.birimCarpan = birimCarpan;
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
        return  birimAdi  ;
    }
}
