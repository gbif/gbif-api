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
package org.gbif.api.v2;

import org.gbif.api.model.checklistbank.NameUsageMatch;
import org.gbif.api.vocabulary.TaxonomicStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * To be removed in favor of the new NameUsageMatchResponse class
 * in kvs library.
 * NameUsageMatch for API v2
 * See https://github.com/gbif/checklistbank/issues/49
 */
public class NameUsageMatch2 {
  private boolean synonym;
  private RankedName usage;
  private RankedName acceptedUsage;
  private Nomenclature nomenclature;
  private List<RankedName> classification = new ArrayList<>();
  private Diagnostics diagnostics = new Diagnostics();

  public static class Nomenclature {
    private String source;
    private String id;

    public String getSource() {
      return source;
    }

    public void setSource(String source) {
      this.source = source;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Nomenclature that = (Nomenclature) o;
      return Objects.equals(source, that.source) &&
          Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
      return Objects.hash(source, id);
    }
  }

  public static class Diagnostics {
    private NameUsageMatch.MatchType matchType;
    private Integer confidence;
    private TaxonomicStatus status;
    private List<String> lineage = new ArrayList<>();
    private List<NameUsageMatch2> alternatives = new ArrayList<>();
    private String note;

    public NameUsageMatch.MatchType getMatchType() {
      return matchType;
    }

    public void setMatchType(NameUsageMatch.MatchType matchType) {
      this.matchType = matchType;
    }

    public Integer getConfidence() {
      return confidence;
    }

    public void setConfidence(Integer confidence) {
      this.confidence = confidence;
    }

    public TaxonomicStatus getStatus() {
      return status;
    }

    public void setStatus(TaxonomicStatus status) {
      this.status = status;
    }

    public List<String> getLineage() {
      return lineage;
    }

    public void setLineage(List<String> lineage) {
      this.lineage = lineage;
    }

    public List<NameUsageMatch2> getAlternatives() {
      return alternatives;
    }

    public void setAlternatives(List<NameUsageMatch2> alternatives) {
      this.alternatives = alternatives;
    }

    public String getNote() {
      return note;
    }

    public void setNote(String note) {
      this.note = note;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Diagnostics that = (Diagnostics) o;
      return status == that.status &&
          matchType == that.matchType &&
          Objects.equals(confidence, that.confidence) &&
          Objects.equals(lineage, that.lineage) &&
          Objects.equals(alternatives, that.alternatives) &&
          Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
      return Objects.hash(status, matchType, confidence, lineage, alternatives, note);
    }
  }

  public boolean isSynonym() {
    return synonym;
  }

  public void setSynonym(boolean synonym) {
    this.synonym = synonym;
  }

  public RankedName getUsage() {
    return usage;
  }

  public void setUsage(RankedName usage) {
    this.usage = usage;
  }

  public RankedName getAcceptedUsage() {
    return acceptedUsage;
  }

  public void setAcceptedUsage(RankedName acceptedUsage) {
    this.acceptedUsage = acceptedUsage;
  }

  public Nomenclature getNomenclature() {
    return nomenclature;
  }

  public void setNomenclature(Nomenclature nomenclature) {
    this.nomenclature = nomenclature;
  }

  /**
   * the classification includes the accepted taxon concept view
   */
  public List<RankedName> getClassification() {
    return classification;
  }

  public void setClassification(List<RankedName> classification) {
    this.classification = classification;
  }

  public Diagnostics getDiagnostics() {
    return diagnostics;
  }

  public void setDiagnostics(Diagnostics diagnostics) {
    this.diagnostics = diagnostics;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    NameUsageMatch2 that = (NameUsageMatch2) o;
    return synonym == that.synonym &&
        Objects.equals(usage, that.usage) &&
        Objects.equals(acceptedUsage, that.acceptedUsage) &&
        Objects.equals(nomenclature, that.nomenclature) &&
        Objects.equals(classification, that.classification) &&
        Objects.equals(diagnostics, that.diagnostics);
  }

  @Override
  public int hashCode() {
    return Objects.hash(synonym, usage, acceptedUsage, nomenclature, classification, diagnostics);
  }
}
