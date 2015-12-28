package org.gooru.nucleus.handlers.lookup.processors.responses.transformers;


import io.vertx.core.json.JsonObject;

import java.util.Map;

public interface ResponseTransformer {
  
  JsonObject transform();

}
