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
    Base.open(DataSourceRegistry.getInstance().getDefaultDataSource());
    LazyList<MetadataReference> result = MetadataReference.findBySQL(
      "select label, sequence_id from metadata_reference where metadata_reference_type = 'reading_level' order by sequence_id");
    JsonObject returnValue = new JsonObject().put("reading_level", new JsonArray(result.toJson(false, "label", "sequence_id")));
    Base.close();
    return returnValue;
  }

  @Override
  public JsonObject getMediaFeatures() {
    Base.open(DataSourceRegistry.getInstance().getDefaultDataSource());
    LazyList<MetadataReference> result = MetadataReference.findBySQL(
      "select label, sequence_id from metadata_reference where metadata_reference_type = 'media_feature' order by sequence_id");
    JsonObject returnValue = new JsonObject().put("media_feature", new JsonArray(result.toJson(false, "label", "sequence_id")));
    Base.close();
    return returnValue;

  }

  @Override
  public JsonObject getGrades() {
    Base.open(DataSourceRegistry.getInstance().getDefaultDataSource());
    // TODO: Fix it
    LazyList<MetadataReference> result = MetadataReference.findBySQL(
      "select label, sequence_id from metadata_reference where metadata_reference_type = 'reading_level' order by sequence_id");
    JsonObject returnValue = new JsonObject().put("reading_level", new JsonArray(result.toJson(false, "label", "sequence_id")));
    Base.close();
    return returnValue;
  }

  @Override
  public JsonObject getEducationalUse() {
    Base.open(DataSourceRegistry.getInstance().getDefaultDataSource());
    LazyList<MetadataReference> result = MetadataReference.findBySQL(
      "select label, sequence_id from metadata_reference where metadata_reference_type = 'educational_use' order by sequence_id");
    JsonObject returnValue = new JsonObject().put("educational_use", new JsonArray(result.toJson(false, "label", "sequence_id")));
    Base.close();
    return returnValue;
  }

  @Override
  public JsonObject getAdStatus() {
    Base.open(DataSourceRegistry.getInstance().getDefaultDataSource());
    LazyList<MetadataReference> result = MetadataReference.findBySQL(
      "select label, sequence_id from metadata_reference where metadata_reference_type = 'advertisement_level' order by sequence_id");
    JsonObject returnValue = new JsonObject().put("advertisement_level", new JsonArray(result.toJson(false, "label", "sequence_id")));
    Base.close();
    return returnValue;
  }

  @Override
  public JsonObject getAccessHazards() {
    Base.open(DataSourceRegistry.getInstance().getDefaultDataSource());
    LazyList<MetadataReference> result = MetadataReference.findBySQL(
      "select label, sequence_id from metadata_reference where metadata_reference_type = 'hazard_level' order by sequence_id");
    JsonObject returnValue = new JsonObject().put("hazard_level", new JsonArray(result.toJson(false, "label", "sequence_id")));
    Base.close();
    return returnValue;
  }
}
