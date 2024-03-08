package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;

public class CalcView extends JFrame{
    private JTextField t_first_poly = new JTextField(30);
    private JTextField t_second_poly = new JTextField(30);
    private JTextField t_answear = new JTextField(30);
    private JButton b_add = new JButton("Addition");
    private JButton b_sub = new JButton("Substract");
    private JButton b_multi = new JButton("Multiplication");
    private JButton b_div = new JButton("Division");
    private JButton b_der = new JButton("Derivate");
    private JButton b_int = new JButton("Integration");
    private JButton b_clear = new JButton("Clear");
    public CalcView(){

        t_answear.setEditable(false);

        JPanel intro = new JPanel();
        intro.add(new JLabel("First Polynomial"));
        intro.add(t_first_poly);
        intro.add(new JLabel("Second Polynomial"));
        intro.add(t_second_poly);
        intro.add(new JLabel("ANSWEAR"));
        intro.add(t_answear);

        JPanel op = new JPanel();
        op.add(b_add);
        op.add(b_sub);
        op.add(b_multi);
        op.add(b_div);
        op.add(b_der);
        op.add(b_int);
        op.setBackground(new Color(190, 126, 144 ));
        op.setLayout(new GridLayout(3,2,10,10));

        intro.add(op);
        intro.add(Box.createVerticalStrut(10));
        intro.add(b_clear);

        this.setContentPane(intro);
        this.pack();

        this.setSize(350,300);
        this.getContentPane().setBackground(new Color(190, 126, 144  ));
        this.setTitle("Polynomial Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);


        //--------------------------------------------------------------------------------------------------------------
        b_add.addActionListener(new ActionListener (){
            @Override
            public void actionPerformed(ActionEvent e){
                Polinom p1 = new Polinom(t_first_poly.getText());
                Polinom p2 = new Polinom(t_second_poly.getText());
                p1.getHash(p1.getStringPoli());
                p2.getHash(p2.getStringPoli());
                p1.getAdd(p1,p2);
                t_answear.setText(p1.toString());
            }
        });
        b_sub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polinom p1 = new Polinom(t_first_poly.getText());
                Polinom p2 = new Polinom(t_second_poly.getText());
                p1.getHash(p1.getStringPoli());
                p2.getHash(p2.getStringPoli());
                p1.getSub(p1,p2);
                t_answear.setText(p1.toString());
            }
        });
        b_multi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polinom p1 = new Polinom(t_first_poly.getText());
                Polinom p2 = new Polinom(t_second_poly.getText());
                p1.getHash(p1.getStringPoli());
                p2.getHash(p2.getStringPoli());
                p1.getMulti(p1,p2);
                t_answear.setText(p1.toString());
            }
        });
        /*b_div.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polinom p1 = new Polinom(t_first_poly.getText());
                Polinom p2 = new Polinom(t_second_poly.getText());
                p1.getHash(p1.getStringPoli());
                p2.getHash(p2.getStringPoli());
                //p1.getDiv(p1,p2);
                t_answear.setText(p1.toString());
            }
        });*/
        b_der.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polinom p1 = new Polinom(t_first_poly.getText());
                p1.getHash(p1.getStringPoli());
                p1.getDer(p1);
                t_answear.setText(p1.toString());
            }
        });
        b_int.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polinom p1 =new Polinom(t_first_poly.getText());
                p1.getHash(p1.getStringPoli());
                t_answear.setText(p1.getInt(p1));
            }
        });

        b_clear.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ard0){
                t_first_poly.setText("");
                t_second_poly.setText("");
                t_answear.setText("");
            }
        });
    }
}
