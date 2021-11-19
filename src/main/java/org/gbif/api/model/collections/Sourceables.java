package org.gbif.api.model.collections;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Container annotation for multiple {@link Sourceable} annotations.
 *
 * <p>This is needed for cases where we have multiple master sources and each of them has different
 * sourceable parts. For example, for GBIF_REGISTRY the DOI identifiers are sourceable and for IH
 * only the IH_IRN are sourceable.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Sourceables {

  Sourceable[] value();
}
