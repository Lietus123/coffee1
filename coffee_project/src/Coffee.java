import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Coffee {
    public JPanel rootPanel;
    public JLabel textField1;
    public JLabel textField2;
    public JButton Button;
    public JComboBox comboBox1;
    public JComboBox comboBox2;
    public JTextField textField;
    public JCheckBox checkBox;
    public JComboBox comboBox3;
    public double summ = 0;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Kafija");
        frame.setContentPane(new Coffee().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800, 600);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        comboBox1 = new JComboBox<>();
        comboBox1.addItem("Small");
        comboBox1.addItem("Medium");
        comboBox1.addItem("Large");

        comboBox2 = new JComboBox<>();
        comboBox2.addItem("Latte");
        comboBox2.addItem("Espresso");
        comboBox2.addItem("Cappuccino");

        comboBox3 = new JComboBox<>();
        comboBox3.addItem("Cow");
        comboBox3.addItem("Soy");
        comboBox3.addItem("Almond");
    }

    public double totalprice() {
        double summ = 0;

        String size = (String) comboBox1.getSelectedItem();
        if (size.equalsIgnoreCase("Small")) {
            summ = summ + 0.25;
        } else if (size.equalsIgnoreCase("Medium")) {
            summ = summ + 0.50;
        } else if (size.equalsIgnoreCase("Large")) {
            summ = summ + 0.75;
        }

        String coffeetype = (String) comboBox2.getSelectedItem();
        if (coffeetype.equalsIgnoreCase("Latte")) {
            summ = summ + 2.25;
        } else if (coffeetype.equalsIgnoreCase("Espresso")) {
            summ = summ + 2.00;
        } else if (coffeetype.equalsIgnoreCase("Cappuccino")) {
            summ = summ + 2.50;
        }

        String milktype = (String) comboBox3.getSelectedItem();
        if (milktype.equalsIgnoreCase("Cow")) {
            summ = summ + 0.25;
        } else if (milktype.equalsIgnoreCase("Soy")) {
            summ = summ + 0.50;
        } else if (milktype.equalsIgnoreCase("Almond")) {
            summ = summ + 0.60;
        }

        if (checkBox.isSelected()) {
            summ = summ + 0.50;
        }
        return summ;
    }

    public Coffee() {
        Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ordernumber = (int) (Math.random() * 99) + 1;
                int optionResult = JOptionPane.showConfirmDialog(null, "Your order cost " + totalprice() + "\nAre You sure?");
                if (optionResult == 0) {
                    JOptionPane.showMessageDialog(rootPanel, "Your order has been made!" + "\nYour order number is " + ordernumber);
                    try {
                        FileWriter fw = new FileWriter("coffee");
                        if (checkBox.isSelected()) {
                            fw.write("Your order number is " + ordernumber +
                                    "\nYour drink is in size: " + comboBox1.getSelectedItem() +
                                    "\nYour drink is: " + comboBox2.getSelectedItem() +
                                    "\nYour drink's milk type is " + comboBox3.getSelectedItem() +
                                    "\nYour drink is iced"+
                                    "\nSugar tablespoon amount: " + textField.getText() +
                                    "\n" +
                                    "\nYour total price is " + totalprice());
                        } else {
                            fw.write("Your order number is " + ordernumber +
                                    "\nYour drink is in size: " + comboBox1.getSelectedItem() +
                                    "\nYour drink is: " + comboBox2.getSelectedItem() +
                                    "\nYour drink's milk type is " + comboBox3.getSelectedItem() +
                                    "\nYour drink is not iced"+
                                    "\nSugar tablespoon amount: " + textField.getText() +
                                    "\n" +
                                    "\nYour total price is " + totalprice());
                        }
                        fw.close();
                    } catch (IOException n) {
                        throw new RuntimeException(n);
                    }
                }
            }
        });
    }
}
