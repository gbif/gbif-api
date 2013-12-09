package org.gbif.api.util;

import java.util.UUID;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

/**
 * A typed dataset key for both registered and external datasets.
 * External dataset keys are compound keys based on the originating network UUID key and a free String based
 * local identifier. The compound external datasetKey is structured as follows in its serialized form:
 * <pre>{NETWORK_KEY}:{DATASET_ID}</pre>
 *
 * This class is intended as a utility class, lacks mutability and therefore is not suitable serialization via Jackson!
 */
public class DatasetKey {

  private static final int UUID_LENGTH = 36;
  private static final int EXTERNAL_KEY_DATASET_ID_START_INDEX = UUID_LENGTH + 1;
  private static final char DELIMITER = ':';

  private final UUID registryKey;
  private final String datasetId;


  /**
   * Parses a datasetKey which can be either a compound external key or a simple UUID for registered datasets.
   *
   * @param key a simple registered dataset uuid key or an external compound dataset key
   *
   * @return a valid DatasetKey instance
   *
   * @throws IllegalArgumentException for invalid external or registered dataset keys
   */
  public static DatasetKey fromString(String key) {
    Preconditions.checkNotNull(key);
    if (key.length() == UUID_LENGTH) {
      // a registered dataset key
      return new DatasetKey(UUID.fromString(key));

    } else if (key.length() > EXTERNAL_KEY_DATASET_ID_START_INDEX && DELIMITER == key.charAt(UUID_LENGTH)) {
      // an external dataset key
      return new DatasetKey(UUID.fromString(key.substring(0, UUID_LENGTH)),
        key.substring(EXTERNAL_KEY_DATASET_ID_START_INDEX));
    }

    throw new IllegalArgumentException("Invalid dataset key " + key);
  }

  /**
   * Constructor for a registered dataset key.
   */
  public DatasetKey(UUID datasetKey) {
    this.registryKey = Preconditions.checkNotNull(datasetKey);
    this.datasetId = null;
  }

  /**
   * Constructor for an external dataset key.
   *
   * @param networkKey of the originating network
   * @param datasetId  the local dataset id within the network
   */
  public DatasetKey(UUID networkKey, String datasetId) {
    this.registryKey = Preconditions.checkNotNull(networkKey);
    this.datasetId = datasetId.trim();
    if (Strings.isNullOrEmpty(this.datasetId)) {
      throw new IllegalArgumentException("datasetId has to have non whitespace characters");
    }
  }

  /**
   * A registered UUID of either a dataset or the network of origin in the case of external datasets.
   *
   * @return the registered network or dataset key
   */
  public UUID getRegistryKey() {
    return registryKey;
  }

  /**
   * @return the local identifier of an external dataset key or null if its a registered dataset
   */
  public String getDatasetId() {
    return datasetId;
  }

  public boolean isExternalKey() {
    return datasetId != null;
  }

  /**
   * Generates the serialized dataset key as used in the {@link org.gbif.api.model.registry.Dataset} class.
   *
   * @return the serialized dataset key
   */
  public String toDatasetKey() {
    if (isExternalKey()) {
      return registryKey.toString() + DELIMITER + datasetId;
    }
    return registryKey.toString();
  }


}
