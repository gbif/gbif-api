package org.gbif.api.vocabulary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DurationUnitTest {

  @Test
  public void parseDurationTest() {
    assertDuration(DurationUnit.Duration.parse("1h").get(), 1, DurationUnit.HOURS);
    assertDuration(DurationUnit.Duration.parse("1hour").get(), 1, DurationUnit.HOURS);
    assertDuration(DurationUnit.Duration.parse("1   hours").get(), 1, DurationUnit.HOURS);
    assertDuration(DurationUnit.Duration.parse("1.5 hour").get(), 1.5, DurationUnit.HOURS);
    assertDuration(DurationUnit.Duration.parse(".5 hour").get(), 0.5, DurationUnit.HOURS);
    assertDuration(DurationUnit.Duration.parse("5").get(), 5, DurationUnit.MINUTES);

    Assertions.assertEquals(60, DurationUnit.Duration.parse("1h").get().toMinutes());
  }

  private void assertDuration(DurationUnit.Duration duration, double value, DurationUnit unit) {
    Assertions.assertEquals(value, duration.getValue());
    Assertions.assertEquals(unit, duration.getUnit());
  }
}
