package horsequeen.iu;

import horsequeen.gamelogic.HorseQueenGame;
import horsequeen.gamelogic.HorseQueenStatus;
import horsequeen.gamelogic.Movement;
import horsequeen.gamelogic.PositioningHeuristic;
import horsequeen.gamelogic.PossibleMovementsHeuristic;
import horsequeen.gamelogic.QueenStackHeuristic;
import horsequeen.ia.AdversarialSearch;
import horsequeen.ia.AlphaBetaSearch;
import horsequeen.ia.Heuristic;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

/**
 *
 * @author josue
 */
public class GameView extends JFrame {

    private Tabletop tabletop;
    private OptionPanel optionPanel;
    private LogPanel logPanel;
    HorseQueenGame horseQueenGame;
    AdversarialSearch search;

    public GameView() {

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(400, 400));
        this.setVisible(true);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        horseQueenGame = new HorseQueenGame();
        horseQueenGame.setWhitePlayerHeuristic(new PositioningHeuristic());
        horseQueenGame.setBlackPlayerHeuristic(new PositioningHeuristic());
        search = new AlphaBetaSearch(horseQueenGame, 2);
        createOptionPanel();
        createLogPanel();
        cretateTabletop();
        
        pack();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    

    private void reset() {
        remove(tabletop);
        horseQueenGame = new HorseQueenGame();
        horseQueenGame.setWhitePlayerHeuristic(new PositioningHeuristic());
        horseQueenGame.setBlackPlayerHeuristic(new PositioningHeuristic());
        search = new AlphaBetaSearch(horseQueenGame, 2);
       
        cretateTabletop();
        logPanel.cleanLog();
        repaint();
        pack();
    }

    private JButton createResetButton() {
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        return resetButton;
    }

    private JButton createMakeDecisionButton() {
        JButton makeDecisionButton = new JButton("Make decision");
        makeDecisionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optionPanel.getDesicionLabel().setVisible(true);
                Movement move =
                        (Movement) search.makeDecision(horseQueenGame.getActualStatus());
                horseQueenGame.move(move);
                optionPanel.getDesicionLabel().setVisible(false);
                logPanel.writeLog(move.toString() + "\n");
                tabletop.update();

            }
        });
        return makeDecisionButton;
    }

    private JComboBox<Heuristic> createWhiteHeuristicComboBox() {
        JComboBox<Heuristic> whiteHeuristicComboBox = new JComboBox<>();
        whiteHeuristicComboBox.addItem(new PositioningHeuristic());
        whiteHeuristicComboBox.addItem(new PossibleMovementsHeuristic());
        whiteHeuristicComboBox.addItem(new QueenStackHeuristic());
        whiteHeuristicComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                horseQueenGame.setWhitePlayerHeuristic((Heuristic) e.getItem());
            }
        });
        return whiteHeuristicComboBox;
    }

    private JComboBox<Heuristic> createBlackHeuristicComboBox() {
        JComboBox<Heuristic> blackHeuristicComboBox = new JComboBox<>();
        blackHeuristicComboBox.addItem(new PositioningHeuristic());
        blackHeuristicComboBox.addItem(new PossibleMovementsHeuristic());
        blackHeuristicComboBox.addItem(new QueenStackHeuristic());
        blackHeuristicComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                horseQueenGame.setBlackPlayerHeuristic((Heuristic) e.getItem());
            }
        });
        return blackHeuristicComboBox;
    }

    private void createOptionPanel() {
        optionPanel = new OptionPanel(500, 100);
        optionPanel.setResetButton(createResetButton());
        optionPanel.setMakeDecisionButton(createMakeDecisionButton());
        optionPanel.setWhiteHeuristicComboBox(createWhiteHeuristicComboBox());
        optionPanel.setBlackHeuristicComboBox(createBlackHeuristicComboBox());
        optionPanel.setDesicionLabel();
        optionPanel.setVisible(true);
        optionPanel.setBackground(Color.lightGray);
        this.add(optionPanel);
    }

    private void cretateTabletop() {
        tabletop = new Tabletop(HorseQueenStatus.ROWS, HorseQueenStatus.COLS,
                500, 500, horseQueenGame);
        tabletop.setVisible(true);
        this.add(tabletop);
    }

    private void createLogPanel() {
        logPanel = new LogPanel(500,60);
        logPanel.setLogText();
        this.add(logPanel);
    }
}
