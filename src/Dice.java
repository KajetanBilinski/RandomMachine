import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Dice implements RandomMachine{



    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JLabel lPanel = new JLabel();
    ImageIcon backImage;
    ImageIcon panelImage;
    ImageIcon butImage;

    Dice()
    {
        try {
            backImage = new ImageIcon(ImageIO.read(new File("src/Images/panelB.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            panelImage = new ImageIcon(ImageIO.read(new File("src/Images/BackGroundPanel.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            butImage = new ImageIcon(ImageIO.read(new File("src/Images/DiceGradient.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setup();
    }

    private void setup()
    {
        // Panel DiceNumbers
        lPanel.setLayout(new GridBagLayout());
        lPanel.setIcon(panelImage);
        // END Panel DiceNumbers

        // Panel INPUT
        JLabel background = new JLabel(backImage);
        FlowLayout fl = new FlowLayout();
        fl.setHgap(15);
        background.setLayout(fl);
        for (int i = 1; i <=6 ; i++)
        {
            JButton but = new JButton(butImage);
            but.setName(i+"");
            but.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    lPanel.removeAll();
                    setupDices(Integer.parseInt(but.getName()));
                    lPanel.repaint();
                    lPanel.revalidate();
                }
            });
            JLabel napis = new JLabel(i+"");
            napis.setHorizontalTextPosition(AbstractButton.CENTER);
            napis.setVerticalTextPosition(AbstractButton.CENTER);
            napis.setForeground(new Color(0));
            but.add(napis);
            background.add(but);
        }
        JButton exit = new JButton("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Main.GUI();
            }
        });
        background.add(exit);
        // END Panel INPUT

        // Frame Setup
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(600,600));
        frame.setLayout(new BorderLayout());
        frame.add(background,BorderLayout.SOUTH);
        frame.add(lPanel,BorderLayout.CENTER);
        frame.pack();

        // END Frame Setup
    }

    private void setupDices(int ilosc)
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy=1;
        gbc.gridx=0;
        for (int i = 0; i <ilosc ; i++)
        {
            int number = Dice();
            ImageIcon icon = null;
            JLabel backG = new JLabel();
            try {
                icon = new ImageIcon(ImageIO.read(new File("src/DiceNumbers/"+number+".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            backG.setIcon(icon);
            if(i==3)
            {
                gbc.gridy=2;
                gbc.gridx=0;
            }
            lPanel.add(backG,gbc);
            gbc.gridx++;
        }

    }


}
