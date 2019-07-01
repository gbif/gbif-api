package org.gbif.api.util.comparators;

import java.io.Serializable;

import org.gbif.api.model.registry.Endpoint;

import com.google.common.collect.Ordering;

/**
 * An ordering that uses the created date of an endpoint for ordering.
 */
public class EndpointCreatedComparator extends Ordering<Endpoint> implements Serializable {
  public static final EndpointCreatedComparator INSTANCE = new EndpointCreatedComparator();

  @Override public int compare(Endpoint left, Endpoint right) {
    return left.toString().compareTo(right.toString());
  }

  private EndpointCreatedComparator() {}

  private static final long serialVersionUID = 0;
}
