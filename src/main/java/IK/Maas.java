package IK;

public class Maas {
    private double tabanMaas=3000;
    private double yillikCarpan=0.1;
    public Maas( ) {
    }

    public double getTabanMaas() {
        return tabanMaas;
    }

    public void setTabanMaas(int tabanMaas) {
        this.tabanMaas = tabanMaas;
    }

    public double getBirimCarpan() {
        return yillikCarpan;
    }

    public void setBirimCarpan(double birimCarpan) {
        this.yillikCarpan = birimCarpan;
    }

    public double maasHesapla(Personel personel){
        double maas;
        maas = tabanMaas*personel.getBirim().getBirimCarpan()+tabanMaas;
        maas = maas*personel.getRol().getRolCarpan()+maas;
        return maas;
    }

}
