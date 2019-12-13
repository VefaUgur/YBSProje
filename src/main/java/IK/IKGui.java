package IK;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.logging.Level;

public class IKGui {
    private JButton personelEkle;
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JTable table1;
    private JTextField name;
    private JTextField email;
    private JTextField tel;
    private JTextField surname;
    private JComboBox birimTxt;
    private JComboBox rolTxt;
    private JTextField tarih;
    private JButton yenileButton;
    private JTextField nameRemove;
    private JTextField surnameRemove;
    private JButton personelCikar;
    private JCheckBox tiklaSil;
    private JTextField yillikMC;
    private JTextField uretimMC;
    private JTextField satisMC;
    private JTextField muhasebeMC;
    private JTextField ikMC;
    private JTextField arastirmanMC;
    private JTextField ekipMC;
    private JTextField yoneticiMC;
    private JButton uygulaButton;
    private JFormattedTextField formattedTextField1;

    static JFrame frame;

    public IKGui() {
        org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("IK");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        PersonelRepository personelRepository = new PersonelRepository(entityManager);
        BirimRepository birimRepository = new BirimRepository(entityManager);
        RolRepository rolRepo = new RolRepository(entityManager);

        personelDuzenleTab(personelRepository,birimRepository,rolRepo);
        bordroTab(personelRepository,birimRepository,rolRepo);

    }

    public static void  main(String[] args) {
        JFrame frame = new JFrame("IKGui");
        frame.setContentPane(new IKGui().panel1);
        frame.setSize(1500,720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void personelDuzenleTab(PersonelRepository personelRepository,BirimRepository birimRepository,RolRepository rolRepo){
        tabloYukle(personelRepository);
        MouseAdapter  mouseAdapter = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                clickRemove(personelRepository,e);
            }
        };
        tiklaSil.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    table1.addMouseListener(mouseAdapter);
                }else table1.removeMouseListener(mouseAdapter);
            }
        });
        personelEkle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
//              formattedTextField1.setText(personelRepository.findAll().get(0).getFirstName());
                personelEkle(birimRepository,rolRepo,personelRepository);
                tabloYukle(personelRepository);

            }
        });
        yenileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
//               formattedTextField1.setText(personelRepository.findAll().get(0).getFirstName());
                tabloYukle(personelRepository);

            }
        });
        personelCikar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                personelSil(personelRepository);
            }
        });
    }

    public void bordroTab(PersonelRepository personelRepository,BirimRepository birimRepository,RolRepository rolRepo){
        setMaasCarpaniTxts(birimRepository,rolRepo);

        uygulaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                rolRepo.updateRolCarpan("Araştırman",arastirmanMC.getText());
                rolRepo.updateRolCarpan("Yönetici",yoneticiMC.getText());
                rolRepo.updateRolCarpan("Ekip Üyesi",ekipMC.getText());

                birimRepository.updateBirimCarpan("Üretim",uretimMC.getText());
                birimRepository.updateBirimCarpan("Satış-Pazarlama",satisMC.getText());
                birimRepository.updateBirimCarpan("Muhasebe",muhasebeMC.getText());
                birimRepository.updateBirimCarpan("İnsan Kaynakları",ikMC.getText());

                personelRepository.updateTumMaaslar();
            }
        });

    }

    public void personelEkle(BirimRepository birimRepository,RolRepository rolRepository,PersonelRepository personelRepository){
        Birim birim = birimRepository.findByName(birimTxt.getSelectedItem().toString());
        Rol rol = rolRepository.findByName(rolTxt.getSelectedItem().toString());
        if(name.getText().length()<=1  || surname.getText().length()<=1 || tarih.getText().length()<=1 || tel.getText().length()<=1 ){
            JOptionPane.showMessageDialog(null,"Hata: Eksik Personel Bilgisi");
        }else{
            Personel personel = new Personel(name.getText(),surname.getText(),birim, rol,tarih.getText(),email.getText(),tel.getText());
            try{
                personelRepository.save(personel);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void personelSil(PersonelRepository personelRepository){
        List<Personel> personelList = personelRepository.findByNameAndSurname(nameRemove.getText(),surnameRemove.getText());
        if(personelList.isEmpty()){
            JOptionPane.showMessageDialog(null,"Hata: Personel Sistemde Mevcut Değil!");
        }else{
            personelRepository.delete(personelList.get(0));
            JOptionPane.showMessageDialog(null,"Personel Sistemden Çıkarıldı.");
            tabloYukle(personelRepository);
        }

    }

    public void tabloYukle(PersonelRepository personelRepository){
        List<Personel> personelList = personelRepository.findAll();
        String[] header = {"ID","İSİM","SOYİSİM","TEL.","E-MAİL","MAAŞ","BİRİM","ROL","BAŞLANGIÇ TARİHİ"};
        DefaultTableModel model = new DefaultTableModel(null,header);
        model.setColumnCount(9);
        model.setRowCount(personelRepository.findAll().size()+1);
        table1.setModel(model);

        JTableHeader tableHeader = table1.getTableHeader();
        tableHeader.setBackground(Color.yellow);

        for (int i=0;i<personelList.size();i++){
            table1.setValueAt(personelList.get(i).getId(),i,0);
            table1.setValueAt(personelList.get(i).getFirstName(),i,1);
            table1.setValueAt(personelList.get(i).getLastName(),i,2);
            table1.setValueAt(personelList.get(i).getTel_number(),i,3);
            table1.setValueAt(personelList.get(i).getEmail(),i,4);
            table1.setValueAt(personelList.get(i).getSallary()+" TL",i,5);
            table1.setValueAt(personelList.get(i).getBirim(),i,6);
            table1.setValueAt(personelList.get(i).getRol(),i,7);
            table1.setValueAt(personelList.get(i).getStartDate(),i,8);
            table1.setEnabled(false);
        }
    }

    public void clickRemove(PersonelRepository personelRepository,MouseEvent e){
        int row=table1.rowAtPoint(e.getPoint());
        int col= table1.columnAtPoint(e.getPoint());
        if(e.getClickCount()==2){
            String value = table1.getValueAt(row,col).toString();
            String name = table1.getValueAt(row,col+1).toString();
            String surname = table1.getValueAt(row,col+2).toString();
            personelRepository.deleteById(Integer.parseInt(value));
            JOptionPane.showMessageDialog(null, name+" "+surname+" silindi.");
            tabloYukle(personelRepository);
        }
    }

    public void setMaasCarpaniTxts(BirimRepository birimRepository,RolRepository rolRepo){
        yillikMC.setText("0.0");
        uretimMC.setText(String.valueOf(birimRepository.findByName("Üretim").getBirimCarpan()));
        satisMC.setText(String.valueOf(birimRepository.findByName("Satış-Pazarlama").getBirimCarpan()));
        ikMC.setText(String.valueOf(birimRepository.findByName("İnsan Kaynakları").getBirimCarpan()));
        muhasebeMC.setText(String.valueOf(birimRepository.findByName("Muhasebe").getBirimCarpan()));
        arastirmanMC.setText(String.valueOf(rolRepo.findByName("Araştırman").getRolCarpan()));
        ekipMC.setText(String.valueOf(rolRepo.findByName("Ekip Üyesi").getRolCarpan()));
        yoneticiMC.setText(String.valueOf(rolRepo.findByName("Yönetici").getRolCarpan()));
    }

}
