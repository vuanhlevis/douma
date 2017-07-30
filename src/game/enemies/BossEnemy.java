package game.enemies;

import game.Utils;

import game.bases.BoxCollider;
import game.bases.FrameCounter;
import game.bases.GameObject;
import game.bases.Vector2D;
import game.bases.physics.PhysicsBody;
import game.bases.renderers.ImageRenderer;

/**
 * Created by VALV on 7/22/2017.
 */
public class BossEnemy extends GameObject implements PhysicsBody {
    private FrameCounter coolDownSpel;
    private boolean spellDissabled;
    private Vector2D velocity;
    boolean tmp = true;
    int i = 0, j = 12, k = 24;
    int count = 5;
    boolean left, right, down = true;

    public static BossEnemy instance;

    ImageRenderer imageRenderer1;
    ImageRenderer imageRenderer2;
    ImageRenderer imageRenderer3;
    FrameCounter changePicture;
    BoxCollider boxCollider;


    public BossEnemy() {
        this.coolDownSpel = new FrameCounter(2);
        this.renderer = new ImageRenderer(Utils.loadAssetImage("enemies/level0/black/0.png"));
        this.imageRenderer1 = new ImageRenderer(Utils.loadAssetImage("enemies/level0/black/0.png"));
        this.imageRenderer2 = new ImageRenderer(Utils.loadAssetImage("enemies/level0/black/1.png"));
        this.imageRenderer3 = new ImageRenderer(Utils.loadAssetImage("enemies/level0/black/2.png"));
        this.velocity = new Vector2D();
        this.changePicture = new FrameCounter(5);
        instance = this;
        this.boxCollider = new BoxCollider(40, 40);
        this.children.add(boxCollider);

    }

    public void spawnEnemyBoss() {
        BossEnemy enemyBoss = new BossEnemy();
        enemyBoss.position.set(192, 10);
        GameObject.add(enemyBoss);

    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        move();
        castSpell();
        if (this.position.x < 0 || this.position.x > 385 || this.position.y > 600 || this.position.y < 10)
            this.isActive = false;
    }

    public void changePicture() {
        if (changePicture.run()) {
            if (this.renderer == imageRenderer1) this.renderer = imageRenderer2;
            else if (this.renderer == imageRenderer2) this.renderer = imageRenderer3;
            else this.renderer = imageRenderer1;
            changePicture.reset();
        }
    }

    public void castSpell() {
        if (!spellDissabled) {
            if (tmp) {
                BossEnemySpell2 bossEnemySpell = new BossEnemySpell2();
                bossEnemySpell.nexposition = new Vector2D((float) (5 * Math.cos(Math.PI * i / 18)), (float) (5 * Math.sin(Math.PI * i / 18)));
                bossEnemySpell.position.set(this.position);
                GameObject.add(bossEnemySpell);
                i++;

                BossEnemySpell2 bossEnemySpell1 = new BossEnemySpell2();
                bossEnemySpell1.nexposition = new Vector2D((float) (5 * Math.cos(Math.PI * j / 18)), (float) (5 * Math.sin(Math.PI * j / 18)));
                bossEnemySpell1.position.set(this.position);
                GameObject.add(bossEnemySpell1);
                j++;

                BossEnemySpell2 bossEnemySpell2 = new BossEnemySpell2();
                bossEnemySpell2.nexposition = new Vector2D((float) (5 * Math.cos(Math.PI * k / 18)), (float) (5 * Math.sin(Math.PI * k / 18)));
                bossEnemySpell2.position.set(this.position);
                GameObject.add(bossEnemySpell2);
                k++;


                if (i > 36) {
                    i = 0;
                    j = 12;
                    k = 24;
                    tmp = false;
                    coolDownSpel = new FrameCounter(50);
                }
            } else {
                if (count > 0) {
                    for (int j = 0; j < 360; j += 10) {
                        BossEnemySpell bossEnemySpell = new BossEnemySpell();
                        bossEnemySpell.nexposition = new Vector2D((float) (5 * Math.cos(Math.PI * j / 180)), (float) (5 * Math.sin(Math.PI * j / 180)));
                        bossEnemySpell.position.set(this.position);
                        GameObject.add(bossEnemySpell);
                        coolDownSpel = new FrameCounter(4);
                    }

                    count--;
                } else {
                    tmp = true;
                    coolDownSpel = new FrameCounter(2);
                    count = 5;
                }


            }

            spellDissabled = true;
//            stopSpell --;
        } else {
            boolean status = coolDownSpel.run();
            if (status) {
                spellDissabled = false;
                coolDownSpel.reset();
            }
        }
    }

    boolean status = true;
    FrameCounter stopmove = new FrameCounter(100);

    public void move() {

        if (this.position.y >= 150 && status) {
            left = true;
            down = false;
            right = false;
            status = false;
        }

        if (this.position.x >= 350) {
            left = true;
            right = false;
            down = false;
        }
        if (this.position.x <= 30) {
            left = false;
            right = true;
            down = false;
        }

        if (left) {
            this.position.addUp(-2, 0);
        }
        if (right) {
            this.position.addUp(2, 0);
        }
        if (down) {
            this.position.addUp(0, 2);
        }
        stopmove.reset();
    }


    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}

