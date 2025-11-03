package org.gbif.api.vocabulary;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
public enum DurationUnit {
  SECONDS(0.0166667, "seconds", "second", "s", "sec", "secs"),
  MINUTES(1, "minutes", "minute", "m", "min", "mins"),
  HOURS(60, "hours", "hour", "h", "hr", "hrs"),
  DAYS(1440, "days", "day", "d"),
  WEEKS(10080, "weeks", "week", "wk", "wks"),
  MONTHS(43800, "months", "month", "mo", "mos"),
  YEARS(525600, "years", "year", "yr", "yrs", "y");

  public static final DurationUnit DEFAULT = MINUTES;

  private final double durationInMinutes;
  private final String[] names;

  DurationUnit(double durationInMinutes, String... names) {
    this.durationInMinutes = durationInMinutes;
    this.names = names;
  }

  public static Optional<DurationUnit> parseDurationUnit(String duration) {
    if (duration != null && !duration.isEmpty()) {
      String normalizedDuration = duration.trim().toLowerCase();
      for (DurationUnit unit : DurationUnit.values()) {
        for (String name : unit.getNames()) {
          if (normalizedDuration.equals(name)) {
            return Optional.of(unit);
          }
        }
      }
    }
    return Optional.empty();
  }

  @AllArgsConstructor
  @Getter
  @EqualsAndHashCode
  public static class Duration {

    private static final Pattern DURATION_PATTERN =
        Pattern.compile("(\\d*\\.?\\d+)\\s*([a-zA-Z]*)");

    private final double value;
    private final DurationUnit unit;

    public static Optional<Duration> parse(String duration) {
      if (duration != null && !duration.isEmpty()) {
        Matcher matcher = DURATION_PATTERN.matcher(duration);
        if (matcher.matches()) {
          String rawUnit = matcher.group(2);
          if (rawUnit != null && !rawUnit.isEmpty()) {
            DurationUnit unit = DurationUnit.parseDurationUnit(matcher.group(2)).orElse(null);

            if (unit == null) {
              return Optional.empty();
            }

            return Optional.of(new Duration(Double.parseDouble(matcher.group(1)), unit));
          } else {
            return Optional.of(new Duration(Double.parseDouble(duration), DEFAULT));
          }
        }
      }
      return Optional.empty();
    }

    public double toMinutes() {
      return value * unit.getDurationInMinutes();
    }
  }
}
