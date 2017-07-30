package game.bases;

/**
 * Created by VALV on 7/16/2017.
 */
public class Vector2D {
    public float x;
    public float y;

    public static final Vector2D ZERO = new Vector2D(0,0);

    public Vector2D (float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector2D()
    {
        this(0,0);
    }

    public void addUp(float x, float y)
    {
        this.x += x;
        this.y += y;
    }

    public void addUp(Vector2D other)
    {
        addUp(other.x, other.y);
    }

    public Vector2D add(float x, float y)
    {
        return new Vector2D(this.x + x, this.y + y);
    }

    public Vector2D add(Vector2D other)
    {
        return add(other.x,other.y);
    }

    public void set (float x, float y)
    {
        this.x = x;
        this.y = y;
    }
    public void set(Vector2D other)
    {
        set(other.x,other.y);
    }



    public Vector2D clone()
    {
        Vector2D c = new Vector2D(x,y);
        return c;
    }
    public Vector2D invert()
    {
        Vector2D a = new Vector2D(-x, -y);
        return a;
    }

    public float magnitude()
    {
        double s;
        s = Math.sqrt(x*x + y*y);
        return (float) s;
    }
    public Vector2D substract(float x, float y) {
        return new Vector2D(this.x - x ,this.y - y);
    }

    public Vector2D substract(Vector2D other) {
        return substract(other.x,other.y);
    }

    public Vector2D normalize() {
        float length =(float) Math.sqrt(x * x + y * y);
        return new Vector2D(x / length, y / length);
    }

    public Vector2D multiply(float f) {
        return new Vector2D(x * f, y * f);
    }

//    public void normallize()
//    {
//
//    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
