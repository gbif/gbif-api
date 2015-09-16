package org.gbif.api.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UnicodeUtilsTest {

    @Test
    public void testAscii() throws Exception {
        String x  = "À Á Â Ã Ä Å Ç È É Ê Ë Ì Í Î Ï Ð Ñ Ò Ó Ô Õ Ö Ø Ù Ú Û Ü Ý Ÿ à á â ã ä å ç è é ê ë ì í î ï ð ñ ò ó ô õ ö ø ù ú û ü ý ÿ";
        String x2 = "A A A A A A C E E E E I I I I D N O O O O O O U U U U Y Y a a a a a a c e e e e i i i i d n o o o o o o u u u u y y";
        assertEquals(x2, UnicodeUtils.ascii(x));
        assertNull(UnicodeUtils.ascii(null));
    }

    @Test
    public void testDecompose() throws Exception {
        String x  = "Æ œ Œ Ĳ ĳ ǈ ǉ \u0238 \u0239 ß ﬆ ﬅ ﬀ ﬁ ﬂ ﬃ ﬄ";
        String x2 = "Ae oe Oe Ij ij Lj lj db qp ss st ft ff fi fl ffi ffl";
        assertEquals(x2, UnicodeUtils.decompose(x));
        assertNull(UnicodeUtils.decompose(null));
        assertEquals("fjaelje", UnicodeUtils.decompose("fjæǉe"));
    }

}