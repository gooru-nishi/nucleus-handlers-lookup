package org.gooru.nucleus.handlers.lookup.processors.responses.transformers;

import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import org.gooru.nucleus.handlers.lookup.constants.MessageConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

class MessageBusResponseTransformer implements ResponseTransformer {

  static final Logger LOG = LoggerFactory.getLogger(ResponseTransformer.class);
  private JsonObject inputToTransform;
  private JsonObject transformedOutput;
  private boolean transformed = false;
  private Map<String, String> headers;
  private int httpStatus;
  private JsonObject httpBody;

  public MessageBusResponseTransformer(JsonObject inputToTransform) {
    this.inputToTransform = inputToTransform;
    if (inputToTransform == null) {
      LOG.error("Invalid or null JsonObject for initialization");
      throw new IllegalArgumentException("Invalid or null JsonObject for initialization");
    }
  }
  
  @Override
  public JsonObject transform() {
    if (!this.transformed) {
      processTransformation();
      this.transformed = true;
    }
    return this.transformedOutput;
  }

  private void processTransformation() {
    // TODO: Provide implementation

    transformedOutput = new JsonObject();

    // Now that we are done, mark it as transformed
    this.transformed = true;
  }




}
