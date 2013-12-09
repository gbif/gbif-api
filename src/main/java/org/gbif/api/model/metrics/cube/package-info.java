/**
 * Provides the classes necessary to construct read only requests to well defined cubes.
 * Cubes are defined with typed dimensions, and a {@link ReadBuilder} is supplied to help ensure types are respected
 * when constructing cube lookups. A typical usage might be as follows:
 * 
 * <pre>
 * {@code
 *   new ReadBuilder()
 *     .at(OccurrenceCube.YEAR, 2012)
 *     .at(OccurrenceCube.KINGDOM, Kingdom.ANIMALIA)
 *     .build();
 * }
 * </pre>
 * 
 * @since 0.1
 */
package org.gbif.api.model.metrics.cube;