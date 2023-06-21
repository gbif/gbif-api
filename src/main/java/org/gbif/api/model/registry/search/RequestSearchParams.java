/*
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
package org.gbif.api.model.registry.search;

import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PageableBase;
import org.gbif.api.model.common.paging.PagingRequest;
import org.gbif.api.util.Range;
import org.gbif.api.vocabulary.IdentifierType;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

/** Base class for registry requests to list the entities. */
@Getter
@Setter
public class RequestSearchParams extends PageableBase {

  public static final String IDENTIFIER_TYPE_PARAM = "identifierType";
  public static final String IDENTIFIER_PARAM = "identifier";
  public static final String MACHINE_TAG_NAMESPACE_PARAM = "machineTagNamespace";
  public static final String MACHINE_TAG_NAME_PARAM = "machineTagName";
  public static final String MACHINE_TAG_VALUE_PARAM = "machineTagValue";
  public static final String Q_PARAM = "q";
  public static final String MODIFIED_PARAM = "modified";

  private IdentifierType identifierType;
  private String identifier;
  private String machineTagNamespace; // namespace
  private String machineTagName; // name
  private String machineTagValue; // value
  private String q; // query
  private Range<LocalDate> modified;

  public Pageable getPage() {
    return new PagingRequest(this.getOffset(), this.getLimit());
  }

  public void setPage(Pageable page) {
    if (page != null) {
      this.offset = page.getOffset();
      this.limit = page.getLimit();
    }
  }
}
