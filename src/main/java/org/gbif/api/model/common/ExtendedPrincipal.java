package org.gbif.api.model.common;

import java.security.Principal;

/**
 * Simply an extension of {@link Principal} to add the possibility to check roles.
 */
public interface ExtendedPrincipal extends Principal {
   boolean hasRole(String role);
}
