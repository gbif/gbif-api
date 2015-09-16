package org.gbif.api.util;

import java.text.Normalizer;

import org.apache.commons.lang3.StringUtils;

/**
 * Utilities dealing with unicode strings
 */
public class UnicodeUtils {

    /**
     * Replaces all diacretics with their ascii counterpart.
     */
    public static String ascii(String x) {
        if (x == null) {
            return null;
        }
        // manually normalize characters not dealt with by the java Normalizer
        x = StringUtils.replaceChars(x, "øØðÐ", "oOdD");

        // use java unicode normalizer to remove accents and punctuation
        x = Normalizer.normalize(x, Normalizer.Form.NFD);
        x = x.replaceAll("\\p{M}", "");
        return x;
    }

    /**
     * Replaces all digraphs and ligatures with their underlying 2 latin letters.
     *
     * @param x the string to decompose
     */
    public static String decompose(String x) {
        if (x == null) {
            return null;
        }
        return x.replaceAll("æ", "ae")
                .replaceAll("Æ", "Ae")
                .replaceAll("œ", "oe")
                .replaceAll("Œ", "Oe")
                .replaceAll("Ĳ", "Ij")
                .replaceAll("ĳ", "ij")
                .replaceAll("ǈ", "Lj")
                .replaceAll("ǉ", "lj")
                .replaceAll("ȸ", "db")
                .replaceAll("ȹ", "qp")
                .replaceAll("ß", "ss")
                .replaceAll("ﬆ", "st")
                .replaceAll("ﬅ", "ft")
                .replaceAll("ﬀ", "ff")
                .replaceAll("ﬁ", "fi")
                .replaceAll("ﬂ", "fl")
                .replaceAll("ﬃ", "ffi")
                .replaceAll("ﬄ", "ffl");
    }
}
