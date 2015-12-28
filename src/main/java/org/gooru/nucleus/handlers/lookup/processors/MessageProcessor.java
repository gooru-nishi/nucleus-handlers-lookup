package org.gooru.nucleus.handlers.lookup.processors;

import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import org.gooru.nucleus.handlers.lookup.constants.MessageConstants;
import org.gooru.nucleus.handlers.lookup.processors.exceptions.InvalidRequestException;
import org.gooru.nucleus.handlers.lookup.processors.exceptions.InvalidUserException;
import org.gooru.nucleus.handlers.lookup.processors.responses.transformers.ResponseTransformerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class MessageProcessor implements Processor {

  private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class);
  private Message<Object> message;
  String userId;
  JsonObject prefs;
  JsonObject request;
  
  public MessageProcessor(Message<Object> message) {
    this.message = message;
  }
  
  @Override
  public JsonObject process() {
    JsonObject result;
    try {
      if (message == null || !(message.body() instanceof JsonObject)) {
        LOGGER.error("Invalid message received, either null or body of message is not JsonObject ");
        throw new InvalidRequestException();
      }
      
      final String msgOp = message.headers().get(MessageConstants.MSG_HEADER_OP);
      userId = ((JsonObject)message.body()).getString(MessageConstants.MSG_USER_ID);
      if (userId == null) {
        LOGGER.error("Invalid user id passed. Not authorized.");
        throw new InvalidUserException();
      }
      prefs = ((JsonObject)message.body()).getJsonObject(MessageConstants.MSG_KEY_PREFS);
      request = ((JsonObject)message.body()).getJsonObject(MessageConstants.MSG_HTTP_BODY);
      switch (msgOp) {
        case MessageConstants.MSG_OP_LKUP_ACCESS_HAZARDS:
          result = processAccessHazards();
          break;
        case MessageConstants.MSG_OP_LKUP_21_CEN_SKILLS:
          result = process21CenSkills();
          break;
        case MessageConstants.MSG_OP_LKUP_AD_STATUS:
          result = processAdStatus();
          break;
        case MessageConstants.MSG_OP_LKUP_EDU_USE:
          result = processEducationalUse();
          break;
        case MessageConstants.MSG_OP_LKUP_GRADE:
          result = processGrades();
          break;
        case MessageConstants.MSG_OP_LKUP_MEDIA_FEATURES:
          result = processMediaFeatures();
          break;
        case MessageConstants.MSG_OP_LKUP_READ_LEVEL:
          result = processReadingLevels();
          break;
      default:
        LOGGER.error("Invalid operation type passed in, not able to handle");
        throw new InvalidRequestException();
      }
      return new ResponseTransformerBuilder().build(result).transform();
    } catch (InvalidRequestException e) {
      // TODO: handle exception
    } catch (InvalidUserException e) {
      // TODO: handle exception
    }

    return null;
  }

  private JsonObject processReadingLevels() {
    JsonObject result = ProcessorCache.getInstance().getReadingLevels();
    if (result != null) {
      return result;
    } else {
      // TODO: Initialize from DB

      // Update the cache item

      // process and return
    }
    return null;
  }

  private JsonObject processMediaFeatures() {
    JsonObject result = ProcessorCache.getInstance().getMediaFeatures();
    if (result != null) {
      return result;
    } else {
      // TODO: Initialize from DB

      // Update the cache item

      // process and return
    }
    return null;
  }

  private JsonObject processGrades() {
    JsonObject result = ProcessorCache.getInstance().getGrades();
    if (result != null) {
      return result;
    } else {
      // TODO: Initialize from DB

      // Update the cache item

      // process and return
    }
    return null;
  }

  private JsonObject processEducationalUse() {
    JsonObject result = ProcessorCache.getInstance().getEducationalUse();
    if (result != null) {
      return result;
    } else {
      // TODO: Initialize from DB

      // Update the cache item

      // process and return
    }
    return null;
  }

  private JsonObject processAdStatus() {
    JsonObject result = ProcessorCache.getInstance().getAdStatus();
    if (result != null) {
      return result;
    } else {
      // TODO: Initialize from DB

      // Update the cache item

      // process and return
    }
    return null;
  }

  private JsonObject process21CenSkills() {
    JsonObject result = ProcessorCache.getInstance().getCenSkills();
    if (result != null) {
      return result;
    } else {
      // TODO: Initialize from DB

      // Update the cache item

      // process and return
    }
    return null;
  }


  private JsonObject processAccessHazards() {
    JsonObject result = ProcessorCache.getInstance().getAccessHazards();
    if (result != null) {
      return result;
    } else {
      // TODO: Initialize from DB

      // Update the cache item

      // process and return
    }
    return null;
  }

}
