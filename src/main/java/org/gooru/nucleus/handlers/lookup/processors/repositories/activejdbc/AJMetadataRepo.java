package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.gooru.nucleus.handlers.lookup.app.components.DataSourceRegistry;
import org.gooru.nucleus.handlers.lookup.processors.repositories.MetadataRepo;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.entities.MetadataReference;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.DB;
import org.javalite.activejdbc.LazyList;

/**
 * Created by ashish on 29/12/15.
 */
public class AJMetadataRepo implements MetadataRepo {

  private static final String DB_DEFAULT = "lookup";

  @Override
  public JsonObject getReadingLevels() {
    return getMetadata("reading_level",
      "select id, label, sequence_id from metadata_reference where metadata_reference_type = 'reading_level' order by sequence_id");
  }

  @Override
  public JsonObject getMediaFeatures() {
    return getMetadata("media_feature",
      "select id, label, sequence_id from metadata_reference where metadata_reference_type = 'media_feature' order by sequence_id");
  }

  @Override
  public JsonObject getGrades() {
    // TODO: Verify if this is fixed at DB level
    return getMetadata("grade",
      "select id, label, sequence_id from metadata_reference where metadata_reference_type = 'grade' order by sequence_id");
  }

  @Override
  public JsonObject getEducationalUse() {
    return getMetadata("educational_use",
      "select id, label, sequence_id from metadata_reference where metadata_reference_type = 'educational_use' order by sequence_id");
  }

  @Override
  public JsonObject getAdStatus() {
    return getMetadata("advertisement_level",
      "select id, label, sequence_id from metadata_reference where metadata_reference_type = 'advertisement_level' order by sequence_id");
  }

  @Override
  public JsonObject getAccessHazards() {
    return getMetadata("hazard_level",
      "select id, label, sequence_id from metadata_reference where metadata_reference_type = 'hazard_level' order by sequence_id");

  }

  @Override
  public JsonObject getMomentsOfLearning() {
    return getMetadata("moments_of_learning",
      "select id, label, sequence_id from metadata_reference where metadata_reference_type = 'moments_of_learning' order by sequence_id");

  }

  @Override
  public JsonObject getDepthOfKnowledge() {
    return getMetadata("depth_of_knowledge",
      "select id, label, sequence_id from metadata_reference where metadata_reference_type = 'depth_of_knowledge' order by sequence_id");
  }

  @Override
  public JsonObject getAudience() {
    return getMetadata("audience",
      "select id, label, sequence_id from metadata_reference where metadata_reference_type = 'audience' order by sequence_id");
  }

  private JsonObject getMetadata(String name, String sql) {
    Base.open(DataSourceRegistry.getInstance().getDefaultDataSource());
    LazyList<MetadataReference> result = MetadataReference.findBySQL(sql);
    JsonObject returnValue = new JsonObject().put(name, new JsonArray(result.toJson(false, "id", "label", "sequence_id")));
    Base.close();
    return returnValue;
  }
}
