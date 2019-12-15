package IK;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen {

    private JPanel panel;
    private JPasswordField sifre;
    private JTextField username;
    private JButton signIn;
    private JLabel message;
    private IKGui ikGui;

    public LoginScreen(){
        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(sifre.getText().equals("sifre")&&username.getText().equals("admin")){
                    ikGui = new IKGui();
                    ikGui.init();
                }else{
                    message.setText("Hatalı kullanıcı adı ve ya şifre!");
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("LoginScreen");
        frame.setContentPane(new LoginScreen().panel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
