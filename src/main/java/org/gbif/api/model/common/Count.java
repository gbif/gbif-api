package org.gbif.api.model.common;

import java.util.Objects;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

/**
 *
 */
public class Count<T> implements Comparable<Count<T>> {
  private T key;
  private String title;
  private int count;

  public Count() {
  }

  public Count(T key, String title, int count) {
    this.count = count;
    this.key = key;
    this.title = title;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public T getKey() {
    return key;
  }

  public void setKey(T key) {
    this.key = key;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, title, count);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    final Count other = (Count) obj;
    return Objects.equals(this.key, other.key)
        && Objects.equals(this.title, other.title)
        && Objects.equals(this.count, other.count);
  }

  @Override
  public int compareTo(Count<T> that) {
    return ComparisonChain.start()
        .compare(this.count, that.count, Ordering.<Integer>natural().reverse())
        .compare(this.title, that.title)
        .result();
  }
}
