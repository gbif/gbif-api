package org.gbif.api.model.occurrence;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

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
  private Double nFraction;
  private Integer nRunsCapped;
  private Boolean naturalLanguageDetected;
  private Boolean endsTrimmed;
  private Boolean gapsOrWhitespaceRemoved;
  private Boolean invalid;
}
