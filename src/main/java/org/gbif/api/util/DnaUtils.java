package org.gbif.api.util;

import java.util.regex.Pattern;
import org.apache.commons.codec.digest.DigestUtils;

public class DnaUtils {

  private static final Pattern NON_IUPAC = Pattern.compile("[^ACGTURYSWKMBDHVN]");

  public static String convertDnaSequenceToID(String dnaSequence) {
    if (dnaSequence != null && !dnaSequence.isEmpty()) {
      return DigestUtils.md5Hex(NON_IUPAC.matcher(dnaSequence.toUpperCase()).replaceAll(""));
    }
    return dnaSequence;
  }
}
