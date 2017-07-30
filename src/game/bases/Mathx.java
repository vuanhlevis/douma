package game.bases;

/**
 * Created by VALV on 7/16/2017.
 */
public class Mathx {
//    public Mathx(float i, float i1, int i2) {
//    }

    public static float clamp(float x, float y, float z)
    {
        if (x < y) return y;
        else if (x > z) return z;
        else return x;
    }
}
