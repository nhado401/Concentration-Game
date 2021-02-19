/*Concentration game - Written by Nha Do in 2018*/

package concentration;

/**
 *
 * @author nhado
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
public class Concentration extends JFrame implements ActionListener, MouseListener   {

    /**
     * @param args the command line arguments
     */
    Container content = this.getContentPane();
    JLabel[] lblBoard = new JLabel[16];
    int[] nums = {1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8};
    int firstChoice = -1;
    int tries = 0;
    JLabel lblFirst = new JLabel();
    JButton btnGame = new JButton("New Game");
    JLabel lblTries = new JLabel("0");
    JPanel pnlControls = new JPanel();
    JPanel pnlBoard = new JPanel();
    Font ft1 = new Font("Helvetica", Font.BOLD, 24);
    
    public Concentration()
    {
        this.setTitle("Concentration Game written by Nha Do");
        this.setVisible(true);
        this.setSize(500,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.createLabels();
        JLabel lbl = new JLabel("Number of tries");
        this.shuffle();
        pnlControls.setLayout(new FlowLayout());
        content.add(pnlBoard);
        pnlControls.add(btnGame);
        pnlControls.add(lbl, BorderLayout.EAST);
        pnlControls.add(lblTries);
        content.add(pnlControls, BorderLayout.SOUTH);
        btnGame.addActionListener(this);
    }
        
    public void createLabels()
    {
        pnlBoard.setLayout(new GridLayout(4,4,5,5));
        for(int i = 0; i < 16; i++)
        {
            lblBoard[i] = new JLabel("", JLabel.CENTER);
            lblBoard[i].setOpaque(true);
            lblBoard[i].setBackground(Color.MAGENTA);
            lblBoard[i].setForeground(Color.WHITE);
            lblBoard[i].setFont(ft1);
            lblBoard[i].addMouseListener(this);
            lblBoard[i].setName("" + i );
            pnlBoard.add(lblBoard[i]);
        }
    }
    
    public void shuffle()
    {
        int num1, num2, temp;
        Random r = new Random();
        for(int i=0; i < 500; i++)
        {
            num1 = r.nextInt(nums.length);
            num2 = r.nextInt(nums.length);
            temp = nums[num1];
            nums[num1] = nums[num2];
            nums[num2] = temp;
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        this.shuffle();
        firstChoice = -1;
        for(int i=0; i<lblBoard.length; i++)
        {
            lblBoard[i].setText("");
        }
        tries = 0;
        lblTries.setText(Integer.toString(tries));
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        JLabel l = (JLabel) me.getSource();
        int theNumber = Integer.parseInt(l.getName());
            if(firstChoice == -1)
            {
                l.setText(Integer.toString(nums[theNumber]));
                lblFirst = l;
                firstChoice = theNumber;
            }
            else if(nums[theNumber]!=nums[firstChoice])
            {
                l.setText(Integer.toString(nums[theNumber]));
                pnlBoard.paintImmediately(0,0, pnlBoard.getWidth(), pnlBoard.getHeight());
                try {
                    Thread.sleep(500);
                } catch (java.lang.InterruptedException ex) {
                }
                lblFirst.setText("");
                l.setText("");
                lblFirst = null;
                firstChoice = -1;
                tries++;
            }
            else
            {
                l.setText(Integer.toString(nums[theNumber]));
                firstChoice = -1;
                tries++;
            }
        lblTries.setText(Integer.toString(tries));
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
    
            public static void main(String[] args) {
        Concentration c = new Concentration();
    }

}
