package game.enemies;

import game.Utils;

import game.bases.BoxCollider;
import game.bases.FrameCounter;
import game.bases.GameObject;
import game.bases.Vector2D;
import game.bases.physics.PhysicsBody;
import game.bases.renderers.ImageRenderer;
import game.player.Player;

import java.util.Random;

/**
 * Created by VALV on 7/20/2017.
 */
public class BlueEnemy extends GameObject implements PhysicsBody {
    FrameCounter coolDownspawE;
    FrameCounter coolDownCounter;
    boolean spellDissabled;
    boolean enemyDissabled;
    Vector2D velocity;
    public BoxCollider boxCollider;
    public static BlueEnemy instance;
    public int HP;

    private ImageRenderer imageRenderer1;
    private ImageRenderer imageRenderer2;
    private ImageRenderer imageRenderer3;
    private ImageRenderer imageRenderer4;
    FrameCounter changePicture;

    public BlueEnemy() {
        this.velocity = new Vector2D();
        instance = this;
        this.HP = 10;
        this.coolDownspawE = new FrameCounter(150);
        this.coolDownCounter = new FrameCounter(50);
        this.imageRenderer1 = new ImageRenderer(Utils.loadAssetImage("enemies/level0/blue/0.png"));
        this.imageRenderer2 = new ImageRenderer(Utils.loadAssetImage("enemies/level0/blue/1.png"));
        this.imageRenderer3 = new ImageRenderer(Utils.loadAssetImage("enemies/level0/blue/2.png"));
        this.imageRenderer4 = new ImageRenderer(Utils.loadAssetImage("enemies/level0/blue/3.png"));
        this.renderer = imageRenderer3;
        this.changePicture = new FrameCounter(3);
        this.boxCollider = new BoxCollider(15, 15);
        this.children.add(boxCollider);
    }

    public void spawEnemy() {
        if (!enemyDissabled) {
            Random random = new Random();
            float rand = random.nextFloat() * (350 - 20) + 20;
            position.set(rand, 10);
//            position.set(192,300);
            GameObject.add(this);
            enemyDissabled = true;
        }
    }

    public void coolDownspawn() {
        if (enemyDissabled) {
            boolean status = coolDownspawE.run();
            if (status) {
                enemyDissabled = false;
                coolDownspawE.reset();
            }
        }
    }

    public void changePicture() {
        if (changePicture.run()) {
            if (renderer == imageRenderer1) renderer = imageRenderer2;
            else if (renderer == imageRenderer2) renderer = imageRenderer3;
            else if (renderer == imageRenderer3) renderer = imageRenderer4;
            else renderer = imageRenderer1;
            changePicture.reset();
        }

    }


    public void castSpell() {
        if (!spellDissabled) {
            Vector2D target = Player.instance.position;
            Vector2D bulletVelocity = target.substract(position).normalize().multiply(3);

            BlueEnemySpell blueEnemySpell = new BlueEnemySpell();
            blueEnemySpell.velocity.set(bulletVelocity);
            blueEnemySpell.position.set(this.position.add(0, 10));
            GameObject.add(blueEnemySpell);
            spellDissabled = true;
        }
    }

    public void coolDownSpell() {
        if (spellDissabled) {
            boolean status = coolDownCounter.run();
            if (status) {
                spellDissabled = false;
                coolDownCounter.reset();
            }
        }

    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        move();
        castSpell();
        coolDownSpell();
        if (this.position.x < 0 || this.position.x > 385 || this.position.y > 600 || this.position.y < 10)
            this.isActive = false;


//        System.out.println(this.boxCollider + " xxxxxxxxxxxxxxxxxx");
    }

    public void move() {
        this.velocity.set(0, 1);
        this.position.addUp(velocity);

    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
