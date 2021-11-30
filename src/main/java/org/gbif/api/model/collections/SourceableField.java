package org.gbif.api.model.collections;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Holds the info about the GRSciColl fields whose master source is outside GRSciColl. */
public class SourceableField implements Serializable {

  private String fieldName;
  private List<Source> sources = new ArrayList<>();

  public String getFieldName() {
    return fieldName;
  }

  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }

  public List<Source> getSources() {
    return sources;
  }

  public void setSources(List<Source> sources) {
    this.sources = sources;
  }

  public static class Source {
    private MasterSourceType masterSource;
    private boolean overridable;
    private List<String> sourceableParts = new ArrayList<>();

    public MasterSourceType getMasterSource() {
      return masterSource;
    }

    public void setMasterSource(MasterSourceType masterSource) {
      this.masterSource = masterSource;
    }

    public boolean isOverridable() {
      return overridable;
    }

    public void setOverridable(boolean overridable) {
      this.overridable = overridable;
    }

    public List<String> getSourceableParts() {
      return sourceableParts;
    }

    public void setSourceableParts(List<String> sourceableParts) {
      this.sourceableParts = sourceableParts;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Source source = (Source) o;
      return overridable == source.overridable
          && masterSource == source.masterSource
          && Objects.equals(sourceableParts, source.sourceableParts);
    }

    @Override
    public int hashCode() {
      return Objects.hash(masterSource, overridable, sourceableParts);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SourceableField that = (SourceableField) o;
    return Objects.equals(fieldName, that.fieldName) && Objects.equals(sources, that.sources);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fieldName, sources);
  }
}
