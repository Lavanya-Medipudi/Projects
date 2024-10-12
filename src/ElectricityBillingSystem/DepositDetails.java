package ElectricityBillingSystem;

import java.awt.*;
import javax.swing.*;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.proteanit.sql.DbUtils;

public class DepositDetails extends JFrame implements ActionListener {
    Choice searchMeterChoice,searchMonthChoice;
    JTable table;
    JButton search,print,close;

    DepositDetails(){
        super("Deposit Details");
        getContentPane().setBackground(new Color(192,186,254));
        setSize(700,500);
        setLocation(400,200);
        setLayout(null);

        JLabel searchMeter = new JLabel("Search By Meter Number");
        searchMeter.setBounds(20,20,150,20);
        add(searchMeter);

        searchMeterChoice = new Choice();
        searchMeterChoice.setBounds(180,20,150,20);
        add(searchMeterChoice);

        try{
            DataBase c= new DataBase();
            ResultSet resultSet = c.statement.executeQuery("select * from Bill");
            while (resultSet.next()){
                searchMeterChoice.add(resultSet.getString("meter_no"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        JLabel searchMonth = new JLabel("Search By Name");
        searchMonth.setBounds(400,20,100,20);
        add(searchMonth);

        searchMonthChoice = new Choice();
        searchMonthChoice.add("January");
        searchMonthChoice.add("February");
        searchMonthChoice.add("March");
        searchMonthChoice.add("April");
        searchMonthChoice.add("May");
        searchMonthChoice.add("June");
        searchMonthChoice.add("July");
        searchMonthChoice.add("August");
        searchMonthChoice.add("September");
        searchMonthChoice.add("October");
        searchMonthChoice.add("November");
        searchMonthChoice.add("December");
        searchMonthChoice.setBounds(520,20,150,20);
        add(searchMonthChoice);



        table = new JTable();
        try{
            DataBase c =new DataBase();
            ResultSet resultSet = c.statement.executeQuery("select * from Bill");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch (Exception e){
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,100,700,500);
        scrollPane.setBackground(Color.white);
        add(scrollPane);


        search = new JButton("Search");
        search.setBackground(Color.white);
        search.setBounds(20,70,80,20);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBackground(Color.white);
        print.setBounds(120,70,80,20);
        print.addActionListener(this);
        add(print);

        close = new JButton("Close");
        close.setBackground(Color.white);
        close.setBounds(600,70,80,20);
        close.addActionListener(this);
        add(close);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==search){
            String query_search = "select * from Bill where meter_no = '"+searchMeterChoice.getSelectedItem()+"' and month = '"+searchMonthChoice.getSelectedItem()+"'" ;
            try{
                DataBase c = new DataBase();
                ResultSet resultSet = c.statement.executeQuery(query_search);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            }catch (Exception E){
                E.printStackTrace();
            }
        } else if (e.getSource()==print)
        {
            try {
                table.print();
            } catch (Exception E) {
                E.printStackTrace();
            }

        }else {
            setVisible(false);
        }
    }
    public static void main(String[] args) {
        new DepositDetails();
    }
}
