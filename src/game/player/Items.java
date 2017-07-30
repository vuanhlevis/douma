package game.player;

import game.Utils;
import game.bases.BoxCollider;
import game.bases.GameObject;
import game.bases.Vector2D;
import game.bases.physics.Physics;
import game.bases.physics.PhysicsBody;
import game.bases.renderers.ImageRenderer;
import game.screnes.Settings;


/**
 * Created by levua on 7/29/2017.
 */
public class Items extends GameObject implements PhysicsBody {
    Vector2D velocity;
    BoxCollider boxCollider;
    int power;
    public Items() {
        this.renderer = new ImageRenderer(Utils.loadAssetImage("items/power-up-red.png"));
        this.boxCollider = new BoxCollider(15,15);
        children.add(boxCollider);
        this.isActive = true;
        this.power = 1;
        this.velocity = new Vector2D();
        this.velocity.set(0,3);
    }

    @Override
    public void run(Vector2D parenPosition) {
        super.run(parenPosition);
        this.position.addUp(velocity);
        if (this.position.x < 0 || this.position.x > Settings.gameplaywidth ||
                this.position.y < 0 || this.position.y > Settings.gameplayheight)
            this.isActive = false;

        hitItem();
    }

    private void hitItem() {
        Player player = Physics.bodyInRect(this.boxCollider,Player.class);
        if (player!=null) {
            this.isActive = false;
            if (PlayerSpell.damage >= 100) {
                PlayerSpell.damage = 100;
            }
            else
                PlayerSpell.damage += this.power;
        }
    }


    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
