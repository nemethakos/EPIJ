import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class HIndexTest {

	@Test
	void testGetHIndex() {
		assertEquals(4, HIndex.getHIndex(1,4,1,4,2,1,3,5,6));
		
		assertEquals(5, HIndex.getHIndex(1,2,3,4,5,6,7,8,9));
	}

}
