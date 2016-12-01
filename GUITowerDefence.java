import javafx.geometry.Pos;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.*;
import java.util.List;

/**
 * Very, very basic GUI for very basic "Tower Defence" game
 */
public class GUITowerDefence extends JFrame implements ActionListener {

    private final Map<Position, JPanel> positionsPanels = new HashMap<>();
    private final MonsterPanel monsterPanel = new MonsterPanel();
    private final Timer timer;
    private static final int SPEED = 1000;
    private static final int PAUSE = 1000;
    private JPanel lands = getLandscapePanel();

    public static void main(String[] args) {
        new GUITowerDefence("Tower Defence").setVisible(true);
    }

    public GUITowerDefence(String title) {
        super(title);
        TowerDefenceGame TwrDfc = buildTowerDefence();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        init();
        this.add(lands, BorderLayout.CENTER);
        this.setSize(800, 300);
        timer = new Timer(SPEED, this);
        timer.setInitialDelay(PAUSE);
        // Will generate ActionEvents
        timer.start();
        lands.setVisible(true);

    }

    // ---------- Event handling --------------------

    @Override
    public void actionPerformed(ActionEvent e) {


    }

    // ---------- Render (if actionPerformed to large) ---------------

    public void render(){
       // JPanel greenPanel = new JPanel();
        //positionsPanels
    }

    public void init(){
        lands.setLayout(new GridLayout(3,8));
        JPanel[] place = new JPanel[24];
        int p = 0;

        for(int i = 0; i < 3; i = i + 1) {
            for (int n = 0; n < 8; n = n + 1) {
                place[p]= new JPanel();
                lands.add(place[p]);
                positionsPanels.put(new Position(i,n), place[p]);
                p++;
            }
        }

        place[1].setBackground(Color.green);


    }


    // ---------- Build model ----------


    private TowerDefenceGame buildTowerDefence() {
        return new TowerDefenceGame();
    }


    // ----------- Build GUI ---------------------

    private JPanel getLandscapePanel() {
        return new JPanel(); // Just for now ...
    }

    // Given a file name returns a label with an icon
    private JLabel getIconLabel(String fileName) {
        URL url = this.getClass().getResource(fileName);
        ImageIcon ii = new ImageIcon(url);
        JLabel lbl = new JLabel(ii);
        return lbl;
    }


    // -------------- Inner class ------------------
    // Use if you like
    private class MonsterPanel extends JPanel {

        private JLabel monster;
        private JLabel health = new JLabel();

        public MonsterPanel() {
            this.setBackground(Color.WHITE);
            this.setLayout(new BorderLayout());
            this.monster = getIconLabel("icons/monster10.gif");
            health.setFont(new Font("Serif", Font.BOLD, 10));
            this.add(monster, BorderLayout.CENTER);
            this.add(health, BorderLayout.SOUTH);
        }

        public void setHealth(int health) {
            this.health.setText(String.valueOf(health));
        }
    }

}
