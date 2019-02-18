
public class Parity {

	/**
	 * Brute force parity: flip parity on each 1 in word until word becomes zero
	 * (little better than for loop)
	 * 
	 * @param word long input
	 * @return parity bit
	 */
	public static int parityBruteForce(long word) {
		int parity = 0;

		while (word != 0) {
			parity ^= (int) (word & 1);
			word = word >>> 1;
		}

		return parity;
	}

	/**
	 * Improved parity: remove the last 1 bit until the word becomes zero, flip
	 * result in each iteration
	 * 
	 * @param word long input
	 * @return parity bit
	 */
	public static int parityImproved(long word) {
		int parity = 0;

		while (word != 0) {
			parity = parity ^ 1;
			word = word & (word - 1);
		}

		return parity;
	}

	// cache for 16 bit => 65536 states / 8 bit per byte => 8192 bytes to store the
	// bits
	private static byte[] parityCache = null;

	private static void setBit(int index, int value) {
		int bitIndex = index % 8;
		int byteIndex = index >> 3;
		byte mask = (byte) ((value & 1) << bitIndex);
		parityCache[byteIndex] |= mask;
	}

	private static int getBit(int index) {
		int bitIndex = index % 8;
		int byteIndex = index >> 3;
		byte mask = (byte) (1 << bitIndex);
		return (parityCache[byteIndex] & mask) != 0 ? 1 : 0;
	}

	/**
	 * Creates a cache for parities for 16 bit integers, uses it to return the
	 * parity for long integers
	 * 
	 * @param word the long word
	 * @return the parity
	 */
	public static int cachedParity(long word) {
		if (parityCache == null) {
			parityCache = new byte[8192];
			for (int i = 0; i < 65536; i++) {
				if (parityImproved(i) != 0) {
					setBit(i, 1);
				}
			}

			for (int i = 0; i <= 0xFFFF; i++) {
				System.out.format("%s=%d\n", Integer.toBinaryString(i), getBit(i));
			}
		}
		return getBit((int) (word & 0xFFFF)) ^ //
				getBit((int) ((word >>> 16) & 0xFFFF)) ^ //
				getBit((int) ((word >>> 32) & 0xFFFF)) ^ //
				getBit((int) ((word >>> 48) & 0xFFFF));

	}

	/**
	 * Associative parity computation. XORing the upper half and lower half of the
	 * word results in the bits having the same parity as the full word. Repeating
	 * the process results in the LSB having the parity of the whole 64 bit word.
	 * 
	 * @param word the long word
	 * @return the parity
	 */
	public static int parityAssociative(long word) {
		word = word ^ word >>> 32;
		word = word ^ word >>> 16;
		word = word ^ word >>> 8;
		word = word ^ word >>> 4;
		word = word ^ word >>> 2;
		word = word ^ word >>> 1;
		return (int) (word & 1);
	}

	public static void main(String[] args) {

		for (long i = 0; i < 64; i++) {
			long parityWord = i == 0 ? 0 : (long) 1 << i;
			// System.out.format("i=%d, parity word = %s\n", i,
			// Long.toBinaryString(parityWord));
			while (parityImproved(parityWord) != 0) {
				if (parityWord == 1) {
					parityWord = (parityWord << (long) 1);
				}
				// System.out.format("i=%d, parity word = %s\n", i,
				// Long.toBinaryString(parityWord));
				parityWord |= (long) 1;
				// System.out.format("i=%d, parity word = %s\n", i,
				// Long.toBinaryString(parityWord));
			}
			long nonParityWord = (long) 1 << i;
			/*
			 * System.out.format("i=%d, parity word = %s, non parity word = %s\n", i,
			 * Long.toBinaryString(parityWord), Long.toBinaryString(nonParityWord));
			 */
			System.out.format("parityWord=%s, brute=%d, improved=%d, cached=%d, associative=%d\n",
					Long.toBinaryString(parityWord), parityBruteForce(parityWord), parityImproved(parityWord),
					cachedParity(parityWord), parityAssociative(parityWord));
			System.out.format("nonParityWord=%s, brute=%d, improved=%d, cached=%d, associative=%d\n",
					Long.toBinaryString(nonParityWord), parityBruteForce(nonParityWord), parityImproved(nonParityWord),
					cachedParity(nonParityWord), parityAssociative(nonParityWord));
		}

	}

}
