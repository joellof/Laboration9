import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.*;

/**
 * Very, very basic GUI for very basic "Tower Defence" game
 */
public class GUITowerDefence extends JFrame implements ActionListener {

    private final Map<Position, JPanel> positionsPanels = new HashMap<>();
    private final Timer timer;
    private static final int SPEED = 500;
    private static final int PAUSE = 500;
    private JPanel lands = getLandscapePanel();
    private TowerDefenceGame TwrDfc;
    private MonsterPanel monsterPanel;

    public static void main(String[] args) {
        new GUITowerDefence("Tower Defence").setVisible(true);
    }

    public GUITowerDefence(String title) {
        super(title);
        this.TwrDfc = buildTowerDefence();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);

        //To change course, uncomment one of the inits and choose path in TwrDfc-constructor
        init1();
        //init2();

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

        if(!TwrDfc.endGame() && !TwrDfc.deadMonster()) {

            TwrDfc.nextTurn();

            monsterPanel.setHealth(TwrDfc.getMonsterHealth());
            positionsPanels.get(TwrDfc.getMonsterPosition()).add(monsterPanel);

            repaint();
        }

        else if (TwrDfc.deadMonster()){

            timer.stop();
            dispose();

            JOptionPane.showMessageDialog(null, "The monster died, you win!");
        }

        else {

            timer.stop();
            dispose();

            JOptionPane.showMessageDialog(null, "Game Over!");
        }


    }

    public void init1(){
        lands.setLayout(new GridLayout(3,8));
        JPanel[] place = new JPanel[24];
        int p = 0;

        for(int i = 2; i >= 0; i = i - 1) {
            for (int n = 0; n < 8; n = n + 1) {
                place[p]= new JPanel();
                lands.add(place[p]);
                positionsPanels.put(new Position(n,i), place[p]);
                p++;
            }
        }

        int[] greenPos = new int[]{0,1,5,6,7,11,13,14,15,16,17,18,19};
        int[] whitePos = new int[]{2,3,4,8,9,10,12,20,21,22,23};

        for(int i = 0; i < greenPos.length; i++){
            place[greenPos[i]].setBackground(Color.green);
            place[greenPos[i]].setBorder(BorderFactory.createLineBorder(Color.black));
        }

        for(int n = 0; n < whitePos.length; n++){
            place[whitePos[n]].setBackground(Color.white);
            place[whitePos[n]].setBorder(BorderFactory.createLineBorder(Color.black));
        }

        JLabel tower1 = getIconLabel("icons/tower-icon.png");
        JLabel tower2 = getIconLabel("icons/tower-icon.png");

        place[5].add(tower1);
        place[11].add(tower2);

        monsterPanel = new MonsterPanel();
        positionsPanels.get(TwrDfc.getMonsterPosition()).add(monsterPanel);
        monsterPanel.setHealth(TwrDfc.getMonsterHealth());

    }

    public void init2(){
        lands.setLayout(new GridLayout(3,8));
        JPanel[] place = new JPanel[24];
        int p = 0;

        for(int i = 2; i >=0; i = i - 1) {
            for (int n = 0; n < 8; n = n + 1) {
                place[p]= new JPanel();
                lands.add(place[p]);
                positionsPanels.put(new Position(n,i), place[p]);
                p++;
            }
        }

        int[] greenPos = new int[]{0,1,2,3,4,5,6,7,11,16,17,21,22,23};
        int[] whitePos = new int[]{8,9,10,12,13,14,15,18,19,20};

        for(int i = 0; i < greenPos.length; i++){
            place[greenPos[i]].setBackground(Color.green);
            place[greenPos[i]].setBorder(BorderFactory.createLineBorder(Color.black));
        }

        for(int n = 0; n < whitePos.length; n++){
            place[whitePos[n]].setBackground(Color.white);
            place[whitePos[n]].setBorder(BorderFactory.createLineBorder(Color.black));
        }

        JLabel tower1 = getIconLabel("icons/tower-icon.png");
        JLabel tower2 = getIconLabel("icons/tower-icon.png");

        place[5].add(tower1);
        place[11].add(tower2);

        monsterPanel = new MonsterPanel();
        positionsPanels.get(TwrDfc.getMonsterPosition()).add(monsterPanel);
        monsterPanel.setHealth(TwrDfc.getMonsterHealth());

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
        //private JPanel panel =


        public MonsterPanel() {
            this.setOpaque(false);
            this.setLayout(new BorderLayout());
            this.monster = getIconLabel("icons/monster10.gif");
            health.setFont(new Font("Serif", Font.BOLD, 10));
            this.add(monster, BorderLayout.CENTER);
            this.add(health, BorderLayout.SOUTH);
        }

        public void setHealth(int health) {
            this.health.setText(String.valueOf(health));
        }

        public JLabel getMonster(){ return this.monster; }

        public JLabel getHealth() { return this.health; }


    }

}
