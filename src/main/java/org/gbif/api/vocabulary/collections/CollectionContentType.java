/*
 * Copyright 2020 Global Biodiversity Information Facility (GBIF)
 *
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
package org.gbif.api.vocabulary.collections;

/**
 * Types of a (sub) group of elements contained in collection..
 *
 * Note: The descriptions are the original values migrated from GRBIO.
 */
public enum CollectionContentType {

  ARCHAEOLOGICAL_OTHER("Archaeological: Other or Undefined"),
  ARCHAEOLOGICAL_HUMAN_REMAINS("Archaeological: Human Remains"),
  ARCHAEOLOGICAL_FAUNAL_REMAINS("Archaeological: Faunal Remains"),
  ARCHAEOLOGICAL_FLORAL_REMAINS("Archaeological: Floral Remains"),
  ARCHAEOLOGICAL_C14("Archaeological: C-14 Samples"),
  ARCHAEOLOGICAL_COPROLITES("Archaeological: Coprolites"),
  ARCHAEOLOGICAL_CERAMIC_ARTIFACTS("Archaeological: Ceramic Artifacts"),
  ARCHAEOLOGICAL_FAUNAL_ARTIFACTS("Archaeological: Faunal Artifacts"),
  ARCHAEOLOGICAL_FLORAL_ARTIFACTS("Archaeological: Floral Artifacts"),
  ARCHAEOLOGICAL_LITHIC_ARTIFACTS("Archaeological: Lithic Artifacts"),
  ARCHAEOLOGICAL_METAL_ARTIFACTS("Archaeological: Metal Artifacts"),
  ARCHAEOLOGICAL_TEXTILES_BASKETRY("Archaeological: Textiles and Basketry"),
  ARCHAEOLOGICAL_TECHONOLOGICAL_PROCESSES_REMAINS("Archaeological: Remains of technological processes"),
  ARCHAEOLOGICA_WOODEN_ARTIFACTS("Archaeological: Wooden Artifacts"),
  BIOLOGICAL_OTHER("Biological: Other or Undefined"),
  BIOLOGICAL_LIVING_ORGANISMS("Biological: Living organisms"),
  BIOLOGICAL_LIVING_CELL_OR_TISSUE_CULTURES("Biological: Living cell or tissue cultures"),
  BIOLOGICAL_PRESERVED_ORGANISMS("Biological: Preserved organisms, their parts & products"),
  BIOLOGICAL_ANIMAL_BUILT_STRUCTURES("Biological: Animal-built structures (middens, beehives, etc.)"),
  BIOLOGICAL_ANIMAL_DERIVED("Biological: Animal-derived (skins, eggs, feathers, etc.)"),
  BIOLOGICAL_BIOFLUIDS("Biological: Biofluids (blood, urine, etc.) (non-human)"),
  BIOLOGICAL_CELLS_TISSUE("Biological: Cells, tissue (non-human)"),
  BIOLOGICAL_ENDOSKELETONS("Biological: Endoskeletons"),
  BIOLOGICAL_EXOSKELETONS("Biological: Exoskeletons"),
  BIOLOGICAL_FECES("Biological: Feces (non-human)"),
  BIOLOGICAL_MOLECULAR_DERIVATES("Biological: Molecular derivatives (DNA, RNA, proteins) (non-human)"),
  BIOLOGICAL_PLANT_DERIVED("Biological: Plant-derived (bark, pollen, phytoliths, etc.)"),
  HUMAN_DERIVED_OTHER("Human-Derived: Other or Undefined"),
  HUMAN_DERIVED_BIOFLUIDS_HUMAN("Human-Derived: Biofluids - Human"),
  HUMAN_DERIVED_BLOOD_HUMAN("Human-Derived: Blood - Human"),
  HUMAN_DERIVED_CELLS_HUMAN("Human-Derived: Cells - Human"),
  HUMAN_DERIVED_FECES_HUMAN("Human-Derived: Feces - Human"),
  HUMAN_DERIVED_MOLECULAR_DERIVATIVES("Human-Derived: Molecular Derivatives (DNA, RNA)"),
  HUMAN_DERIVED_TISSUE_HUMAN("Human-Derived: Tissue - Human"),
  EARTH_PLANETARY_OTHER("Earth & Planetary: Other or Undefined"),
  EARTH_PLANETARY_ASTEROIDS("Earth & Planetary: Asteroids"),
  EARTH_PLANETARY_COMETS("Earth & Planetary: Comets"),
  EARTH_PLANETARY_COSMIC_INTERPLANETARY_DUST("Earth & Planetary: Cosmic/Interplanetary dust"),
  EARTH_PLANETARY_GAS("Earth & Planetary: Gas"),
  EARTH_PLANETARY_GEMS("Earth & Planetary: Gems"),
  EARTH_PLANETARY_ICE("Earth & Planetary: Ice"),
  EARTH_PLANETARY_LUNAR_MATERIALS("Earth & Planetary: Lunar materials"),
  EARTH_PLANETARY_METALS_ORES("Earth & Planetary: Metals or Ores"),
  EARTH_PLANETARY_METEORITES("Earth & Planetary: Meteorites"),
  EARTH_PLANETARY_MINERALS("Earth & Planetary: Minerals"),
  EARTH_PLANETARY_ROCKS("Earth & Planetary: Rocks"),
  EARTH_PLANETARY_SEDIMENTS("Earth & Planetary: Sediments"),
  EARTH_PLANETARY_SOILS("Earth & Planetary: Soils"),
  EARTH_PLANETARY_SPACE_EXPOSED_MATERIALS("Earth & Planetary: Space-exposed materials"),
  EARTH_PLANETARY_WATER("Earth & Planetary: Water"),
  PALEONTOLOGICAL_OTHER("Paleontological: Other or Undefined"),
  PALEONTOLOGICAL_CONODONTS("Paleontological: Conodonts"),
  PALEONTOLOGICAL_INVERTEBRATE_FOSSILS("Paleontological: Invertebrate Body Fossils"),
  PALEONTOLOGICAL_INVERTEBRATE_MICROFOSSILS("Paleontological: Invertebrate Microfossils"),
  PALEONTOLOGICAL_PETRIFIED_WOOD("Paleontological: Petrified Wood"),
  PALEONTOLOGICAL_PLANT_FOSSILS("Paleontological: Plant Fossils"),
  PALEONTOLOGICAL_TRACE_FOSSILS("Paleontological: Trace Fossils"),
  PALEONTOLOGICAL_VERTEBRATE_FOSSILS("Paleontological: Vertebrate Body Fossils"),
  RECORDS_OTHER("Records: Other or Undefined"),
  RECORDS_SEISMOGRAMS("Records: Seismograms"),
  RECORDS_RADIOGRAPH("Records: Radiograph"),
  RECORDS_IMAGES("Records: Images (SEM, remote sensing, photos, etc.)"),
  RECORDS_DOCUMENTS("Records: Documents (historical)"),
  RECORDS_DOCUMENTATION("Records: Documentation (field notes, lab notes, etc.)"),
  RECORDS_DERIVED_DATA("Records: Derived Data"),
  RECORDS_ASSOCIATED_DATA("Records: Associated Data"),
  RECORDS_MAPS("Records: Maps"),
  RECORDS_RECORDINGS("Records: Recordings (audio, video, etc.)");

  private String description;

  CollectionContentType(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
