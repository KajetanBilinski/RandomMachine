import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;

public class NumberFromTo implements RandomMachine{


    JFrame frame = new JFrame();
    JFormattedTextField from;
    JFormattedTextField to;
    JButton ok= new JButton("Ok");
    JComboBox<Integer> combo = new JComboBox<>();
    JLabel panel = new JLabel();
    ImageIcon panelB;
    ImageIcon panelImage;

    NumberFromTo()
    {
        setupIcon();
        setup();
    }

    private void setupIcon()
    {
        try {
            panelB = new ImageIcon(ImageIO.read(new File("src/Images/panelB.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            panelImage = new ImageIcon(ImageIO.read(new File("src/Images/BackGroundPanel.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setup()
    {
        panel.setLayout(new GridBagLayout());
        panel.setIcon(panelImage);
        // JTextField
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(Integer.MIN_VALUE);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        from= new JFormattedTextField(formatter);
        to= new JFormattedTextField(formatter);
        to.addKeyListener(new KeyListenerFields(to));
        from.addKeyListener(new KeyListenerFields(from));
        // END JTextField

        // JButton ok
        GridBagConstraints gbc = new GridBagConstraints();
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!from.getText().isEmpty()&&!to.getText().isEmpty())
                {
                    panel.removeAll();
                    for (int i = 0; i <(int)combo.getSelectedItem() ; i++)
                    {
                        gbc.gridy++;
                        JLabel output = new JLabel();
                        String fromText = from.getText();
                        String toText = to.getText();
                        toText = toText.replace("\u00A0","");
                        fromText = fromText.replace("\u00A0","");
                        int fromInt = Integer.parseInt(fromText);
                        int toInt = Integer.parseInt(toText);
                        int outputVal = NumberFromTo(fromInt,toInt);
                        output.setFont(new Font("Arial",Font.BOLD,40));
                        output.setForeground(new Color(0x000000));
                        output.setHorizontalAlignment(AbstractButton.CENTER);
                        output.setText(outputVal+"");
                        panel.add(output,gbc);
                    }
                    panel.revalidate();
                    panel.repaint();
                }
            }
        });
        // END JButton ok

        // Panel INPUT

        for (int i = 1; i <=10 ; i++)
        {
            combo.addItem(i);
        }
        combo.setSelectedIndex(0);
        JButton clear = new JButton("Clear");
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                from.setValue(null);
                to.setValue(null);
                panel.removeAll();
                panel.repaint();
                panel.revalidate();
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
        JLabel backGround = new JLabel(panelB);
        backGround.setLayout(new FlowLayout());
        JLabel text1 = new JLabel("From");
        JLabel text2 = new JLabel("To");
        from.setPreferredSize(new Dimension(100,25));
        to.setPreferredSize(new Dimension(100,25));
        backGround.add(text1);
        backGround.add(from);
        backGround.add(text2);
        backGround.add(to);
        backGround.add(ok);
        backGround.add(clear);
        backGround.add(combo);
        backGround.add(exit);
        // END PANEL INPUT

        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        frame.add(panel,BorderLayout.CENTER);
        frame.add(backGround,BorderLayout.SOUTH);
        frame.setPreferredSize(new Dimension(550,600));
        frame.setResizable(false);
        frame.pack();
    }
}
