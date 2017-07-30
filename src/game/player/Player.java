package game.player;

import game.Utils;
import game.bases.*;
import game.bases.physics.PhysicsBody;
import game.bases.renderers.ImageRenderer;
import game.inputs.InputManager;

import javax.swing.*;

/**
 * Created by VALV on 7/11/2017.
 */
//
public class Player extends GameObject implements PhysicsBody{
    public int HP;

    Contraints contraints;
    InputManager inputManager;

    FrameCounter coolDownCounter;
    boolean spellDisabled;
    BoxCollider boxCollider;
    Vector2D velocity;

    public static Player instance;

    public Player() {
        super();
        this.boxCollider = new BoxCollider(5,5);
        this.HP = 100;
        this.velocity = new Vector2D();
        this.coolDownCounter = new FrameCounter(2);  // 17 frame  = 300 millisecond
        this.renderer = new ImageRenderer(Utils.loadAssetImage("players/straight/0.png"));
        instance = this;
        children.add(boxCollider);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);

        move();
        castSpell();
        System.out.println(this.HP);
    }

    private void castSpell() {
        if (!spellDisabled) {
            if (inputManager.xPress) {
                PlayerSpell playerSpell = GameObjectPool.recycle(PlayerSpell.class);
                playerSpell.position.set(this.position.add(0, -20));
            }
            spellDisabled = true;
        }
        coolDown();
    }


    private void move() {
        this.velocity.set(0, 0);
        if (inputManager.leftPress) this.velocity.x -= 10;
        if (inputManager.rightPress) this.velocity.x += 10;
        if (inputManager.upress) this.velocity.y -= 10;
        if (inputManager.downPress) this.velocity.y += 10;

        this.position.addUp(velocity);

        this.contraints.make(this.position);

    }

    public void coolDown() {
        if (spellDisabled) {
            boolean status = coolDownCounter.run();
            if (status) {
                spellDisabled = false;
                coolDownCounter.reset();
            }
        }
    }

    public void setContraints(Contraints contraints) {
        this.contraints = contraints;
    }

    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
