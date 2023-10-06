package euclid;

public class Euclid {
	/**
	 * Implementation requirement: must do recursively, as given in the spec.
	 * @param a First integer
	 * @param b Second integer
	 * @return The greatest common divisor of a and b using Euclid's recursive algorithm. 
	 */
	public static long gcd(long a, long b) {
		if (a==0)
			return b;
		return gcd(b%a, a);
	}
}
