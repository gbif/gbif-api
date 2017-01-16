package org.gbif.api.vocabulary;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.google.common.reflect.ClassPath;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Checks InterpretationRemark implementation under "org.gbif.api.vocabulary".
 */
public class InterpretationRemarkTest {

  private static final String ROOT_PACKAGE = "org.gbif.api.vocabulary";

  /**
   * Checks all classes (enumerations) that implement {@link InterpretationRemark} to ensure they have unique entry names.
   * {@link InterpretationRemark} implementations can be used as keys and can also be serialized as String. For this
   * reason we want to ensure we have unique entries among implementations. This is mainly to avoid confusion when we
   * do aggregation.
   */
  @Test
  public void testInterpretationRemarkImplementations() {
    try {
      Set<ClassPath.ClassInfo> classes =
              ClassPath.from(InterpretationRemarkTest.class.getClassLoader()).getTopLevelClasses(ROOT_PACKAGE);

      Set<String> interpretationRemarks = new HashSet<String>();
      for (ClassPath.ClassInfo c : classes) {
        Class<?> loadedClass = c.load();
        if (Arrays.asList(loadedClass.getInterfaces()).contains(InterpretationRemark.class)) {
          for (InterpretationRemark enumEntry : Arrays.asList((InterpretationRemark[]) loadedClass.getEnumConstants())) {
            assertTrue("Enumeration value " + enumEntry + " is unique within all InterpretationRemark implementations.",
                    interpretationRemarks.add(enumEntry.toString()));
          }
        }
      }
    } catch (IOException e) {
      fail(e.getMessage());
    }
  }
}
