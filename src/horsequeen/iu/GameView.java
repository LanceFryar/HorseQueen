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

    public GameView() {

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Horse Queen");
        this.setSize(new Dimension(400, 425));
        this.setVisible(true);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        horseQueenGame = new HorseQueenGame();
        horseQueenGame.setWhitePlayerSearch(new AlphaBetaSearch(horseQueenGame, 4));
        horseQueenGame.setBlackPlayerSearch(new AlphaBetaSearch(horseQueenGame, 4));
        horseQueenGame.setWhitePlayerHeuristic(new QueenStackHeuristic());
        horseQueenGame.setBlackPlayerHeuristic(new QueenStackHeuristic());

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
        horseQueenGame.setWhitePlayerHeuristic(optionPanel.getWhiteHeuristic());
        horseQueenGame.setBlackPlayerHeuristic(optionPanel.getBlackHeuristic());
        horseQueenGame.setWhitePlayerSearch(new AlphaBetaSearch(horseQueenGame, 
                optionPanel.getWhiteDepth()));
        horseQueenGame.setBlackPlayerSearch(new AlphaBetaSearch(horseQueenGame, 
                optionPanel.getBlackDepth()));

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

    private JButton createPlayButton() {
        JButton playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while (!horseQueenGame.getActualStatus().isTerminal()) {
                    decideNextMovement();
                    tabletop.update();
                }
            }
        });
        return playButton;

    }

    private JButton createMakeDecisionButton() {
        JButton makeDecisionButton = new JButton("Make decision");
        makeDecisionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!horseQueenGame.getActualStatus().isTerminal()) {
                    decideNextMovement();
                    tabletop.update();
                }
            }
        });
        return makeDecisionButton;
    }

    private void decideNextMovement() {
        logPanel.writeLog("Decidiendo...\n");
        Movement move;
        if (horseQueenGame.getPlayer(horseQueenGame.getActualStatus()) == HorseQueenStatus.WHITE) {
            move = (Movement) horseQueenGame.getWhitePlayerSearch().makeDecision(
                    horseQueenGame.getActualStatus());
            horseQueenGame.move(move);
            logPanel.writeLog(
                    move.toString()
                    + "\n"
                    + horseQueenGame.getWhitePlayerSearch().getMetrics()
                    + "\n");
        } else {
            move = (Movement) horseQueenGame.getBlackPlayerSearch().makeDecision(
                    horseQueenGame.getActualStatus());
            horseQueenGame.move(move);
            logPanel.writeLog(
                    move.toString()
                    + "\n"
                    + horseQueenGame.getBlackPlayerSearch().getMetrics()
                    + "\n");
        }

    }

    private JComboBox<Integer> createWhiteDepth(){
        JComboBox<Integer> whiteDepthComboBox = new JComboBox<>();
        whiteDepthComboBox.addItem(2);
        whiteDepthComboBox.addItem(4);
        whiteDepthComboBox.addItem(6);
        whiteDepthComboBox.addItem(8);
        whiteDepthComboBox.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                horseQueenGame.setWhitePlayerSearch(
                        new AlphaBetaSearch(horseQueenGame,(int) e.getItem()));
            }
        });
        return whiteDepthComboBox;
    }
    
    private JComboBox<Integer> createBlackDepth(){
        JComboBox<Integer> blackDepthComboBox = new JComboBox<>();
        blackDepthComboBox.addItem(2);
        blackDepthComboBox.addItem(4);
        blackDepthComboBox.addItem(6);
        blackDepthComboBox.addItem(8);
        blackDepthComboBox.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                horseQueenGame.setBlackPlayerSearch(
                        new AlphaBetaSearch(horseQueenGame,(int) e.getItem()));
            }
        });
        return blackDepthComboBox;
    }
    
    private JComboBox<Heuristic> createWhiteHeuristicComboBox() {
        JComboBox<Heuristic> whiteHeuristicComboBox = new JComboBox<>();
        whiteHeuristicComboBox.addItem(new QueenStackHeuristic());
        whiteHeuristicComboBox.addItem(new PositioningHeuristic());
        whiteHeuristicComboBox.addItem(new PossibleMovementsHeuristic());
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
        blackHeuristicComboBox.addItem(new QueenStackHeuristic());
        blackHeuristicComboBox.addItem(new PositioningHeuristic());
        blackHeuristicComboBox.addItem(new PossibleMovementsHeuristic());

        blackHeuristicComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                horseQueenGame.setBlackPlayerHeuristic((Heuristic) e.getItem());
            }
        });
        return blackHeuristicComboBox;
    }

    private void createOptionPanel() {
        optionPanel = new OptionPanel(500, 125);
        optionPanel.setResetButton(createResetButton());
        optionPanel.setMakeDecisionButton(createMakeDecisionButton());
        optionPanel.setPlayButton(createPlayButton());
        optionPanel.setWhiteHeuristicComboBox(createWhiteHeuristicComboBox());
        optionPanel.setWhiteDepth(createWhiteDepth());
        optionPanel.setBlackHeuristicComboBox(createBlackHeuristicComboBox()); 
        optionPanel.setBlackDepth(createBlackDepth());
        optionPanel.setVisible(true);
        optionPanel.setBackground(Color.lightGray);
        this.add(optionPanel);
    }

    private void cretateTabletop() {
        tabletop = new Tabletop(HorseQueenStatus.ROWS, HorseQueenStatus.COLS,
                500, 500, horseQueenGame, logPanel);
        tabletop.setVisible(true);
        this.add(tabletop);
    }

    private void createLogPanel() {
        logPanel = new LogPanel(525, 60);
        logPanel.setLogText();
        this.add(logPanel);
    }
}
