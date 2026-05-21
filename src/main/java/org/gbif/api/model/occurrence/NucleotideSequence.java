package org.gbif.api.model.occurrence;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

@Schema(description = "A nucleotide sequence.")
@Data
public class NucleotideSequence {

  private String nucleotideSequenceID;
  private String targetGene;
  private String sequence;
  private Integer sequenceLength;
  private Double gcContent;
  private Double nonIupacFraction;
  private Double nonACGTNFraction;
  @Getter(AccessLevel.NONE)
  private Double nFraction;
  @Getter(AccessLevel.NONE)
  private Integer nRunsCapped;
  private Boolean naturalLanguageDetected;
  private Boolean endsTrimmed;
  private Boolean gapsOrWhitespaceRemoved;
  private Boolean invalid;

  public Double getnFraction() {
    return nFraction;
  }

  public void setnFraction(Double nFraction) {
    this.nFraction = nFraction;
  }

  public Integer getnRunsCapped() {
    return nRunsCapped;
  }

  public void setnRunsCapped(Integer nRunsCapped) {
    this.nRunsCapped = nRunsCapped;
  }

}
