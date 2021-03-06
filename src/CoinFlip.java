import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class CoinFlip implements RandomMachine {
    
    
    JFrame frame = new JFrame();
    JLabel backGroundPanel = new JLabel();
    JLabel panel= new JLabel();
    ImageIcon backIcon;
    ImageIcon panelIcon;
    ImageIcon butIcon;
    CoinFlip()
    {
        try {
            panelIcon = new ImageIcon(ImageIO.read(new File("src/Images/panelB.png")));
            backIcon = new ImageIcon(ImageIO.read(new File("src/Images/BackGroundPanel.jpg")));
             butIcon = new ImageIcon(ImageIO.read(new File("src/Images/Gradient_1.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setup();
    }

    private void setup()
    {
        // Panel Setup
        panel.setIcon(panelIcon);
        panel.setLayout(new FlowLayout());
        for (int i = 0; i < 2; i++)
        {
          JButton but = new JButton();
          but.setIcon(butIcon);
          but.add(new JLabel(i+1+""));
          but.setName(i+1+"");
          but.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e)
              {
                  backGroundPanel.removeAll();
                  doaFlip(Integer.parseInt(but.getName()));
              }
          });

          panel.add(but);
        }
        JButton exit = new JButton("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Main.GUI();
            }
        });
        panel.add(exit);
        //END Panel Setup

        // Background Panel Setup
        backGroundPanel.setLayout(new GridBagLayout());
        backGroundPanel.setIcon(backIcon);
        // END Background Panel Setup
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(550,750));
        frame.setLayout(new BorderLayout());
        frame.add(backGroundPanel,BorderLayout.CENTER);
        frame.add(panel,BorderLayout.SOUTH);
        frame.pack();
    }

    private void doaFlip(int ilosc)
    {
        for (int i = 0; i <ilosc ; i++)
        {
            int fliped = CoinFlip();
            CoinThread coinThread = new CoinThread(fliped);
            coinThread.start();
        }
    }
    public class CoinThread extends Thread {
        ImageIcon outputCoin;
        ImageIcon img1;
        ImageIcon img2;
        ImageIcon img3;

        CoinThread(int fliped)
        {
            try {
                img1 = new ImageIcon(ImageIO.read(new File("src/Coin/0.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                img2 = new ImageIcon(ImageIO.read(new File("src/Coin/1.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                img3 = new ImageIcon(ImageIO.read(new File("src/Coin/Side.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(fliped!=-1)
                outputCoin = fliped == 0 ? img1 : img2;
            else
                outputCoin=img3;
        }

        @Override
        public void run() {
            JLabel label = new JLabel();
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridy=backGroundPanel.getComponentCount()*3;
            backGroundPanel.add(label,gbc);
            int ilosc = NumberFromTo(30, 60);
            int fill = 0;
            ArrayList<ImageIcon> icons = new ArrayList<>();
            icons.add(img1);
            icons.add(img2);
            icons.add(img3);
            Collections.shuffle(icons);
            int sleepTime=0;
            for (int i = 0; i < ilosc; i++)
            {
                if(sleepTime==2)
                {
                    if (fill == 0)
                    {
                        label.setIcon(icons.get(0));
                    }
                    else if (fill == 1)
                    {
                        label.setIcon(icons.get(1));
                    }
                    else if(fill== 2)
                    {
                        label.setIcon(icons.get(2));
                        fill=-1;
                    }
                    label.repaint();
                    label.revalidate();
                    backGroundPanel.repaint();
                    backGroundPanel.revalidate();
                    sleepTime=0;
                    fill++;
                }
                try {
                    sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sleepTime++;
            }
            label.setIcon(outputCoin);
        }

    }
}
