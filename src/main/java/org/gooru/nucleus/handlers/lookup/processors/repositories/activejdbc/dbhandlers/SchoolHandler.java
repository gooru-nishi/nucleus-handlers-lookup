package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.dbhandlers;

import java.util.UUID;

import org.gooru.nucleus.handlers.lookup.constants.MessageConstants;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.entities.AJEntitySchool;
import org.gooru.nucleus.handlers.lookup.processors.responses.ExecutionResult;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponseFactory;
import org.javalite.activejdbc.LazyList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

class SchoolHandler implements DBHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(SchoolHandler.class);
  private final String keyword;
  private final String schoolDistrictId;

  public SchoolHandler(String keyword, String schoolDistrictId) {
    this.keyword = keyword;
    this.schoolDistrictId = schoolDistrictId;
  }

  @Override
  public ExecutionResult<MessageResponse> executeRequest() {
    JsonObject returnValue = null;
    UUID school_district_id = null;
    String schooldistrict_keyword  = "";
    LazyList<AJEntitySchool> result = null;
    if (keyword == null || keyword.isEmpty()) {
      return new ExecutionResult<>(MessageResponseFactory.createInvalidRequestResponse(), ExecutionResult.ExecutionStatus.FAILED);
    }
    
    schooldistrict_keyword = "%" + keyword + "%";
    if ( this.schoolDistrictId == null ) {
      LOGGER.debug("one param 1. {} " , keyword);
      result = AJEntitySchool.where("name ilike ?" , schooldistrict_keyword);
      returnValue = new JsonObject().put("school-districts", new JsonArray(result.toJson(false, "id", "name", "code")));
      return new ExecutionResult<>(MessageResponseFactory.createOkayResponse(returnValue), ExecutionResult.ExecutionStatus.SUCCESSFUL);
    } else if ( this.schoolDistrictId != null ) {
      school_district_id = UUID.fromString(schoolDistrictId);
      LOGGER.debug("two params 2. {} {}" , keyword, school_district_id);
      result = AJEntitySchool.where("name ilike ? AND school_district_id = ?::uuid" , schooldistrict_keyword, school_district_id);
      returnValue = new JsonObject().put("school-districts", new JsonArray(result.toJson(false, "id", "name", "code")));
      return new ExecutionResult<>(MessageResponseFactory.createOkayResponse(returnValue), ExecutionResult.ExecutionStatus.SUCCESSFUL);
    } else {
      return new ExecutionResult<>(MessageResponseFactory.createInvalidRequestResponse(), ExecutionResult.ExecutionStatus.FAILED);
    }
    
    /* if (!query.isEmpty() && (query != null)){
      String[] keywordMap = query.split("&");
      LOGGER.debug("length: {} ", keywordMap.length);
      if ( keywordMap.length == 1)
      {
        String[] keywordValue = keywordMap[0].split("=");
        if (keywordValue.length > 0) {
          if ( keywordValue[0].equalsIgnoreCase(MessageConstants.ID_KEYWORD)) {
            schooldistrict_keyword = "%" + keywordValue[1] + "%";
            LOGGER.debug("only one param" , schooldistrict_keyword);
           
          }
          return new ExecutionResult<>(MessageResponseFactory.createInvalidRequestResponse(), ExecutionResult.ExecutionStatus.FAILED);
        }
        return new ExecutionResult<>(MessageResponseFactory.createInvalidRequestResponse(), ExecutionResult.ExecutionStatus.FAILED);
      } else if ( keywordMap.length > 1){
        for ( int i = 0; i < keywordMap.length; i++) {
          LOGGER.debug(": {}", keywordMap[i]);
          String[] keywordValue = keywordMap[i].split("=");
          if(keywordValue[0].equalsIgnoreCase(MessageConstants.ID_KEYWORD)) {
            schooldistrict_keyword = "%" + keywordValue[1] + "%";
            LOGGER.debug("two params 1. {} {}" , keywordValue[0], schooldistrict_keyword);
          } else if (keywordValue[0].equalsIgnoreCase(MessageConstants.ID_SCHOOLDISTRICT)) {
            school_district_id = UUID.fromString(keywordValue[1]);
            LOGGER.debug("two params 2. {} {}" , keywordValue[0], school_district_id);
          }
          else
            return new ExecutionResult<>(MessageResponseFactory.createInvalidRequestResponse(), ExecutionResult.ExecutionStatus.FAILED);
        }
        
       
      }
      return new ExecutionResult<>(MessageResponseFactory.createInvalidRequestResponse(), ExecutionResult.ExecutionStatus.FAILED);
    }
    return new ExecutionResult<>(MessageResponseFactory.createInvalidRequestResponse(), ExecutionResult.ExecutionStatus.FAILED);*/
  }


  @Override
  public boolean handlerReadOnly() {
    return true;
  }

}
