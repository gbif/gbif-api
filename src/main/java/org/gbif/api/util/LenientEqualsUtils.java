package org.gbif.api.util;

import org.gbif.api.model.registry.LenientEquals;

public class LenientEqualsUtils {

  private LenientEqualsUtils() {}

  public static <T extends LenientEquals<T>> boolean lenientEquals(T a, T b) {
    return (a == b) || (a != null && a.lenientEquals(b));
  }
}
