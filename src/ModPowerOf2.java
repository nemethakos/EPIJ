
public class ModPowerOf2 {

	public static long modPowerOf2(long word, int i) {
		if (i <= 0 || i >= 64) {
			throw new IllegalArgumentException("i > 0 && i <= 63");
		}
		long mask = 0;
		long v = 1;
		while (i > 0) {
			mask |= v;
			v <<= 1;
			i--;
		}
		return word & mask;
	}

	public static boolean isPowerOf2(long word) {
		long mask = word & ~(word-1);
		return (word ^ mask) == 0;
	}
	
	public static long modPowerOf2Alt(long word, long powerOf2) {
		// only one bit could be set
		if (!isPowerOf2(powerOf2)) {
			throw new IllegalArgumentException("powerOf2 is not a power of 2");
		}
		// if powerOf2 is a power of 2, then powerOf2-1, is all bit set from positions
		// log2(powerOf2)-1 to 0. AND-ing the number with the powerOf2-1 results in word
		// MOD powerOf2

		return word & (powerOf2 - 1);
	}

	public static void test(long word, int i) {
		long result = modPowerOf2(word, i);
		long resultAlt = modPowerOf2Alt(word, (long) Math.pow(2, i));
		System.out.format("word=%d,i=%d, word MOD 2^i=%d, alt=%d\n", word, i, result, resultAlt);
	}

	public static void main(String[] args) {
		test(77, 6);
		
		System.out.println(isPowerOf2(1));
		
		System.out.println(isPowerOf2(1000));

	}

}
