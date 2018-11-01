package org.gbif.api.jackson;

import org.gbif.api.model.collections.Institution;
import org.gbif.api.model.collections.vocabulary.Discipline;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

public class ListEnumSerializersTest {

  @Test
  public void test() throws IOException {
    Institution institution = new Institution();
    List<Discipline> disciplines = new ArrayList<>();
    disciplines.add(Discipline.AGRICULTURAL_ANIMAL_SCIENCE);
    disciplines.add(Discipline.BIOLOGICAL);
    institution.setDisciplines(disciplines);

    String value = new ObjectMapper().writeValueAsString(institution);

    Institution obj = new ObjectMapper().readValue(value, Institution.class);
  }

}
