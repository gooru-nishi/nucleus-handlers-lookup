package org.gooru.nucleus.handlers.lookup.processors;

import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import org.gooru.nucleus.handlers.lookup.constants.MessageConstants;
import org.gooru.nucleus.handlers.lookup.processors.exceptions.InvalidRequestException;
import org.gooru.nucleus.handlers.lookup.processors.exceptions.InvalidUserException;
import org.gooru.nucleus.handlers.lookup.processors.repositories.RepoBuilder;
import org.gooru.nucleus.handlers.lookup.processors.responses.transformers.ResponseTransformerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class MessageProcessor implements Processor {

  private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class);
  private String userId;
  private JsonObject prefs;
  private JsonObject request;
  private final Message<Object> message;

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
      userId = ((JsonObject) message.body()).getString(MessageConstants.MSG_USER_ID);
      if (userId == null) {
        LOGGER.error("Invalid user id passed. Not authorized.");
        throw new InvalidUserException();
      }
      prefs = ((JsonObject) message.body()).getJsonObject(MessageConstants.MSG_KEY_PREFS);
      request = ((JsonObject) message.body()).getJsonObject(MessageConstants.MSG_HTTP_BODY);
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
        case MessageConstants.MSG_OP_LKUP_DOK:
          result = processDepthOfKnowledge();
          break;
        case MessageConstants.MSG_OP_LKUP_AUDIENCE:
          result = processAudience();
          break;
        case MessageConstants.MSG_OP_LKUP_MOMENTS:
          result = processMomentsOfLearning();
          break;
        default:
          LOGGER.error("Invalid operation type passed in, not able to handle");
          throw new InvalidRequestException();
      }
      return new ResponseTransformerBuilder().build(result).transform();
    } catch (InvalidRequestException e) {
      LOGGER.warn("Caught Invalid Request exception while processing", e);
      return new ResponseTransformerBuilder().build(e).transform();
    } catch (InvalidUserException e) {
      LOGGER.warn("Caught Invalid User while processing", e);
      return new ResponseTransformerBuilder().build(e).transform();
    } catch (Throwable throwable) {
      LOGGER.warn("Caught unexpected exception here", throwable);
      return new ResponseTransformerBuilder().build(throwable).transform();
    }
  }

  private JsonObject processReadingLevels() {
    JsonObject result = ProcessorCache.getInstance().getReadingLevels();
    if (result == null) {
      result = new RepoBuilder().buildMetadataRepo().getReadingLevels();
      // Update the cache item
      ProcessorCache.getInstance().setReadingLevels(result);
    }
    return result;
  }

  private JsonObject processMediaFeatures() {
    JsonObject result = ProcessorCache.getInstance().getMediaFeatures();
    if (result == null) {
      result = new RepoBuilder().buildMetadataRepo().getMediaFeatures();
      // Update the cache item
      ProcessorCache.getInstance().setMediaFeatures(result);
    }
    return result;
  }

  private JsonObject processGrades() {
    JsonObject result = ProcessorCache.getInstance().getGrades();
    if (result == null) {
      result = new RepoBuilder().buildMetadataRepo().getGrades();
      // Update the cache item
      ProcessorCache.getInstance().setGrades(result);
    }
    return result;
  }

  private JsonObject processEducationalUse() {
    JsonObject result = ProcessorCache.getInstance().getEducationalUse();
    if (result == null) {
      result = new RepoBuilder().buildMetadataRepo().getEducationalUse();
      // Update the cache item
      ProcessorCache.getInstance().setEducationalUse(result);
    }
    return result;
  }

  private JsonObject processAdStatus() {
    JsonObject result = ProcessorCache.getInstance().getAdStatus();
    if (result == null) {
      result = new RepoBuilder().buildMetadataRepo().getAdStatus();
      // Update the cache item
      ProcessorCache.getInstance().setAdStatus(result);
    }
    return result;
  }

  private JsonObject process21CenSkills() {
    JsonObject result = ProcessorCache.getInstance().getCenSkills();
    if (result == null) {
      result = new RepoBuilder().buildCen21SkillsRepo().getCen21Skills();
      // Update the cache item
      ProcessorCache.getInstance().setCenSkills(result);
    }
    return result;
  }


  private JsonObject processAccessHazards() {
    JsonObject result = ProcessorCache.getInstance().getAccessHazards();
    if (result == null) {
      result = new RepoBuilder().buildMetadataRepo().getAccessHazards();
      // Update the cache item
      ProcessorCache.getInstance().setAccessHazards(result);
    }
    return result;
  }

  private JsonObject processMomentsOfLearning() {
    JsonObject result = ProcessorCache.getInstance().getMomentsOfLearning();
    if (result == null) {
      result = new RepoBuilder().buildMetadataRepo().getMomentsOfLearning();
      // Update the cache item
      ProcessorCache.getInstance().setMomentsOfLearning(result);
    }
    return result;

  }

  private JsonObject processDepthOfKnowledge() {
    JsonObject result = ProcessorCache.getInstance().getDepthOfKnowledge();
    if (result == null) {
      result = new RepoBuilder().buildMetadataRepo().getDepthOfKnowledge();
      // Update the cache item
      ProcessorCache.getInstance().setDepthOfKnowledge(result);
    }
    return result;

  }

  private JsonObject processAudience() {
    JsonObject result = ProcessorCache.getInstance().getAudience();
    if (result == null) {
      result = new RepoBuilder().buildMetadataRepo().getAudience();
      // Update the cache item
      ProcessorCache.getInstance().setAudience(result);
    }
    return result;

  }

}
