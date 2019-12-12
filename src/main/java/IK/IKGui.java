package IK;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JFormattedTextField formattedTextField1;



    public IKGui() {
        org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("IK");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        PersonelRepository personelRepository = new PersonelRepository(entityManager);
        BirimRepository birimRepository = new BirimRepository(entityManager);
        RolRepository rolRepo = new RolRepository(entityManager);

        tabloYukle(personelRepository);

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
    }

    public static void  main(String[] args) {
        JFrame frame = new JFrame("IKGui");
        frame.setContentPane(new IKGui().panel1);
        frame.setSize(1500,720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void personelEkle(BirimRepository birimRepository,RolRepository rolRepository,PersonelRepository personelRepository){
        Birim birim = birimRepository.findByName(birimTxt.getSelectedItem().toString());
        Rol rol = rolRepository.findByName(rolTxt.getSelectedItem().toString());
        Personel personel = new Personel(name.getText(),surname.getText(),birim, rol,tarih.getText(),email.getText(),tel.getText(),21222);

        try{
            personelRepository.save(personel);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void tabloYukle(PersonelRepository personelRepository){
        List<Personel> personelList = personelRepository.findAll();

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnCount(9);
        model.setRowCount(personelRepository.findAll().size()+1);
        table1.setModel(model);
        String[] header = {"ID","İSİM","SOYİSİM","TEL.","E-MAİL","MAAŞ","BİRİM","ROL","BAŞLANGIÇ TARİHİ"};

        for (int i=0;i<9;i++) {
            table1.setValueAt(header[i], 0, i);
        }
        for (int i=0;i<personelList.size();i++){
            table1.setValueAt(personelList.get(i).getId(),i+1,0);
            table1.setValueAt(personelList.get(i).getFirstName(),i+1,1);
            table1.setValueAt(personelList.get(i).getLastName(),i+1,2);
            table1.setValueAt(personelList.get(i).getTel_number(),i+1,3);
            table1.setValueAt(personelList.get(i).getEmail(),i+1,4);
            table1.setValueAt(personelList.get(i).getSallary(),i+1,5);
            table1.setValueAt(personelList.get(i).getBirim(),i+1,6);
            table1.setValueAt(personelList.get(i).getRol(),i+1,7);
            table1.setValueAt(personelList.get(i).getStartDate(),i+1,8);
            table1.setEnabled(false);
        }
    }
}
