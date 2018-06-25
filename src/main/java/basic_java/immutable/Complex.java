package basic_java.immutable;

/**
 * Created by Mati on 05.02.2018.
 */
public final class Complex {
    /**
     * Rules:
     * 1. No setter methods
     * 2. Declare class as final to prevent inheritance
     * 3. All fields as final
     * 4. All fields as private
     * 5. Return new object in all calls
     */

    private final float re;
    private final float im;

    public Complex(float re, float im) {
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
    public Complex add(Complex c) {
        return new Complex(re + c.re, im + c.im);
    }
}
