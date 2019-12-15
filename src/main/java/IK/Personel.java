package IK;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Personel")
public class Personel {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String isim;

    @Column(name = "last_name")
    private String soyisim;

    @ManyToOne
    @JoinColumn(name="birim_id")
    private Birim birim;

    @ManyToOne
    @JoinColumn(name="rol_id")
    private Rol rol;

    @Column(name = "baslamaTarihi")
    private String baslangicTarihi;

    @Column(name = "bitisTarihi")
    private Date bitisTarihi;

    @Column(name = "email")
    private String email;

    @Column(name = "telefon")
    private String tel_number;

    @Column(name = "maas")
    private double maas;

    @ManyToOne
    @JoinColumn(name="egitim_id")
    private Egitim egitim;


    public Personel() {
    }

    public Personel(String isim, String soyisim, Birim birim, Rol rol, String baslangicTarihi, String email, String tel_number) {
        this.isim = isim;
        this.soyisim = soyisim;
        this.birim = birim;
        this.rol = rol ;
        this.baslangicTarihi = baslangicTarihi;
        this.email = email;
        this.tel_number = tel_number;
        this.maas = new Maas().maasHesapla(this);
    }

    public void maasGuncelle(){
        this.maas = new Maas().maasHesapla(this);
    }

    public int getId() {
        return id;
    }

    public Rol getRol() {
        return rol;
    }

    public Egitim getEgitim() {
        return egitim;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String firstName) {
        this.isim = firstName;
    }

    public String getSoyisim() {
        return soyisim;
    }

    public void setSoyisim(String lastName) {
        this.soyisim = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Birim getBirim() {
        return birim;
    }

    public void setBirim(Birim birim) {
        this.birim = birim;
    }

    public String getBaslangicTarihi() {
        return baslangicTarihi;
    }

    public void setBaslangicTarihi(String startDate) {
        this.baslangicTarihi = startDate;
    }

    public Date getBitisTarihi() {
        return bitisTarihi;
    }

    public void setBitisTarihi(Date finishDate) {
        this.bitisTarihi = finishDate;
    }

    public String getTel_number() {
        return tel_number;
    }

    public void setTel_number(String tel_number) {
        this.tel_number = tel_number;
    }

    public double getMaas() {
        return maas;
    }

    public void setMaas(int sallary) {
        this.maas = sallary;
    }

    @Override
    public String toString() {
        return "Personel: [Id="+id + ", firstName=" + isim + ", lastName=" + soyisim + ", email=" + email + "]"+"Birim="+birim.getBirimAdi()+"Rol:"+rol.getRolAdi();
    }
}

