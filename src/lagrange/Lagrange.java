package lagrange;

import static java.lang.Math.pow;

public class Lagrange {

    public float calculate(float[] polyLagrange, float x) {
        float res = 0;
        int exp = polyLagrange.length - 1;
        for (int i = 0; i < polyLagrange.length; i++) {
            res += polyLagrange[i] * pow(x, exp);
            exp--;
        }
        return res;
    }

    public float[] multiplyPolynomials(float[] a, float[] b) {
        int degA = a.length;
        int degB = b.length;
        float[] result = new float[degA + degB - 1];
        for (int i = 0; i < degA; i++) {
            for (int j = 0; j < degB; j++) {
                result[i + j] += a[i] * b[j];
            }
        }
        return result;
    }

    public float[] addPolynomials(float[] a, float[] b) {
        int degree = a.length;
        float[] c = new float[degree];
        for (int i = 0; i < degree; i++) {
            c[i] = a[i] + b[i];
        }
        return c;
    }

    public float[] getPolynomial(float[] xs, float[] ys) {
        int degree = xs.length;
        float[][] deltas = new float[degree][degree];
        float[] result = new float[degree];

        for (int i = 0; i < degree; i++) {
            deltas[i] = getDeltaPolynomial(xs, i);
        }

        for (int i = 0; i < degree; i++) {
            //System.out.println(Arrays.toString(deltas[i]));
            result = addPolynomials(result, scalePoly(deltas[i], ys[i]));
        }

        return result;
    }

    public float[] getDeltaPolynomial(float[] xs, int xpos) {
        float[] poly = {1};
        float denom = 1;
        for (int i = 0; i < xs.length; i++) {
            if (i != xpos) {
                float[] currentTerm = {1, -xs[i]};
                denom *= xs[xpos] - xs[i];
                poly = multiplyPolynomials(poly, currentTerm);
            }
        }
        return scalePoly(poly, 1 / denom);
    }

    public float[] scalePoly(float[] a, float k) {
        float[] b = new float[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i] * k;
        }
        return b;
    }

    public String convertToPolynomial(float[] coefs) {
        String s = "";
        int degree = coefs.length;
        for (int i = 0; i < degree; i++) {
            if (coefs[i] > 0 && s != "") {
                s += "  +  ";
            }
            if (coefs[i] < 0) {
                coefs[i] *= -1;
                s += "  -  ";
            }
            if (coefs[i] != 0) {
                if (coefs[i] == (int) coefs[i]) {
                    if (coefs[i] != 1 || (coefs[i] == 1 && degree - i - 1 == 0)) {
                        s += Integer.toString((int) coefs[i]);
                    }
                } else {
                    try {
                        s += Float.toString(coefs[i]).substring(0, 4);
                    } catch (Exception e) {
                        System.out.println("");
                    }
                }
            }
            if (coefs[i] != 0) {
                if (degree - i - 1 == 1) {
                    s += "x";
                } else if (degree - i - 1 > 1) {
                    s += "x^" + Integer.toString(degree - i - 1);
                }
            }

        }
        return "f(x) = " + s;
    }
}
