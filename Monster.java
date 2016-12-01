
public class Monster {
    private int health;
    private int hit = 10;
    private Position currentPosition;

    public Monster (){
        this.health=100;
        Position start = new Position(0, 1);
        this.currentPosition = start;
    }
    public void loseHealth(){
        this.health = health - this.hit;
    }

    public Position getCurrentPosition(){
        return this.currentPosition;
    }
    public int getHealth(){
    	return this.health;
    }

}
