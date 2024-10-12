package ElectricityBillingSystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MeterInfo extends JFrame implements ActionListener {

    Choice meterLocChoice, meterTypChoice,phaseCodeChoice,billtypChoice;
    JButton submit;
    String meternumber;
    MeterInfo(String meternumber) {
        this.meternumber=meternumber;
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(252,186,3));
        add(panel);

        JLabel heading  =new JLabel("Meter Information");
        heading.setBounds(180,10,200,20);
        heading.setFont(new Font("Tahome",Font.BOLD,20));
        panel.add(heading);

        JLabel meterNumber = new JLabel("Meter Number");
        meterNumber.setBounds(50,80,100,20);
        panel.add(meterNumber);

         JLabel meterNumberText = new JLabel(meternumber);
         meterNumberText.setBounds(180,80,150,20);
         panel.add(meterNumberText);

        JLabel meterLoc = new JLabel("Meter Number");
        meterLoc.setBounds(50,120,100,20);
        panel.add(meterLoc);

        meterLocChoice =new Choice();
        meterLocChoice.add("Outside");
        meterLocChoice.add("Inside");
        meterLocChoice.setBounds(180,120,150,20);
        panel.add(meterLocChoice);

        JLabel meterTyp = new JLabel("Meter Type");
        meterTyp.setBounds(50,160,100,20);
        panel.add(meterTyp);

        meterTypChoice =new Choice();
        meterTypChoice.add("Electric Meter");
        meterTypChoice.add("Solar Meter");
        meterTypChoice.add("Smart Meter");
        meterTypChoice.setBounds(180,160,150,20);
        panel.add(meterTypChoice);

        JLabel phaseCode = new JLabel("Phase Code");
        phaseCode.setBounds(50,200,100,20);
        panel.add(phaseCode);

        phaseCodeChoice =new Choice();
        phaseCodeChoice.add("011");
        phaseCodeChoice.add("022");
        phaseCodeChoice.add("033");
        phaseCodeChoice.add("044");
        phaseCodeChoice.add("055");
        phaseCodeChoice.add("066");
        phaseCodeChoice.add("077");
        phaseCodeChoice.add("088");
        phaseCodeChoice.add("099");
        phaseCodeChoice.setBounds(180,200,150,20);
        panel.add(phaseCodeChoice);


        JLabel billtyp = new JLabel("Bill Type");
        billtyp.setBounds(50,240,100,20);
        panel.add(billtyp);

        billtypChoice =new Choice();
        billtypChoice.add("Normal");
        billtypChoice.add("Industrial");
        billtypChoice.setBounds(180,240,150,20);
        panel.add(billtypChoice);


        JLabel day = new JLabel("30 Days Billing Time...");
        day.setBounds(50,280,150,20);
        panel.add(day);

        JLabel note = new JLabel("Note:-");
        note.setBounds(50,320,100,20);
        panel.add(note);

        JLabel note1 = new JLabel("By default bill is calculated for 30 days only");
        note1.setBounds(50,340,300,20);
        panel.add(note1);

        submit = new JButton("Submit");
        submit.setBounds(220,390,100,25);
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        panel.add(submit);

        setLayout(new BorderLayout());
        add(panel,"Center");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("ElectricityBillingSystem/Icons/details.png"));
        Image i2 = i1.getImage().getScaledInstance(230,200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imgLabel = new JLabel(i3);
        add(imgLabel,"East");


        setSize(700,500);
        setLocation(400,200);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==submit){
            String smeterNum = meternumber;
            String smeterLoc = meterLocChoice.getSelectedItem();
            String smeterTyp = meterTypChoice.getSelectedItem();
            String sphaseCode = phaseCodeChoice.getSelectedItem();
            String sbillTyp = billtypChoice.getSelectedItem();
            String sday ="30";

            String query_meterInfo = "insert into MeterInfo values('"+smeterNum+"','"+smeterLoc+"','"+smeterTyp+"','"+sphaseCode+"','"+sbillTyp+"','"+sday+"')";
            try{
                DataBase c= new DataBase();
                c.statement.executeUpdate(query_meterInfo);

                JOptionPane.showMessageDialog(null,"Meter Information Submited Successfully");
                setVisible(false);
            }catch (Exception E){
                E.printStackTrace();
            }
        }else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new MeterInfo("");
    }
}
