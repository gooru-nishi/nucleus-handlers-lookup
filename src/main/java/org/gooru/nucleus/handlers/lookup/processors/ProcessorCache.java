package org.gooru.nucleus.handlers.lookup.processors;

import io.vertx.core.json.JsonObject;

/**
 * Created by ashish on 28/12/15.
 */
public class ProcessorCache {
  private static final ProcessorCache INSTANCE = new ProcessorCache();

  private CacheHolder readingLevels;
  private CacheHolder mediaFeatures;
  private CacheHolder grades;
  private CacheHolder educationalUse;
  private CacheHolder adStatus;
  private CacheHolder cenSkills;
  private CacheHolder accessHazards;

  public JsonObject getReadingLevels() {
    return readingLevels.getCachedValue();
  }

  public void setReadingLevels(JsonObject readingLevels) {
    if (readingLevels != null) {
      this.readingLevels.initialize(readingLevels);
    }
  }

  public JsonObject getMediaFeatures() {
    return this.mediaFeatures.getCachedValue();
  }

  public void setMediaFeatures(JsonObject mediaFeatures) {
    if (mediaFeatures != null) {
      this.mediaFeatures.initialize(mediaFeatures);
    }
  }

  public JsonObject getGrades() {
    return this.grades.getCachedValue();
  }

  public void setGrades(JsonObject grades) {
    if (this.grades != null) {
      this.grades.initialize(grades);
    }
  }

  public JsonObject getEducationalUse() {
    return this.educationalUse.getCachedValue();
  }

  public void setEducationalUse(JsonObject educationalUse) {
    if (educationalUse != null) {
      this.educationalUse.initialize(educationalUse);
    }
  }

  public JsonObject getAdStatus() {
    return this.adStatus.getCachedValue();
  }

  public void setAdStatus(JsonObject adStatus) {
    if (adStatus != null) {
      this.adStatus.initialize(adStatus);
    }
  }

  public JsonObject getCenSkills() {
    return this.cenSkills.getCachedValue();
  }

  public void setCenSkills(JsonObject cenSkills) {
    if (cenSkills != null) {
      this.cenSkills.initialize(cenSkills);
    }
  }

  public JsonObject getAccessHazards() {
    return this.accessHazards.getCachedValue();
  }

  public void setAccessHazards(JsonObject accessHazards) {
    if (accessHazards != null) {
      this.accessHazards.initialize(accessHazards);
    }
  }

  public static ProcessorCache getInstance() {
    return INSTANCE;
  }

  private ProcessorCache() {
    this.readingLevels = new CacheHolder();
    this.mediaFeatures = new CacheHolder();
    this.grades = new CacheHolder();
    this.educationalUse = new CacheHolder();
    this.adStatus = new CacheHolder();
    this.cenSkills = new CacheHolder();
  }

  private class CacheHolder {
    JsonObject cache;
    private boolean initialized = false;
    private Object lock = new Object();

    public CacheHolder() {};

    public void initialize(JsonObject cacheItem) {
      if (!initialized) {
        synchronized (lock) {
          if (!initialized) {
            this.cache = cacheItem;
            this.initialized = true;
          }
        }
      }
    }

    public void reset() {
      this.initialized = false;
    }

    public JsonObject getCachedValue() {
      return this.cache.copy();
    }

  }
}
