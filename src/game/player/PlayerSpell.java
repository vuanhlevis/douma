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
public class PlayerSpell extends GameObject implements PhysicsBody{
    //properties : thuoc tinh
    private BoxCollider boxCollider;
    public PlayerSpell(){
        super();
        this.renderer = new ImageRenderer(Utils.loadAssetImage("player-spell/a/0.png"));

        boxCollider = new BoxCollider(20,20);
        children.add(boxCollider);
    }

    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        this.position.addUp(0, -10);
        hitEnemy();
        if (this.position.y < 0) {
            this.isActive  = false;
        }
    }

    private void hitEnemy() {
        BlueEnemy hitEnemy = Physics.bodyInRect(this.boxCollider, BlueEnemy.class);
        if (hitEnemy != null) {
            hitEnemy.isActive = false;
            this.isActive = false;
            PlayerSpellExplove playerSpellExplove = GameObjectPool.recycle(PlayerSpellExplove.class);
            playerSpellExplove.isActive = true;
            playerSpellExplove.position = this.screenPosition;
        }

        BossEnemy bossEnemy = Physics.bodyInRect(this.boxCollider, BossEnemy.class);
        if (bossEnemy != null) {
            bossEnemy.isActive = false;
            this.isActive = false;
            PlayerSpellExplove playerSpellExplove = GameObjectPool.recycle(PlayerSpellExplove.class);
            playerSpellExplove.isActive = true;
            playerSpellExplove.position = this.screenPosition;
        }

        PinkEnemy pinkEnemy = Physics.bodyInRect(this.boxCollider, PinkEnemy.class);
        if (pinkEnemy != null) {
            pinkEnemy.isActive = false;
            this.isActive = false;
            PlayerSpellExplove playerSpellExplove = GameObjectPool.recycle(PlayerSpellExplove.class);
            playerSpellExplove.isActive = true;
            playerSpellExplove.position = this.screenPosition;
        }
//        System.out.println(hitEnemy);

    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
