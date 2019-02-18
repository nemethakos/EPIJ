import java.util.Arrays;

public class HIndex {

	/**
	 * Returns the Hirsch/H-index from the counts of publications a researcher has
	 * been cited
	 * 
	 * @param publicationCounts the array of counts a given publication at index i
	 *                          has been cited
	 * @return the H-Index
	 */
	public static int getHIndex(int... publicationCounts) {
		var hindex = 0;

		if (publicationCounts.length == 0) {
			return 0;
		}

		// sort the index in ascending orders
		Arrays.sort(publicationCounts);

		for (var i = 0; i < publicationCounts.length; i++) {
			var index = publicationCounts.length - 1 - i;
			if (publicationCounts[index] < i) {
				return i;
			}
		}

		return hindex;
	}

}
