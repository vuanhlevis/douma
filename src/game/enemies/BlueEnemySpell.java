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

/**
 * Created by VALV on 7/20/2017.
 */
public class BlueEnemySpell extends GameObject implements PhysicsBody {
    Vector2D velocity;
    Vector2D velocityPosition;
    BoxCollider boxCollider;
//    Vector2D lastPosition;


    public BlueEnemySpell() {
        this.renderer = new ImageRenderer(Utils.loadAssetImage("enemies/bullets/white.png"));
        this.velocity = new Vector2D();
        this.boxCollider = new BoxCollider(5, 5);
        this.children.add(boxCollider);
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        velocityPosition = Player.instance.position;
        if (Math.sqrt(velocityPosition.substract(this.position).x * velocityPosition.substract(this.position).x +
                velocityPosition.substract(this.position).y * velocityPosition.substract(this.position).y) < 100) {
            velocity = velocityPosition.substract(this.position).normalize().multiply(3);
        }
        this.position.addUp(velocity);
        if (this.position.x < 0 || this.position.x > 385 || this.position.y > 600 || this.position.y < 10)
            this.isActive = false;
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
