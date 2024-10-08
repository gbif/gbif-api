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
 * Academic or research discipline to which an institution is dedicated.
 *
 * Note: The descriptions are the original values migrated from GRBIO.
 *
 * Deprecated to use the vocabulary server instead.
 */
@Deprecated
public enum Discipline {

  ARCHAEOLOGY("Archaeology: General"),
  ARCHAEOLOGY_PREHISTORIC("Archaeology: Prehistoric"),
  ARCHAEOLOGY_HISTORIC("Archaeology: Historic"),
  ARCHAEOLOGY_UNDERWATER("Archaeology: Underwater"),
  ANTHROPOLOGY("Anthropology: General"),
  ANTHROPOLOGY_BIOLOGICAL("Anthropology: Biological"),
  ANTHROPOLOGY_CULTURAL("Anthropology: Cultural"),
  ANTHROPOLOGY_LINGUISTIC("Anthropology: Linguistic"),
  SPACE("Space Sciences: General"),
  SPACE_ASTRONOMY("Space Sciences: Astronomy"),
  SPACE_ASTROPHYSICS("Space Sciences: Astrophysics"),
  SPACE_COSMOLOGY("Space Sciences: Cosmology"),
  SPACE_PLANETARY_SCIENCE("Space Sciences: Planetary Science"),
  ATMOSPHERIC("Atmospheric Sciences: General"),
  ATMOSPHERIC_PHYSICS_DYNAMICS("Atmospheric Sciences: Atmospheric Physics & Dynamics"),
  ATMOSPHERIC_CLIMATOLOGY("Atmospheric Sciences: Climatology"),
  ATMOSPHERIC_METEOROLOGY("Atmospheric Sciences: Meteorology"),
  CHEMICAL("Chemical Sciences: General"),
  CHEMICAL_ANALYTICAL("Chemical Sciences: Analytical Chemistry"),
  CHEMICAL_ASTROCHEMISTRY("Chemical Sciences: Astrochemsitry"),
  CHEMICAL_ATMOSPHERIC_CHEMISTRY("Chemical Sciences: Atmospheric Chemistry"),
  CHEMICAL_BIOCHEMISTRY("Chemical Sciences: Biochemistry"),
  CHEMICAL_BIOGEOCHEMISTRY("Chemical Sciences: Biogeochemistry"),
  CHEMICAL_COSMOCHEMISTRY("Chemical Sciences: Cosmochemistry"),
  CHEMICAL_INORGANIC_CHEMISTRY("Chemical Sciences: Inorganic Chemistry"),
  CHEMICAL_NUCLEAR_CHEMISTRY("Chemical Sciences: Nuclear Chemistry"),
  CHEMICAL_ORGANIC_CHEMISTRY("Chemical Sciences: Organic Chemistry"),
  CHEMICAL_PHYSICAL_CHEMISTRY("Chemical Sciences: Physical Chemistry"),
  GEOLOGICAL("Geological & Earth Sciences: General"),
  GEOLOGICAL_ECONOMIC_GEOLOGY_MINERAL_RESOURCES("Geological & Earth Sciences: Economic geology/mineral resources"),
  GEOLOGICAL_ENERGY_RESOURCE_GEOLOGY("Geological & Earth Sciences: Energy resource geology"),
  GEOLOGICAL_GEOCHEMISTRY("Geological & Earth Sciences: Geochemistry"),
  GEOLOGICAL_GEOLOGY("Geological & Earth Sciences: Geology"),
  GEOLOGICAL_GEOPHYSICS_SEISMOLOGY("Geological & Earth Sciences: Geophysics & Seismology"),
  GEOLOGICAL_HYDROLOGY_WATER_RESOURCES("Geological & Earth Sciences: Hydrology & Water Resources"),
  GEOLOGICAL_MINERALOGY_PETROLOGY("Geological & Earth Sciences: Mineralogy & Petrology"),
  GEOLOGICAL_PALEONTOLOGY("Geological & Earth Sciences: Paleontology"),
  GEOLOGICAL_VOLCANOLOGY("Geological & Earth Sciences: Volcanology"),
  OCEAN("Ocean & Marine Sciences: General"),
  OCEAN_MARINE_BIOLOGY_AND_BIOLOGICAL_OCEANOGRAPHY("Ocean & Marine Sciences: Marine Biology & Biological Oceanography"),
  OCEAN_MARINE_GEOLOGY_AND_PALEOCEANOGRAPHY("Ocean & Marine Sciences: Marine Geology & Paleoceanography"),
  OCEAN_OCEANOGRAPHY_CHEMICAL_PHYSICAL("Ocean & Marine Sciences: Oceanography, Chemical & Physical"),
  PHYSICS("Physics: General"),
  PHYSICS_ACOUSTICS("Physics: Acoustics"),
  PHYSICS_APPLIED_PHYSICS("Physics: Applied Physics"),
  PHYSICS_ATOMIC_MOLECULAR_CHEMICAL_PHYSICS("Physics: Atomic/Molecular/Chemical Physics"),
  PHYSICS_BIOPHYSICS("Physics: Biophysics"),
  PHYSICS_MEDICAL_RADIOLOGICAL("Physics: Medical Physics/Radiological Science"),
  PHYSICS_NUCLEAR_PHYSICS("Physics: Nuclear Physics"),
  PHYSICS_OPTICS_PHOTONICS("Physics: Optics/Photonics"),
  PHYSICS_PARTICLE_PHYSICS("Physics: Particle (Elementary) Physics"),
  AGRICULTURAL("Agricultural Sciences & Natural Resources: General"),
  AGRICULTURAL_ANIMAL_SCIENCE("Agricultural Sciences & Natural Resources: Animal Science, General"),
  AGRICULTURAL_ANIMAL_SCIENCE_POULTRY("Agricultural Sciences & Natural Resources: Animal Science, Poultry (or Avian)"),
  AGRICULTURAL_AGRICULTURAL_ANIMAL_BREEDING("Agricultural Sciences & Natural Resources: Agricultural Animal Breeding"),
  AGRICULTURAL_AGRICULTURAL_HORTICULTURAL_PLANT_BREEDING("Agricultural Sciences & Natural Resources: Agricultural & Horticultural Plant Breeding"),
  AGRICULTURAL_AGRONOMY_CROP_SCIENCE("Agricultural Sciences & Natural Resources: Agronomy & Crop Science"),
  AGRICULTURAL_ENVIRONMENTAL_SCIENCE("Agricultural Sciences & Natural Resources: Environmental Science"),
  AGRICULTURAL_FISHING_FISHERIES_SCIENCE("Agricultural Sciences & Natural Resources: Fishing & Fisheries Sciences"),
  AGRICULTURAL_FOOD_SCIENCE_AND_TECHNOLOGY("Agricultural Sciences & Natural Resources: Food Science & Technology"),
  AGRICULTURAL_FOREST_SCIENCES_AND_FORESTRY("Agricultural Sciences & Natural Resources: Forest Sciences & Forestry"),
  AGRICULTURAL_HORTICULTURAL_SCIENCE("Agricultural Sciences & Natural Resources: Horticultural Science"),
  AGRICULTURAL_NATURAL_RESOURCES("Agricultural Sciences & Natural Resources: Natural Resources"),
  AGRICULTURAL_PLANT_SCIENCES("Agricultural Sciences & Natural Resources: Plant Sciences, Other"),
  AGRICULTURAL_SOIL_CHEMISTRY_MICROBIOLOGY("Agricultural Sciences & Natural Resources: Soil Chemistry/Microbiology"),
  AGRICULTURAL_SOIL_SCIENCES("Agricultural Sciences & Natural Resources: Soil Sciences, Other"),
  AGRICULTURAL_WILDLIFE_RANGE_MANAGEMENT("Agricultural Sciences & Natural Resources: Wildlife/Range Management"),
  AGRICULTURAL_WOOD_SCIENCE_AND_PULP_TECHNOLOGY("Agricultural Sciences & Natural Resources: Wood Science & Pulp/Paper Technology"),
  BIOLOGICAL("Biological Sciences: General"),
  BIOLOGICAL_ANATOMY_AND_PHYSIOLOGY("Biological Sciences: Anatomy and Physiology"),
  BIOLOGICAL_CELLULAR_BIOLOGY_AND_HISTOLOGY("Biological Sciences: Cell/Cellular Biology & Histology"),
  BIOLOGICAL_DEVELOPMENT_BIOLOGY_EMBRYOLOGY("Biological Sciences: Developmental Biology/Embryology"),
  BIOLOGICAL_ECOLOGY("Biological Sciences: Ecology"),
  BIOLOGICAL_ENVIRONMENTAL_TOXICOLOGY("Biological Sciences: Environmental Toxicology"),
  BIOLOGICAL_EVOLUTIONARY_BIOLOGY("Biological Sciences: Evolutionary Biology"),
  BIOLOGICAL_GENETICS_GENOMICS("Biological Sciences: Genetics/Genomics"),
  BIOLOGICAL_MICROBIOLOGY_BACTERIOLOGY_VIROLOGY("Biological Sciences: Microbiology, Bacteriology & Virology"),
  BIOLOGICAL_MOLECULAR_BIOLOGY("Biological Sciences: Molecular Biology"),
  BIOLOGICAL_NEUROSCIENCES_AND_NEUROBIOLOGY("Biological Sciences: Neurosciences & Neurobiology"),
  BIOLOGICAL_PARASITOLOGY("Biological Sciences: Parasitology"),
  BIOLOGICAL_PATHOLOGY_ANIMAL_PLANT("Biological Sciences: Pathology, Animal and Plant"),
  BIOLOGICAL_TAXONOMY("Biological Sciences: Taxonomy"),
  BIOLOGICAL_ZOOLOGY("Biological Sciences: Zoology"),
  HEALTH("Health Biomedical Sciences: General"),
  HEALTH_BIOMEDICAL_SCIENCE("Health Biomedical Sciences: Biomedical Science"),
  HEALTH_ENVIRONMENTAL_HEALTH("Health Biomedical Sciences: Environmental Health"),
  HEALTH_EPIDEMIOLOGY_PUBLIC_HEALTH("Health Biomedical Sciences: Epidemiology/Public Health"),
  HEALTH_GENETICS_GENOMICS("Health Biomedical Sciences: Genetics/Genomics"),
  HEALTH_MICROBIOLOGY_BACTERIOLOGY_VIROLOGY("Health Biomedical Sciences: Microbiology, Bacteriology & Virology"),
  HEALTH_NEUROSCIENCES_AND_NEUROBIOLOGY("Health Biomedical Sciences: Neurosciences & Neurobiology"),
  HEALTH_NUTRITION_SCIENCES("Health Biomedical Sciences: Nutrition Sciences"),
  HEALTH_PATHOLOGY_HUMAN("Health Biomedical Sciences: Pathology, Human"),
  HEALTH_PHARMACEUTICAL_MEDICINAL_SCIENCES("Health Biomedical Sciences: Pharmaceutical/Medicinal Sciences"),
  HEALTH_PHARMACOLOGY_HUMAN_AND_ANIMAL("Health Biomedical Sciences: Pharmacology, Human & Animal"),
  HEALTH_TOXICOLOGY("Health Biomedical Sciences: Toxicology"),
  HEALTH_VETERINARY_SCIENCES("Health Biomedical Sciences: Veterinary Sciences"),
  MATERIAL("Material Sciences: General");

  private String description;

  Discipline(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

}
