/*
 * Copyright 2020 Global Biodiversity Information Facility (GBIF)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
