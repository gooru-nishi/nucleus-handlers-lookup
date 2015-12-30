package org.gooru.nucleus.handlers.lookup.processors;

import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ashish on 28/12/15.
 */
public class ProcessorCache {
  private static final ProcessorCache INSTANCE = new ProcessorCache();
  private static final Logger LOGGER = LoggerFactory.getLogger(ProcessorCache.class);

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
      LOGGER.debug("Trying to initialize reading levels");
      this.readingLevels.initialize(readingLevels);
    }
  }

  public JsonObject getMediaFeatures() {
    return this.mediaFeatures.getCachedValue();
  }

  public void setMediaFeatures(JsonObject mediaFeatures) {
    if (mediaFeatures != null) {
      LOGGER.debug("Trying to initialize media features");
      this.mediaFeatures.initialize(mediaFeatures);
    }
  }

  public JsonObject getGrades() {
    return this.grades.getCachedValue();
  }

  public void setGrades(JsonObject grades) {
    if (this.grades != null) {
      LOGGER.debug("Trying to initialize grades");
      this.grades.initialize(grades);
    }
  }

  public JsonObject getEducationalUse() {
    return this.educationalUse.getCachedValue();
  }

  public void setEducationalUse(JsonObject educationalUse) {
    if (educationalUse != null) {
      LOGGER.debug("Trying to initialize educational use");
      this.educationalUse.initialize(educationalUse);
    }
  }

  public JsonObject getAdStatus() {
    return this.adStatus.getCachedValue();
  }

  public void setAdStatus(JsonObject adStatus) {
    if (adStatus != null) {
      LOGGER.debug("Trying to initialize ad status");
      this.adStatus.initialize(adStatus);
    }
  }

  public JsonObject getCenSkills() {
    return this.cenSkills.getCachedValue();
  }

  public void setCenSkills(JsonObject cenSkills) {
    if (cenSkills != null) {
      LOGGER.debug("Trying to initialize 21st century skills");
      this.cenSkills.initialize(cenSkills);
    }
  }

  public JsonObject getAccessHazards() {
    return this.accessHazards.getCachedValue();
  }

  public void setAccessHazards(JsonObject accessHazards) {
    if (accessHazards != null) {
      LOGGER.debug("Trying to initialize access hazards");
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
    this.accessHazards = new CacheHolder();
  }

  private class CacheHolder {
    JsonObject cache;
    private boolean initialized = false;
    private Object lock = new Object();

    public CacheHolder() {};

    public void initialize(JsonObject cacheItem) {
      if (cacheItem != null) {
        if (!initialized) {
          synchronized (lock) {
            if (!initialized) {
              LOGGER.debug("Initialization successful");
              this.cache = cacheItem.copy();
              this.initialized = true;
            }
          }
        }
      }
    }

    public void reset() {
      this.initialized = false;
    }

    public JsonObject getCachedValue() {
      if (this.cache == null) {
        return null;
      }
      return this.cache.copy();
    }

  }
}
