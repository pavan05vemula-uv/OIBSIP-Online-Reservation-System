import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class AdvancedReservationForm extends JFrame implements ActionListener {

    JLabel titleLabel, nameLabel, trainNoLabel, trainNameLabel, classLabel, dateLabel, fromLabel, toLabel;
    JTextField nameField, trainNoField, trainNameField, classField, dateField, fromField, toField;
    JButton insertButton;

    Map<String, String> trainMap = new HashMap<>();

    public AdvancedReservationForm() {
        setTitle("Advanced Reservation System");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 🎨 Set background color
        getContentPane().setBackground(new Color(245, 245, 245));  // Light gray

        // 🛤 Dummy train data for auto-fill
        trainMap.put("12345", "Express Line");
        trainMap.put("54321", "Mountain Rider");
        trainMap.put("67890", "City Connector");

        // 🖋 Font settings
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 15);
        Font titleFont = new Font("Segoe UI", Font.BOLD, 20);

        // 🏷 Labels
        titleLabel = new JLabel("Train Reservation Form", JLabel.CENTER);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(new Color(0, 102, 204)); // Blue

        nameLabel = new JLabel("Name:");
        trainNoLabel = new JLabel("Train Number:");
        trainNameLabel = new JLabel("Train Name:");
        classLabel = new JLabel("Class:");
        dateLabel = new JLabel("Date (YYYY-MM-DD):");
        fromLabel = new JLabel("From:");
        toLabel = new JLabel("To:");

        // ✍ Text fields
        nameField = new JTextField();
        trainNoField = new JTextField();
        trainNameField = new JTextField();
        classField = new JTextField();
        dateField = new JTextField();
        fromField = new JTextField();
        toField = new JTextField();

        // ⌨️ Auto-fill train name
        trainNoField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String trainNo = trainNoField.getText().trim();
                String trainName = trainMap.getOrDefault(trainNo, "");
                trainNameField.setText(trainName);
            }
        });

        // 🎯 Insert button
        insertButton = new JButton("Book Ticket");
        insertButton.setBackground(new Color(0, 153, 76)); // Green
        insertButton.setForeground(Color.WHITE);
        insertButton.setFocusPainted(false);
        insertButton.setFont(new Font("Segoe UI", Font.BOLD, 14));

        // 🔲 Panel for form
        JPanel formPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        formPanel.setBackground(new Color(245, 245, 245));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        // 🧩 Add fields to panel
        formPanel.add(nameLabel);       formPanel.add(nameField);
        formPanel.add(trainNoLabel);    formPanel.add(trainNoField);
        formPanel.add(trainNameLabel);  formPanel.add(trainNameField);
        formPanel.add(classLabel);      formPanel.add(classField);
        formPanel.add(dateLabel);       formPanel.add(dateField);
        formPanel.add(fromLabel);       formPanel.add(fromField);
        formPanel.add(toLabel);         formPanel.add(toField);
        formPanel.add(new JLabel());    formPanel.add(insertButton);

        // 🔧 Set label fonts
        for (Component comp : formPanel.getComponents()) {
            if (comp instanceof JLabel) {
                comp.setFont(labelFont);
            }
        }

        // 🧱 Main layout
        setLayout(new BorderLayout(10, 10));
        add(titleLabel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);

        // 🎬 Button click
        insertButton.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Get user input
        String name = nameField.getText().trim();
        String trainNo = trainNoField.getText().trim();
        String trainName = trainNameField.getText().trim();
        String cls = classField.getText().trim();
        String date = dateField.getText().trim();
        String from = fromField.getText().trim();
        String to = toField.getText().trim();

        // Validate input
        if (name.isEmpty() || trainNo.isEmpty() || trainName.isEmpty() ||
            cls.isEmpty() || date.isEmpty() || from.isEmpty() || to.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!", "Missing Info", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Generate PNR
        String pnr = UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        // Save to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reservation.txt", true))) {
            writer.write(pnr + "|" + name + "|" + trainNo + "|" + trainName + "|" +
                    cls + "|" + date + "|" + from + "|" + to);
            writer.newLine();
            JOptionPane.showMessageDialog(this, "✅ Ticket Booked!\nYour PNR: " + pnr, "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving reservation: " + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        nameField.setText("");
        trainNoField.setText("");
        trainNameField.setText("");
        classField.setText("");
        dateField.setText("");
        fromField.setText("");
        toField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdvancedReservationForm());
    }
}
