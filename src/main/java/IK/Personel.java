package IK;

import IK.Birim;
import IK.Egitim;

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
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne
    @JoinColumn(name="birim_id")
    private Birim birim;

    @ManyToOne
    @JoinColumn(name="rol_id")
    private Rol rol;

    @Column(name = "baslamaTarihi")
    private String startDate;

    @Column(name = "bitisTarihi")
    private Date finishDate;

    @Column(name = "email")
    private String email;

    @Column(name = "telefon")
    private String tel_number;

    @Column(name = "maas")
    private int sallary;

    @ManyToOne
    @JoinColumn(name="egitim_id")
    private Egitim egitim;

    public Personel() {
    }

    public Personel(String firstName, String lastName, Birim birim, Rol rol, String startDate, String email, String tel_number, int sallary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birim = birim;
        this.rol = rol ;
        this.startDate = startDate;
        this.email = email;
        this.tel_number = tel_number;
        this.sallary = sallary;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public String getTel_number() {
        return tel_number;
    }

    public void setTel_number(String tel_number) {
        this.tel_number = tel_number;
    }

    public int getSallary() {
        return sallary;
    }

    public void setSallary(int sallary) {
        this.sallary = sallary;
    }

    @Override
    public String toString() {
        return "Personel: [Id="+id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]"+"Birim="+birim.getBirimAdi()+"Rol:"+rol.getRolAdi();
    }
}

