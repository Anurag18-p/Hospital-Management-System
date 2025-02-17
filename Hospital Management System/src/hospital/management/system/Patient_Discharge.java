package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;
import java.util.concurrent.Callable;

public class   Patient_Discharge extends JFrame {
    Patient_Discharge(){

        JPanel panel = new JPanel();
        panel.setBounds(5,5,790,390);
        panel.setBackground(new Color(90,156,163));
        panel.setLayout(null);
        add(panel);

        JLabel label = new JLabel("CHECKK-OUT");
        label.setBounds(100,20,150,20);
        label.setFont(new Font("Tahoma",Font.BOLD,20));
        label.setForeground(Color.WHITE);
        label.add(label);

        JLabel label2 = new JLabel("Customer ID");
        label2.setBounds(30,80,150,20);
        label2.setFont(new Font("Tahoma",Font.BOLD,14));
        label2.setForeground(Color.WHITE);
        panel.add(label2);

        Choice choice = new Choice();
        choice.setBounds(200,80,150,25);
        panel.add(choice);

        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from Patient_Info");
            while (resultSet.next()){
                choice.add(resultSet.getString("number"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        JLabel label3 = new JLabel("Room Number");
        label3.setBounds(30,130,150,20);
        label3.setFont(new Font("Tahoma",Font.BOLD,14));
        label3.setForeground(Color.WHITE);
        panel.add(label3);

        JLabel RNo = new JLabel();
        RNo.setBounds(200,130,150,20);
        RNo.setFont(new Font("Tahoma",Font.BOLD,14));
        RNo.setForeground(Color.WHITE);
        panel.add(RNo);

        JLabel label4 = new JLabel("In Time");
        label4.setBounds(30,180,250,20);
        label4.setFont(new Font("Tahoma",Font.BOLD,14));
        label4.setForeground(Color.WHITE);
        panel.add(label4);

        JLabel Intime = new JLabel();
        Intime.setBounds(200,180,150,20);
        Intime.setFont(new Font("Tahoma",Font.BOLD,14));
        Intime.setForeground(Color.WHITE);
        panel.add(Intime);

        JLabel label5 = new JLabel("Out Time");
        label5.setBounds(30,230,150,20);
        label5.setFont(new Font("Tahoma",Font.BOLD,14));
        label5.setForeground(Color.WHITE);
        panel.add(label5);

        Date date = new Date();

        JLabel OutTime = new JLabel(""+date);
        OutTime.setBounds(200,230,250,20);
        OutTime.setFont(new Font("Tahoma",Font.BOLD,14));
        OutTime.setForeground(Color.WHITE);
        panel.add(OutTime);

        JButton discharge = new JButton("Discharge");
        discharge.setBounds(30,300,120,30);
        discharge.setBackground(Color.BLACK);
        discharge.setForeground(Color.WHITE);
        panel.add(discharge);
        discharge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conn c = new conn();
                try {
                    c.statement.executeUpdate("delete fromm Patient_Info where number = '"+choice.getSelectedItem()+"'");
                    c.statement.executeUpdate("update from setAvailability = 'Available' where room_no = '"+RNo.getText()+"'");
                    JOptionPane.showMessageDialog(null,"Done");
                    setVisible(false);
                }catch (Exception E) {
                    E.printStackTrace();
                }

            }
        });

        JButton Check = new JButton("Check");
        Check.setBounds(170,300,120,30);
        Check.setBackground(Color.BLACK);
        Check.setForeground(Color.WHITE);
        panel.add(Check);
        Check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conn c = new conn();
                try {
                    ResultSet resultSet = c.statement.executeQuery("select * from Patient_Info where number = '"+choice.getSelectedItem()+"'");
                    while (resultSet.next()){
                        RNo.setText(resultSet.getString("Room_Number"));
                        Intime.setText(resultSet.getString("Time"));
                    }
                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        JButton Back = new JButton("BACk");
        Back.setBounds(300,300,120,30);
        Back.setBackground(Color.BLACK);
        Back.setForeground(Color.WHITE);
        panel.add(Back);
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setUndecorated(true);
        setSize(800,400);
        setLayout(null);
        setLocation(400,250);
        setVisible(true);

    }
    public static void main(String[] args) {
        new Patient_Discharge();
    }
}