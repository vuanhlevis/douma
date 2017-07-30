package game.enemies;

import game.Utils;

import game.bases.BoxCollider;
import game.bases.GameObject;
import game.bases.Vector2D;
import game.bases.physics.PhysicsBody;
import game.bases.renderers.ImageRenderer;

import javax.swing.*;

/**
 * Created by VALV on 7/21/2017.
 */
public class PinkEnemySpell extends GameObject implements PhysicsBody {
    Vector2D nextPosition = new Vector2D();
    boolean status = true;
    BoxCollider boxCollider;
    double S;
    public PinkEnemySpell () {
        this.renderer = new ImageRenderer(Utils.loadAssetImage("enemies/bullets/blue.png"));
        this.boxCollider = new BoxCollider(10,10);
    }
//    public void move(float dx, float dy) {
//        this.position.addUp(dx,dy);
//    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (S > 200) status = false;
        if (status)
        {
            this.position.addUp(this.nextPosition.x , this.nextPosition.y );
            S += Math.sqrt(this.nextPosition.x * this.nextPosition.x + this.nextPosition.y * this.nextPosition.y);
        }
        else {
            this.position.addUp(this.nextPosition.x / 4 , this.nextPosition.y / 4 );
        }
        if (this.position.x < 0 || this.position.x > 385 || this.position.y > 600 || this.position.y < 10)
            this.isActive = false;

    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
