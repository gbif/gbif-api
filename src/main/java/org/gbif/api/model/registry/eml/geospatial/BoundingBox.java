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

import java.io.Serializable;
import java.util.Locale;


/**
 * Geographic coverage geometry in the form of 4 sided bounding box.
 */
public class BoundingBox implements Serializable, Geometry {

  private static final long serialVersionUID = 9101833933868378174L;

  private static final int MIN_MIN_LONGITUDE = -180;
  private static final int MAX_MAX_LONGITUDE = 180;

  private static final int MIN_MIN_LATITUDE = -90;
  private static final int MAX_MAX_LATITUDE = 90;

  private double minLatitude;
  private double maxLatitude;
  private double minLongitude;
  private double maxLongitude;
  private boolean isGlobalCoverage;


  public BoundingBox() {
  }

  public BoundingBox(double minLatitude, double maxLatitude, double minLongitude, double maxLongitude) {
    this.minLatitude = minLatitude;
    this.maxLatitude = maxLatitude;
    this.minLongitude = minLongitude;
    this.maxLongitude = maxLongitude;
    this.isGlobalCoverage = checkIsGlobalCoverage(minLatitude, maxLatitude, minLongitude, maxLongitude);
  }

  public double getMaxLatitude() {
    return maxLatitude;
  }

  public void setMaxLatitude(double maxLatitude) {
    this.maxLatitude = maxLatitude;
  }

  public double getMaxLongitude() {
    return maxLongitude;
  }

  public void setMaxLongitude(double maxLongitude) {
    this.maxLongitude = maxLongitude;
  }

  public double getMinLatitude() {
    return minLatitude;
  }

  public void setMinLatitude(double minLatitude) {
    this.minLatitude = minLatitude;
  }

  public double getMinLongitude() {
    return minLongitude;
  }

  public void setMinLongitude(double minLongitude) {
    this.minLongitude = minLongitude;
  }

  @Override
  public String toWellKnownText() {
    return String.format(Locale.ENGLISH, "POLYGON ((%f %f, %f %f, %f %f, %f %f))",
                         minLongitude,
                         minLatitude,
                         minLongitude,
                         maxLatitude,
                         maxLongitude,
                         maxLatitude,
                         maxLongitude,
                         minLatitude);
  }

  /**
   * @return whether the BoundingBox represents global coverage
   */
  public boolean isGlobalCoverage() {
    return isGlobalCoverage;
  }

  /**
   * Set whether this binding box represents global coverage or not. Setter ensures all 4 required params are provided.
   *
   * @param minLatitude  min latitude
   * @param maxLatitude  max latitude
   * @param minLongitude min longitude
   * @param maxLongitude max longitude
   */
  public void setGlobalCoverage(double minLatitude, double maxLatitude, double minLongitude, double maxLongitude) {
    isGlobalCoverage = checkIsGlobalCoverage(minLatitude, maxLatitude, minLongitude, maxLongitude);
  }

  /**
   * Determine whether the bounding box represents global coverage. This is the case, when all North, East, South,
   * and West lines represent the maximum possible degree values (90, 180, -90, -180).
   *
   * @param minLatitude  min latitude
   * @param maxLatitude  max latitude
   * @param minLongitude min longitude
   * @param maxLongitude max longitude
   *
   * @return whether the bounding box represents global coverage
   */
  private boolean checkIsGlobalCoverage(
    double minLatitude, double maxLatitude, double minLongitude, double maxLongitude
  ) {
    return minLatitude == MIN_MIN_LATITUDE
           && maxLatitude == MAX_MAX_LATITUDE
           && minLongitude == MIN_MIN_LONGITUDE
           && maxLongitude == MAX_MAX_LONGITUDE;
  }

}
