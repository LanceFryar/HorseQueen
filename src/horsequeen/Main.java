package horsequeen;

import horsequeen.gamelogic.HorseQueenGame;
import horsequeen.gamelogic.HorseQueenStatus;
import horsequeen.iu.OPanel;
import horsequeen.iu.Tablero;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author josue
 */
public class Main extends JFrame {

    private Tablero tablero;
    private OPanel opciones;

    public Main() {

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(801, 601));
        this.setVisible(true);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        opciones = new OPanel(600, 80);
        opciones.setResetButton(resetButton);
        opciones.setVisible(true);
        opciones.setBackground(Color.lightGray);
        this.add(opciones);
        tablero = new Tablero(HorseQueenStatus.ROWS, HorseQueenStatus.COLS,
                600, 600, new HorseQueenGame());
        tablero.setVisible(true);
        this.add(tablero);
        pack();

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    public static void main(String[] args) {
        Main main = new Main();

    }

    private void reset() {
        remove(tablero);
        tablero = new Tablero(HorseQueenStatus.ROWS, HorseQueenStatus.COLS,
                600, 600, new HorseQueenGame());
        add(tablero);
        repaint();
        pack();
    }
}
