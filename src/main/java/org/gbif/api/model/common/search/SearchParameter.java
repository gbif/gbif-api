package org.gbif.api.model.common.search;

/**
 * Interface to be implemented by all search parameter enumerations.
 * The enumeration member name should avoid the following names which are already defined as
 * standard (faceted) search parameters:
 * <ul>
 * <li>limit</li>
 * <li>offset</li>
 * <li>q</li>
 * <li>hl</li>
 * <li>language</li>
 * <li>facet</li>
 * </ul>
 */
public interface SearchParameter {

  String name();

  Class<?> type();
}
