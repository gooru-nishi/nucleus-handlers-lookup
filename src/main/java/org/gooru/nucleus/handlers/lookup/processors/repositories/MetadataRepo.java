package org.gooru.nucleus.handlers.lookup.processors.repositories;

import io.vertx.core.json.JsonObject;

/**
 * Created by ashish on 29/12/15.
 */
public interface MetadataRepo {
  JsonObject getReadingLevels();

  JsonObject getMediaFeatures();

  JsonObject getGrades();

  JsonObject getEducationalUse();

  JsonObject getAdStatus();

  JsonObject getAccessHazards();

  JsonObject getMomentsOfLearning();

  JsonObject getDepthOfKnowledge();

  JsonObject getAudience();
}
