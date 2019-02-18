
public class RightPropagateRightmost1Bit {

	/**
	 * Right propagate the rightmost set bit in word, e.g.: turns(01010000) to
	 * (01011111)
	 * 
	 * @param word the long word
	 * @return the propagated result
	 */
	public static long rpropagate(long word) {
		if (word == 0) {
			// early return
			return 0;
		}
		// extract rightmost bit
		long rmost = word & ~(word - 1);
		if (rmost == 1) {
			// if the rightmost bit is the MSbit, then the original value is already the
			// return value
			return word;
		}

		long mask = 0;
		// loop until LSbit is not filled
		while ((rmost & 1) == 0) {
			rmost = rmost >>> 1;
			mask |= rmost;
		}

		return word | mask;

	}

	public static void test(long word) {
		long result = rpropagate(word);
		System.out.format("original=%s, rightmost propagated=%s\n", Long.toBinaryString(word),
				Long.toBinaryString(result));
	}

	public static void main(String[] args) {
		test(0b0);
		test(0b1);
		test(0b10);
		test(0b1010101010000000);
		test(0b01010000);
	}

}
