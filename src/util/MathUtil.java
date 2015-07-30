package util;

public class MathUtil {

	public static float euclidDist(float[] a, float[] b) {
		double fSq = Math.pow(a[0] - b[0], 2);
		double sSq = Math.pow(a[1] - b[1], 2);
		return (float) Math.sqrt(fSq + sSq);
	}

}
