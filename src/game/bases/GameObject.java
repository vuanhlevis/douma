package game.bases;

import game.bases.physics.Physics;
import game.bases.physics.PhysicsBody;
import game.bases.renderers.Renderer;

import java.awt.*;
import java.util.Vector;

/**
 * Created by VALV on 7/18/2017.
 */
public class GameObject {
    public Vector2D position; // relative
    public Vector2D screenPosition; // screen
    public boolean isActive;

    public Renderer renderer;

    public Vector<GameObject> children;
    private static Vector<GameObject> gameObjects = new Vector<>();
    private static Vector<GameObject> newgameObjects = new Vector<>();

    public static void add(GameObject gameObject) {
        newgameObjects.add(gameObject);
        if (gameObject instanceof PhysicsBody) {
            Physics.add((PhysicsBody) gameObject);
        }
    }


    public static void renderAll(Graphics2D graphics2D) {
        for (GameObject gameObject : gameObjects) {
            if (gameObject.isActive)
                gameObject.render(graphics2D);
        }
    }

    public static void changeAllPicture() {
        for (GameObject gameObject : gameObjects) {
            gameObject.changePicture();
        }
    }

    public void changePicture() {

    }

    public static void runAll() {
        for (GameObject gameObject : gameObjects) {
            if (gameObject.isActive) {
                gameObject.run(Vector2D.ZERO);
            }
        }
        gameObjects.addAll(newgameObjects);
        newgameObjects.clear();
//        System.out.println(gameObjects.size());

    }

    public void render(Graphics2D g2d) {
        if (renderer != null) {
            renderer.render(g2d,this.position);
        }
    }

    public GameObject() {
        this.position = new Vector2D();
        this.screenPosition = new Vector2D();
        this.children = new Vector<>();
        this.renderer = null;
        this.isActive = true;
    }

    public boolean isActive() {
        return isActive;
    }

    public void run(Vector2D parentPosition) {
        // position = relative
        this.screenPosition = parentPosition.add(position);
        for (GameObject child : children) {
            child.run(this.screenPosition);
        }
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }
}
