package org.example;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class Frame {
    boolean breakProgram = false;
    private JTextField input = new JTextField();

    public Frame() {
        JFrame frame = new JFrame("Simple frame");//        declara o frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//  para o programa quando o frame é fechado

        //criando a parte visual
        JLabel textLabel = new JLabel("I'm a label in the window", SwingConstants.CENTER);
        textLabel.setPreferredSize(new Dimension(300, 100));
        frame.getContentPane().add(textLabel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);

        //criando o botão
        frame.add(colocandoOBotaoESuasFuncionalidades(frame));
        //tornando o frame visivel
        frame.pack();
        frame.setVisible(true);
        //FONTE: https://www.thoughtco.com/create-a-simple-window-using-jframe-2034069
    }

    public JButton closeButton(JFrame frame){
        JButton button2 = new JButton("Click Here");
        button2.setBounds(150,195,95,30);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        return button2;
    }

    public JButton colocandoOBotaoESuasFuncionalidades(JFrame frame){
        JButton button = new JButton("Click Here");
        button.setBounds(50,100,95,30);
        frame.add(closeButton(frame));
        button.addActionListener(new ActionListener() {
            //FONTE: https://pt.stackoverflow.com/questions/401201/como-criar-um-evento-para-um-botão-no-swing-java
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(){
                    @Override
                    public void run(){
                        Robot robot = null;
                        try {
                            robot = new Robot();
                        } catch (AWTException ex) {
                            throw new RuntimeException(ex);
                        }
                        int x = 0;
                        int y = 0;

                        PointerInfo pointerInfo = MouseInfo.getPointerInfo();
                        Point infoLocation = pointerInfo.getLocation();
                        int initialY = (int) infoLocation.getY();

                        while(true){
                            x = (int) infoLocation.getX();
                            y = (int) infoLocation.getY();

                            robot.mouseMove(x - 30, y);
                            try {
                                TimeUnit.MINUTES.sleep(1);
                            } catch (InterruptedException ex) {
                                throw new RuntimeException(ex);
                            }

                            x = (int) infoLocation.getX();
                            y = (int) infoLocation.getY();

                            robot.mouseMove(x + 30, y);
                            try {
                                TimeUnit.MINUTES.sleep(1);
                            } catch (InterruptedException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                }.start();
            }
        });
        return button;
    }
}
/*Seguinte nao dá pra usar while nesse tipo de caso, o certo é usar threads
* o while bloqueia o jframe */
//https://www.devmedia.com.br/trabalhando-com-threads-em-java/28780