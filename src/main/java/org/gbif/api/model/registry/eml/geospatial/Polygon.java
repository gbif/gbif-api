/*
 * Copyright 2014 Global Biodiversity Information Facility (GBIF)
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

import java.util.List;
import java.util.Locale;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

/**
 * A simple polygon made out of a list of points to be joined.
 */
public class Polygon implements Geometry {

  private static final Joiner POINT_JOINER = Joiner.on(", ");
  private List<Point> points = Lists.newArrayList();

  public List<Point> getPoints() {
    return points;
  }

  public void setPoints(List<Point> points) {
    this.points = points;
  }

  @Override
  public String toWellKnownText() {
    return String.format(Locale.ENGLISH, "POLYGON ((%s))", POINT_JOINER.join(points));
  }

  public void addPoint(Point point) {
    points.add(point);
  }

}
