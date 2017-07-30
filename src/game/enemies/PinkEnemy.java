package game.enemies;

import game.Utils;
import game.bases.*;
import game.bases.physics.PhysicsBody;
import game.bases.renderers.ImageRenderer;


/**
 * Created by VALV on 7/21/2017.
 */
public class PinkEnemy extends GameObject implements PhysicsBody {
    FrameCounter coolDownCounter;
    FrameCounter coolDownspell;
    boolean stopCastSpell;
    Vector2D velocity;

    ImageRenderer imageRenderer1;
    ImageRenderer imageRenderer2;
    ImageRenderer imageRenderer3;
    ImageRenderer imageRenderer4;
    FrameCounter changePicture;
    BoxCollider boxCollider;

    public int HP;

    public static PinkEnemy instance;


    boolean move1 = true, move2, move3;

    boolean spellDissabled;
    boolean enemyDissabled;

    public PinkEnemy() {
        this.velocity = new Vector2D();
        this.coolDownCounter = new FrameCounter(50);
        this.coolDownspell = new FrameCounter(4);
        this.imageRenderer1 = new ImageRenderer(Utils.loadAssetImage("enemies/level0/pink/0.png"));
        this.imageRenderer2 = new ImageRenderer(Utils.loadAssetImage("enemies/level0/pink/1.png"));
        this.imageRenderer3 = new ImageRenderer(Utils.loadAssetImage("enemies/level0/pink/2.png"));
        this.imageRenderer4 = new ImageRenderer(Utils.loadAssetImage("enemies/level0/pink/3.png"));
        this.changePicture = new FrameCounter(2);
        this.boxCollider = new BoxCollider(16, 16);
        children.add(boxCollider);
        instance = this;
        this.HP = 50;
    }

    public void spawnEnemy() {
        if (!enemyDissabled) {
            PinkEnemy pinkEnemy = new PinkEnemy();
            pinkEnemy.position.set(96, 10);
            GameObject.add(pinkEnemy);


            enemyDissabled = true;
        }
    }

    public void changePicture() {
        if (changePicture.run()) {
            if (this.renderer == imageRenderer1) this.renderer = imageRenderer2;
            else if (this.renderer == imageRenderer2) this.renderer = imageRenderer3;
            else if (this.renderer == imageRenderer3) this.renderer = imageRenderer4;
            else this.renderer = imageRenderer1;
            changePicture.reset();
        }
    }

    public void spawnEnemy2() {

        if (!enemyDissabled) {
            PinkEnemy pinkEnemy = new PinkEnemy();
            pinkEnemy.position.set(288, 10);
            GameObject.add(pinkEnemy);

            enemyDissabled = true;

        }
    }

    int count = 3;

    public void castSpell() {
        if (!spellDissabled) {
            if (count > 0) {
                for (int j = 20; j < 160; j += 20) {
                    PinkEnemySpell pinkEnemySpell = GameObjectPool.recycle(PinkEnemySpell.class);
                    pinkEnemySpell.nextPosition = new Vector2D((float) (10 * Math.cos(Math.PI * j / 180)), (float) (10 * Math.sin(j * Math.PI / 180)));
                    pinkEnemySpell.position.set(this.position);

                    coolDownspell = new FrameCounter(8);
                }
                count--;
            } else {
                coolDownspell = new FrameCounter(250);
                count = 3;
            }
            spellDissabled = true;
        }
    }

    public void coolDownSpell() {
        if (spellDissabled && !stopCastSpell) {
            boolean status = coolDownspell.run();

            if (status) {
                spellDissabled = false;
                coolDownspell.reset();
            }
        }
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        castSpell();
        coolDownSpell();
        move();
        if (this.position.x < 0 || this.position.x > 385 || this.position.y > 600 || this.position.y < 10)
            this.isActive = false;
//        System.out.println(this.boxCollider);
    }

    public void move() {
        if (move1) {
            this.position.addUp(0, (float) 0.5);
        }
        if (move2) {
            this.position.addUp((float) -1, (float) 1);
        }
        if (move3) {
            this.position.addUp((float) 1, (float) 1);
        }

        if (this.position.x < 192 && this.position.y > 200) {
            move2 = true;
            move3 = false;
            move1 = false;
        }

        if (this.position.x > 192 && this.position.y > 200) {
            move3 = true;
            move1 = false;
            move2 = false;
        }

    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
