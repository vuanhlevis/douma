package game.player;

import game.Utils;
import game.bases.BoxCollider;
import game.bases.GameObject;
import game.bases.GameObjectPool;
import game.bases.renderers.ImageRenderer;
import game.bases.Vector2D;
import game.bases.physics.Physics;
import game.bases.physics.PhysicsBody;
import game.enemies.BlueEnemy;
import game.enemies.BossEnemy;
import game.enemies.PinkEnemy;

/**
 * Created by VALV on 7/11/2017.
 */
public class PlayerSpell extends GameObject implements PhysicsBody {
    //properties : thuoc tinh
    public static int damage;
    private BoxCollider boxCollider;

    public PlayerSpell() {
        super();
        this.renderer = new ImageRenderer(Utils.loadAssetImage("player-spell/a/0.png"));
        this.damage = 5;
        boxCollider = new BoxCollider(20, 20);
        children.add(boxCollider);
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        this.position.addUp(0, -10);
        hitEnemy();
        if (this.position.y < 0) {
            this.isActive = false;
        }
        System.out.println(damage);
    }

    private void hitEnemy() {
        BlueEnemy hitEnemy = Physics.bodyInRect(this.boxCollider, BlueEnemy.class);
        if (hitEnemy != null) {
            hitEnemy.HP -= this.damage;
            this.isActive = false;
            if (hitEnemy.HP <= 0) {
                PlayerSpellExplove playerSpellExplove = GameObjectPool.recycle(PlayerSpellExplove.class);
                playerSpellExplove.isActive = true;
                playerSpellExplove.position = this.screenPosition;
                Items items = GameObjectPool.recycle(Items.class);
                items.position.set(this.position);
                hitEnemy.isActive = false;
            }

        }

        BossEnemy bossEnemy = Physics.bodyInRect(this.boxCollider, BossEnemy.class);
        if (bossEnemy != null) {
            bossEnemy.HP -= damage;
            this.isActive = false;
            if (bossEnemy.HP <= 0) {
                PlayerSpellExplove playerSpellExplove = GameObjectPool.recycle(PlayerSpellExplove.class);
                playerSpellExplove.isActive = true;
                playerSpellExplove.position = this.screenPosition;
                bossEnemy.isActive = false;
            }

        }

        PinkEnemy pinkEnemy = Physics.bodyInRect(this.boxCollider, PinkEnemy.class);
        if (pinkEnemy != null) {
            pinkEnemy.HP -= damage;
            this.isActive = false;
            if (pinkEnemy.HP <= 0) {
                Items items = GameObjectPool.recycle(Items.class);
                items.power = 2;
                items.position.set(this.position);
                PlayerSpellExplove playerSpellExplove = GameObjectPool.recycle(PlayerSpellExplove.class);
                playerSpellExplove.isActive = true;
                playerSpellExplove.position = this.screenPosition;
                pinkEnemy.isActive = false;
            }

        }
//        System.out.println(hitEnemy);

    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
