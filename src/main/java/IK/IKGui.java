package IK;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.logging.Level;

public class IKGui {
    private JButton button1;
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JTable table1;
    private JFormattedTextField formattedTextField1;



    public IKGui() {
        org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("IK");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        PersonelRepository personelRepository = new PersonelRepository(entityManager);
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnCount(5);
        model.setRowCount(personelRepository.findAll().size()+1);
        table1.setModel(model);

        String[] header = {"ID","Name","Surname","Birim","Rol"};
        for (int i=0;i<5;i++){
            table1.setValueAt(header[i],0,i);
        }
        List<Personel> personelList = personelRepository.findAll();

        for (int i=0;i<personelList.size();i++){
            table1.setValueAt(personelList.get(i).getId(),i+1,0);
            table1.setValueAt(personelList.get(i).getFirstName(),i+1,1);
            table1.setValueAt(personelList.get(i).getLastName(),i+1,2);
            table1.setValueAt(personelList.get(i).getBirim(),i+1,3);
            table1.setValueAt(personelList.get(i).getRol(),i+1,4);
            table1.setEnabled(false);
        }

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
//               formattedTextField1.setText(personelRepository.findAll().get(0).getFirstName());


            }
        });
    }

    public static void  main(String[] args) {
        JFrame frame = new JFrame("IKGui");
        frame.setContentPane(new IKGui().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
