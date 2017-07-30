package game.enemies;

import game.Utils;

import game.bases.BoxCollider;
import game.bases.GameObject;
import game.bases.GameObjectPool;
import game.bases.Vector2D;
import game.bases.physics.Physics;
import game.bases.physics.PhysicsBody;
import game.bases.renderers.ImageRenderer;
import game.player.Player;
import game.player.PlayerExplove;

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
        if (this.position.x < 0 || this.position.x > 385 || this.position.y > 600 || this.position.y < 10) {
            this.isActive = false;
            this.S = 0;
            this.status = true;
        }


        hitPlayer();
    }

    private void hitPlayer() {
        Player player = Physics.bodyInRect(this.boxCollider, Player.class);
        if (player != null) {
            player.HP --;
            this.isActive = false;
            if (player.HP <= 0) {
                player.isActive = false;
                PlayerExplove playerExplove = GameObjectPool.recycle(PlayerExplove.class);
                playerExplove.isActive = true;
                playerExplove.position.set(this.position);
            }
        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
