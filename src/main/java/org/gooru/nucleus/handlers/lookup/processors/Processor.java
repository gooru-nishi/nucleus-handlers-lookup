package org.gooru.nucleus.handlers.lookup.processors;

import io.vertx.core.json.JsonObject;

public interface Processor {
  public JsonObject process();
}
