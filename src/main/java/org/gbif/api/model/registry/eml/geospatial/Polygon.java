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
package org.gbif.api.model.registry.eml.geospatial;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * A simple polygon made out of a list of points to be joined.
 */
public class Polygon implements Geometry {

  private List<Point> points = new ArrayList<>();

  public List<Point> getPoints() {
    return points;
  }

  public void setPoints(List<Point> points) {
    this.points = points;
  }

  @Override
  public String toWellKnownText() {
    return String.format(Locale.ENGLISH, "POLYGON ((%s))",
      points.stream()
        .map(p -> p.getLongitude() + " " + p.getLatitude())
        .collect(Collectors.joining(", "))
    );
  }

  public void addPoint(Point point) {
    points.add(point);
  }
}
