import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args)
    {
        new GUI();
    }

    public static class GUI
    {
        JFrame frameMain = new JFrame();
        JLabel backGround=new JLabel();


        GUI()
        {
            setup();
        }

        private void setup()
        {
            JButton exit = new JButton("Exit");
            exit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frameMain.dispose();
                    System.exit(0);
                }
            });
            GridBagConstraints gbc = new GridBagConstraints();
            try {
                backGround.setIcon(new ImageIcon(ImageIO.read(new File("src/Images/BackGround.png"))));
            } catch (IOException e) {
                e.printStackTrace();
            }
            ImageIcon icon1 = null;
            ImageIcon icon2 = null;
            try {
                icon1 =new ImageIcon(ImageIO.read(new File("src/Images/Gradient_1.jpg")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                icon2 =new ImageIcon(ImageIO.read(new File("src/Images/Gradient_2.jpg")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            backGround.setLayout(new GridBagLayout());
            for (int i = 0; i < 5; i++)
            {
                gbc.gridy++;
                JButton button=new JButton();
                if(i%2==0)
                    button.setIcon(icon1);
                else
                    button.setIcon(icon2);
                JLabel label = new JLabel();
                button.setPreferredSize(new Dimension(150,50));
                 if(i==0)
                 {
                     label.setText("Number FROM - TO");
                     button.addActionListener(new ActionListener() {
                         @Override
                         public void actionPerformed(ActionEvent e) {
                            frameMain.dispose();
                            new NumberFromTo();
                         }
                     });
                 }
                 else if(i==1)
                 {
                     label.setText("Dice");
                     button.addActionListener(new ActionListener() {
                         @Override
                         public void actionPerformed(ActionEvent e) {
                             frameMain.dispose();
                             new Dice();
                         }
                     });
                 }
                 else if(i==2)
                 {
                     label.setText("Coin Flip");
                     button.addActionListener(new ActionListener() {
                         @Override
                         public void actionPerformed(ActionEvent e) {
                             frameMain.dispose();
                             new CoinFlip();
                         }
                     });
                 }
                 else if(i==3)
                 {
                     label.setText("Random with %");
                     button.addActionListener(new ActionListener() {
                         @Override
                         public void actionPerformed(ActionEvent e) {
                             frameMain.dispose();
                             new RandomWithPercent();
                         }
                     });
                 }
                 else
                 {
                     label.setText("              EXIT");
                     button.addActionListener(new ActionListener() {
                         @Override
                         public void actionPerformed(ActionEvent e) {
                             frameMain.dispose();
                             System.exit(0);
                         }
                     });
                 }
                 label.setHorizontalTextPosition(AbstractButton.CENTER);
                 label.setVerticalTextPosition(AbstractButton.CENTER);
                 label.setForeground(new Color(0));
                button.add(label,Component.CENTER_ALIGNMENT);
                backGround.add(button,gbc);
            }
            frameMain.setTitle("Random Machine");
            frameMain.setLayout(new BorderLayout());
            frameMain.add(backGround,BorderLayout.CENTER);
            frameMain.setVisible(true);
            frameMain.setPreferredSize(new Dimension(550,600));
            frameMain.setResizable(false);
            frameMain.pack();
        }
    }


}
