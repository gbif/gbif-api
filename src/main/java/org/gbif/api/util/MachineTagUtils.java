package org.gbif.api.util;

import org.gbif.api.model.registry.MachineTag;
import org.gbif.api.model.registry.MachineTaggable;
import org.gbif.api.vocabulary.TagName;

import java.util.List;
import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Utility class to simplify your life when dealing with machine tags.
 */
public class MachineTagUtils {

  private MachineTagUtils() {
  }

  /**
   * Returns a copy of the original list of machine taggable entities, that have the machine tag.
   */
  public static <T extends MachineTaggable> List<T> filter(List<T> source, @Nullable final String namespace, @Nullable final String name, @Nullable final String value) {
    return Lists.newArrayList(Iterables.filter(source, new Predicate<T>() {

      @Override
      public boolean apply(@Nullable T e) {
        for (MachineTag t : e.getMachineTags()) {
          if ((namespace == null || t.getNamespace().equals(namespace)) &&
              (name == null || t.getName().equals(name)) &&
              (value == null || t.getValue().equals(value)))
          {
            return true;
          }
        }
        return false;
      }
    }));
  }


  /**
   * @return the int value for the given machine tag or zero if its no valid integer or null
   */
  public static Integer tagValueAsInteger(MachineTag tag) {
    if (tag != null) {
      try {
        return Integer.parseInt(tag.getValue());
      } catch (NumberFormatException e) {
        // quietly swallow
      }
    }
    return null;
  }

  /**
   * @return the double value for the given machine tag or zero if its no valid double or null
   */
  public static Double tagValueAsDouble(MachineTag tag) {
    if (tag != null) {
      try {
        return Double.parseDouble(tag.getValue());
      } catch (NumberFormatException e) {
        // quietly swallow
      }
    }
    return null;
  }

  /**
   * @return the first machine tag that with the given namespace and name.
   */
  public static MachineTag firstTag(MachineTaggable taggable, TagName tagName) {
    return firstTag(taggable, tagName.getNamespace().getNamespace(), tagName.getName());
  }

  /**
   * @return the first machine tag that with the given namespace and name.
   */
  public static MachineTag firstTag(MachineTaggable taggable, String namespace, String tagName) {
    for (MachineTag mt : taggable.getMachineTags()) {
      if (mt.getNamespace().equals(namespace) && mt.getName().equals(tagName)) {
        return mt;
      }
    }
    return null;
  }

  /**
   * @return a new list of machine tags which have the given tagName.
   */
  public static List<MachineTag> list(MachineTaggable taggable, TagName tagName) {
    return list(taggable, tagName.getNamespace().getNamespace(), tagName.getName());
  }

  /**
   * @return a new list of machine tags which have the given namespace and name.
   */
  public static List<MachineTag> list(MachineTaggable taggable, String namespace, String tagName) {
    List<MachineTag> tags = Lists.newArrayList();
    for (MachineTag mt : taggable.getMachineTags()) {
      if (mt.getNamespace().equals(namespace) && mt.getName().equals(tagName)) {
        tags.add(mt);
      }
    }
    return tags;
  }

  /**
   * @return a new list of machine tags which have the given namespace and a name starting with a common prefix.
   */
  public static List<MachineTag> listByPrefix(MachineTaggable taggable, String namespace, String prefix) {
    List<MachineTag> tags = Lists.newArrayList();
    for (MachineTag mt : taggable.getMachineTags()) {
      if (mt.getNamespace().equals(namespace) && mt.getName().startsWith(prefix)) {
        tags.add(mt);
      }
    }
    return tags;
  }
}
