import javax.crypto.spec.PBEKeySpec;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
import java.io.OutputStream;

public class EnigmaFrame extends JFrame implements ActionListener{
    private JComboBox<String> rotor1;
    private JComboBox<String> rotor2;
    private JComboBox<String> rotor3;

    private JTextField startText;
    private JTextArea inText;
    private JTextArea outText;
    private JButton enc;
    private JButton dec;

    private JLabel in;
    private JLabel out;
    private JLabel middle;
    private JLabel input;
    private JLabel output;

    private JPanel p;
    private JPanel panelTop;
    private JPanel panelMiddle;
    private JPanel panelBottom;
    private JPanel helpPanel;
    private JPanel moreHelp;
    private JPanel otherHelp;




    private final String[] numbers = {
        "1","2","3","4","5"
    };

    public EnigmaFrame(){
        super();
        p = new JPanel();
        
        startText = new JTextField("###", 5);
        rotor1 = new JComboBox<String>(numbers);
        rotor2 = new JComboBox<String>(numbers);
        rotor3 = new JComboBox<String>(numbers);

        inText = new JTextArea(5,20);
        outText = new JTextArea(5,20);
        outText.setEditable(false);

        enc = new JButton("Encrypt");
        dec = new JButton("Decrypt");

        in = new JLabel("Inner");
        out = new JLabel("Outer");
        middle = new JLabel("Middle");
        input = new JLabel("Input");
        output = new JLabel("Output");

        panelTop = new JPanel();
        panelMiddle = new JPanel();
        panelBottom = new JPanel();
        helpPanel = new JPanel();
        moreHelp = new JPanel();
        otherHelp = new JPanel();
        
        panelTop.add(in, BorderLayout.WEST);
        panelTop.add(rotor1, BorderLayout.CENTER);

        panelBottom.add(middle, BorderLayout.WEST);
        panelBottom.add(rotor2, BorderLayout.EAST);

        helpPanel.add(out, BorderLayout.WEST);
        helpPanel.add(rotor3, BorderLayout.EAST);

        panelMiddle.add(startText, BorderLayout.WEST);
        panelMiddle.add(enc, BorderLayout.CENTER);
        panelMiddle.add(dec, BorderLayout.EAST);

        moreHelp.add(input, BorderLayout.WEST);
        moreHelp.add(inText, BorderLayout.CENTER);
        otherHelp.add(output, BorderLayout.WEST );
        otherHelp.add(outText, BorderLayout.CENTER );

        p.add(panelTop, BorderLayout.WEST);
        p.add(panelBottom, BorderLayout.CENTER);
        p.add(helpPanel, BorderLayout.EAST);

        p.add(panelMiddle, BorderLayout.NORTH);
       // p.add(moreHelp, BorderLayout.SOUTH);

        enc.addActionListener(this);
        dec.addActionListener(this);

        this.add(p, BorderLayout.NORTH);
        this.add(moreHelp, BorderLayout.CENTER);
        this.add(otherHelp, BorderLayout.SOUTH);

      //  this.add(inText, BorderLayout.CENTER);
       // this.add(outText, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

    }

    public void actionPerformed(ActionEvent e) {
        int r1 = rotor1.getSelectedIndex() + 1;
        int r2 = rotor2.getSelectedIndex() + 1;
        int r3 = rotor3.getSelectedIndex() + 1;
        // System.out.println(r1 );//+ r2 + r3 + "" );

        String enigmaStart = startText.getText();
        
        Enigma enigma = new Enigma(r1, r2, r3, enigmaStart);

        if (e.getSource() == enc){
            outText.setText(enigma.encrypt(inText.getText()));
        }
        else if (e.getSource() == dec){
            outText.setText(enigma.decrypt(inText.getText()));

        }

    }

}
