package org.gbif.api.model.occurrence.geo;

/**
 * The DistanceUnit enumerates several units for measuring distances. These units
 * provide methods for converting strings and methods to convert units among each
 * others.The default unit used within this project is <code>METERS</code>
 * which is defined by <code>DEFAULT</code>
 */
public enum DistanceUnit {
  INCH(0.0254, "in", "inch"),
  YARD(0.9144, "yd", "yards"),
  FEET(0.3048, "ft", "feet"),
  KILOMETERS(1000.0, "km", "kilometers"),
  NAUTICALMILES(1852.0, "NM", "nmi", "nauticalmiles"),
  MILLIMETERS(0.001, "mm", "millimeters"),
  CENTIMETERS(0.01, "cm", "centimeters"),

  // 'm' is a suffix of 'nmi' so it must follow 'nmi'
  MILES(1609.344, "mi", "miles"),

  // since 'm' is suffix of other unit
  // it must be the last entry of unit
  // names ending with 'm'. otherwise
  // parsing would fail
  METERS(1, "m", "meters");

  public static final DistanceUnit DEFAULT = METERS;

  private double meters;
  private final String[] names;

  DistanceUnit(double meters, String...names) {
    this.meters = meters;
    this.names = names;
  }

  public double getMeters() {
    return meters;
  }

  public String[] getNames() {
    return names;
  }

  /**
   * Convert a value to a distance string
   *
   * @param distance value to convert
   * @return String representation of the distance
   */
  public String toString(double distance) {
    return distance + toString();
  }

  @Override
  public String toString() {
    return names[0];
  }

  /**
   * Parse a {@link Distance} from a given String
   *
   * @param distance String defining a {@link Distance}
   * @return parsed {@link Distance}
   */
  public static Distance parseDistance(String distance) {
    for (DistanceUnit unit: DistanceUnit.values()) {
      for (String name : unit.getNames()) {
        if(distance.endsWith(name)) {
          return new Distance(Double.parseDouble(distance.substring(0, distance.length() - name.length())), unit);
        }
      }
    }
    return new Distance(Double.parseDouble(distance), DEFAULT);
  }

  /**
   * Converts the given distance from the given DistanceUnit, to the given DistanceUnit
   *
   * @param distance Distance to convert
   * @param from     Unit to convert the distance from
   * @param to       Unit of distance to convert to
   * @return Given distance converted to the distance in the given unit
   */
  public static double convert(double distance, DistanceUnit from, DistanceUnit to) {
    if (from == to) {
      return distance;
    } else {
      return distance * from.meters / to.meters;
    }
  }

  public static class Distance implements Comparable<Distance> {
    public final double value;
    public final DistanceUnit unit;

    public Distance(double value, DistanceUnit unit) {
      this.value = value;
      this.unit = unit;
    }

    public double getValue() {
      return value;
    }

    public DistanceUnit getUnit() {
      return unit;
    }

    @Override
    public boolean equals(Object obj) {
      if(obj == null) {
        return false;
      } else if (obj instanceof Distance) {
        Distance other = (Distance) obj;
        return DistanceUnit.convert(value, unit, other.unit) == other.value;
      } else {
        return false;
      }
    }

    @Override
    public int hashCode() {
      return Double.valueOf(value * unit.meters).hashCode();
    }

    @Override
    public int compareTo(Distance o) {
      return Double.compare(value, DistanceUnit.convert(o.value, o.unit, unit));
    }

    @Override
    public String toString() {
      return unit.toString(value);
    }
  }
}
