public class TowerDefenceGame implements Game {
	
	    private Monster monster;
	    private Tower towerOne;
	    private Tower towerTwo;
	    private enum movements {FRONT, UP, DOWN}
	    private movements[] pathOne = {movements.FRONT, movements.FRONT,
	    		movements.UP, movements.FRONT, movements.FRONT, movements.DOWN,
	    		movements.DOWN, movements.FRONT, movements.FRONT, movements.FRONT};
	    private int n = 0;
	    
	public TowerDefenceGame(){
		this.monster = new Monster();
		this.towerOne = new Tower(new Position(3,1));
		this.towerTwo = new Tower(new Position(5,2));
	}

    @Override
    public void nextTurn() {
    	
    	towerAttack();
    	
        movements move = pathOne[n];

        switch (move) {
            case FRONT:
                monster.getCurrentPosition().moveForward();
                break;
            case UP:
                monster.getCurrentPosition().moveUp();
                break;
            case DOWN:
                monster.getCurrentPosition().moveDown();
                break;
        }
        endGame();
        n++;
    }

    @Override
    public void endGame() {
    	if(getMonsterHealth() <= 0){
    		System.exit(0);
    	}
    	else if(monster.getCurrentPosition().getXPosition() == 7) {
            System.out.println(getMonsterHealth());
            System.out.println(monster.getCurrentPosition().toString() + " The monster made it through!");
            System.exit(0);
        }
    }

    @Override
    public void towerAttack() {
    	if(towerOne.mosterScout(monster.getCurrentPosition())) {
            towerOne.shoot(monster);
        }
        if(towerTwo.mosterScout(monster.getCurrentPosition())){
    		towerTwo.shoot(monster);
    	}
    }

    @Override
    public int getMonsterHealth() {
        return monster.getHealth();
    }
    public Position getMonsterPosition(){
    	return monster.getCurrentPosition();
    }
}

