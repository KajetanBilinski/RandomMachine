import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class RandomWithPercent implements RandomMachine{

    JFrame frame = new JFrame();
    JLabel backgroundPanel = new JLabel();
    JLabel panelLabel = new JLabel();
    JSlider slider = new JSlider();
    JLabel whowon = new JLabel();
    JLabel value = new JLabel("50%");

    ImageIcon backG;
    ImageIcon panelImage;
    ImageIcon butImage;
    RandomWithPercent()
    {
        try {
            backG = new ImageIcon(ImageIO.read(new File("src/Images/panelB.png")));
            panelImage = new ImageIcon(ImageIO.read(new File("src/Images/BackGroundPanel.jpg")));
             butImage = new ImageIcon(ImageIO.read(new File("src/Images/DiceGradient.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setup();
    }

    private void setup()
    {
        //Panel Label
         panelLabel.setLayout(new FlowLayout());
         panelLabel.setIcon(backG);
         JButton button = new JButton("Ok");
         button.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e)
             {
                 backgroundPanel.removeAll();
                 calculate(slider.getValue());
             }
         });
        JButton exit = new JButton("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Main.GUI();
            }
        });
         slider.setMaximum(100);
         slider.setMinimum(0);
         slider.setPreferredSize(new Dimension(200,75));
         FlowLayout fl2 = new FlowLayout();
         fl2.setVgap(10);
         slider.setLayout(fl2);
         slider.add(value);
         slider.addChangeListener(new ChangeListener() {
             @Override
             public void stateChanged(ChangeEvent e)
             {
                 value.setText(slider.getValue()+"%");
             }
         });
         panelLabel.add(button);
         panelLabel.add(slider);
         panelLabel.add(exit);
        //END Panel Label


        //Background Panel

        FlowLayout fl = new FlowLayout();
        fl.setHgap(10);
        backgroundPanel.setLayout(fl);
        backgroundPanel.setIcon(panelImage);
        whowon.setFont(new Font("Arial",Font.BOLD,30));
        //END Background Panel

        //Frame Setup
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(550,250));
        frame.add(backgroundPanel,BorderLayout.CENTER);
        frame.add(panelLabel,BorderLayout.SOUTH);
        frame.pack();
        //END Frame Setup
    }

    private void calculate(int value)
    {
        double val = value;

       if(Math.random()<=val/100)
        {
            whowon.setText("Won with "+value+"%");
            whowon.setForeground(new Color(0x11FF00));
        }
       else
       {
           whowon.setText("Lost with "+value+"%");
           whowon.setForeground(new Color(0xFF0000));
       }
        whowon.setBackground(new Color(0x3A000000, true));
       backgroundPanel.add(whowon);
       backgroundPanel.repaint();
       backgroundPanel.revalidate();
    }


}
