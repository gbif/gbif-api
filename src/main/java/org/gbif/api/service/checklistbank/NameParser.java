package org.gbif.api.service.checklistbank;

import org.gbif.api.exception.UnparsableException;
import org.gbif.api.model.checklistbank.ParsedName;
import org.gbif.api.vocabulary.Rank;

import javax.annotation.Nullable;

/**
 * Interface for parsing scientific names.
 */
public interface NameParser {

  /**
   * Fully parse the supplied name also trying to extract authorships, a conceptual sec reference, remarks or notes
   * on the nomenclatural status. In some cases the authorship parsing proves impossible and this nameparser will
   * return null.
   *
   * For strings which are no scientific names and scientific names that cannot be expressed by the ParsedName class
   * the parser will throw an UnparsableException with a given NameType and the original, unparsed name. This is the
   * case for all virus names and proper hybrid formulas, so make sure you catch and process this exception.
   *
   * @param scientificName the full scientific name to parse
   * @param rank the rank of the name if it is known externally. Helps identifying infrageneric names vs bracket authors
   *
   * @return the parsed name
   *
   * @throws UnparsableException
   */
  ParsedName parse(String scientificName, @Nullable Rank rank) throws UnparsableException;

  /**
   * Delegate method to parse a scientific name of unknown rank.
   * @return the parsed name
   */
  ParsedName parse(String scientificName) throws UnparsableException;

  /**
   * Fully parses a name using #parse(String, Rank) but converts names that throw a UnparsableException
   * into ParsedName objects with the scientific name, rank and name type given.
   * @return the parsed name
   */
  ParsedName parseQuietly(String scientificName, @Nullable Rank rank);

  /**
   * Delegate method to parse a scientific name of unknown rank quietly.
   * @return the parsed name
   */
  ParsedName parseQuietly(String scientificName);

  /**
   * Parses the scientific name without authorship and returns the ParsedName.canonicalName() string
   * @param scientificName the full scientific name to parse
   * @param rank the rank of the name if it is known externally. Helps identifying infrageneric names vs bracket authors
   *
   * @return the canonical name or null for unparsable names
   */
  String parseToCanonical(String scientificName, @Nullable Rank rank);

  /**
   * Delegate method to parse a scientific name of unknown rank and return its canonical form.
   */
  String parseToCanonical(String scientificName);

}
