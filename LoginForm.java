// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class LoginForm extends JFrame implements ActionListener {
   JLabel userLabel;
   JLabel passLabel;
   JLabel titleLabel;
   JTextField userField;
   JPasswordField passField;
   JButton loginButton;

   public LoginForm() {
      this.setTitle("User Login");
      this.setSize(350, 220);
      this.setDefaultCloseOperation(3);
      this.setLocationRelativeTo((Component)null);
      this.setResizable(false);
      this.getContentPane().setBackground(new Color(245, 245, 245));
      this.titleLabel = new JLabel("Reservation System Login", 0);
      this.titleLabel.setFont(new Font("Segoe UI", 1, 18));
      this.titleLabel.setForeground(new Color(0, 102, 204));
      this.userLabel = new JLabel("Username:");
      this.passLabel = new JLabel("Password:");
      this.userLabel.setFont(new Font("Segoe UI", 0, 14));
      this.passLabel.setFont(new Font("Segoe UI", 0, 14));
      this.userField = new JTextField(15);
      this.passField = new JPasswordField(15);
      this.loginButton = new JButton("Login");
      this.loginButton.setBackground(new Color(0, 153, 76));
      this.loginButton.setForeground(Color.WHITE);
      this.loginButton.setFocusPainted(false);
      this.loginButton.setFont(new Font("Segoe UI", 1, 14));
      JPanel var1 = new JPanel(new GridLayout(3, 2, 10, 10));
      var1.setBackground(new Color(245, 245, 245));
      var1.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
      var1.add(this.userLabel);
      var1.add(this.userField);
      var1.add(this.passLabel);
      var1.add(this.passField);
      var1.add(new JLabel());
      var1.add(this.loginButton);
      this.setLayout(new BorderLayout(10, 10));
      this.add(this.titleLabel, "North");
      this.add(var1, "Center");
      this.loginButton.addActionListener(this);
      this.setVisible(true);
   }

   public void actionPerformed(ActionEvent var1) {
      String var2 = this.userField.getText().trim();
      String var3 = (new String(this.passField.getPassword())).trim();
      if (var2.equals("pavan") && var3.equals("1234")) {
         this.dispose();
         new AdvancedReservationForm();
      } else {
         JOptionPane.showMessageDialog(this, "Invalid login credentials", "Login Failed", 0);
      }

   }

   public static void main(String[] var0) {
      SwingUtilities.invokeLater(() -> {
         new LoginForm();
      });
   }
}


