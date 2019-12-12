package IK;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Egitim")
public class Egitim {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "egitim_adi")
    private String egitimAdi;

    @Column(name = "egitim_alani")
    private String egitimAlani;

    @Column(name = "egitim_tarihi")
    private String egitimTarihi;

    @Column(name = "egitim_suresi")
    private String egitimSuresi;

    @OneToMany(mappedBy = "egitim", cascade = CascadeType.ALL)
    private List<Personel> personels = new ArrayList<>();

    public Egitim(String egitimAdi, String egitimAlani, String egitimTarihi, String egitimSuresi) {
        this.egitimAdi = egitimAdi;
        this.egitimAlani = egitimAlani;
        this.egitimTarihi = egitimTarihi;
        this.egitimSuresi = egitimSuresi;
    }

    public int getId() {
        return id;
    }

    public String getEgitimAdi() {
        return egitimAdi;
    }

    public String getEgitimAlani() {
        return egitimAlani;
    }

    public String getEgitimTarihi() {
        return egitimTarihi;
    }

    public String getEgitimSuresi() {
        return egitimSuresi;
    }

    public List<Personel> getPersonels() {
        return personels;
    }

}
