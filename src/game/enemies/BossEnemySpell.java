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
import game.screnes.Settings;

import javax.swing.*;

/**
 * Created by VALV on 7/22/2017.
 */
public class BossEnemySpell extends GameObject implements PhysicsBody{
    boolean status1 = true;
    boolean status2 = false;
    float sum;

    BoxCollider boxCollider;
    Vector2D nexposition = new Vector2D();

    public BossEnemySpell() {
        this.renderer = new ImageRenderer(Utils.loadAssetImage("enemies/bullets/green.png"));
        this.boxCollider = new BoxCollider(10,10);
        children.add(boxCollider);
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (sum > 100) {
            status1 = false;
        }
        if (sum > 150) {
            status2 = true;
        }
        if (status1) {
            this.position.addUp(this.nexposition);
            sum += Math.sqrt(this.nexposition.x * this.nexposition.x + this.nexposition.y * this.nexposition.y);
        } else if (status2 == status1) {
            this.position.addUp(this.nexposition.x / 7, this.nexposition.y / 7);
            sum += Math.sqrt((this.nexposition.x / 7) * (this.nexposition.x / 7) + (this.nexposition.y / 7) * (this.nexposition.y / 7));
        } else if (status2) {
            this.position.addUp(this.nexposition.x / 1.5f, this.nexposition.y / 1.5f);
        }
        if (this.position.x < 0 || this.position.x > Settings.gameplaywidth || this.position.y > Settings.gameplayheight || this.position.y < 10) {
            this.isActive = false;
            sum = 0;
            status1 = true;
            status2 = false;
        }

        hitPlayer();
    }

    private void hitPlayer() {
        Player player = Physics.bodyInRect(this.boxCollider,Player.class);

        if (player != null) {
            player.HP --;
            this.isActive = false;
            this.sum = 0;
            this.status2 = false;
            this.status1 = true;
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
