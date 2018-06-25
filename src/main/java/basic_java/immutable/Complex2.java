package basic_java.immutable;

/**
 * Created by Mati on 05.02.2018.
 */
public class Complex2 {
    /**
     * Rules:
     * 1. No setter methods
     * 3. All fields as final
     * 4. All fields as private
     * 5. Return new object in all calls
     */

    private final float re;
    private final float im;

    private Complex2(float re, float im) {
        this.re = re;
        this.im = im;
    }

    // only getters

    public float getRe() {
        return re;
    }

    public float getIm() {
        return im;
    }

    // Return new Complex object
    public static Complex2 valueOf(float re, float im) {
        return new Complex2(re, im);
    }
}
