package org.gooru.nucleus.handlers.lookup.processors.responses.transformers;


import io.vertx.core.json.JsonObject;

public interface ResponseTransformer {

  JsonObject transform();

}
