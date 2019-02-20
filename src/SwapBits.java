
public class SwapBits {

	/**
	 * Swap the bits in position i and j
	 * 
	 * @param word the long word
	 * @param i    the first bit position
	 * @param j    the second bit position
	 * @return the swapped word
	 */
	public static long swapBits(long word, int i, int j) {

		// parameter check
		if (i < 0 || i > 63 || j < 0 || j > 63) {
			throw new IllegalArgumentException();
		}

		// if i == j return the original
		if (i == j) {
			return word;
		}

		// mask will contain only the bits to swap set to 1
		long mask = 1L << i | 1L << j;
		long test = word & mask;
		// if both bits are 0 or 1 just return the original
		if (test == 0 || test == mask) {
			return word;
		} else {
			// if one bit is 1 and the other is 0, XOR with the mask, this will change the 1
			// to 0 and the 0 to 1
			// effectively swapping the bit values
			return word ^ mask;
		}
	}

	private static String str(long word) {
		return String.format("%64s", Long.toBinaryString(word)).replace(' ', '0');
	}

	public static void test(long word, int i, int j) {
		var swapped = swapBits(word, i, j);
		System.out.format("original: %s, i:%d, j:%d\nswapped:  %s\n\n", str(word), i, j, str(swapped));
	}

	public static void main(String... args) {
		test(0b10, 0, 1);
		test(0b01, 0, 1);
		test(0b00, 0, 1);
		test(0b11, 0, 1);

		test(0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00000001L, 0, 63);
		test(0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L, 0, 63);
		test(0b10000000_00000000_00000000_00000000_00000000_00000000_00000000_00000001L, 0, 63);
		test(0b10000000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L, 0, 63);
	}

}
