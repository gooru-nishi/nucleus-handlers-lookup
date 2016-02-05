package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.dbhandlers;

/**
 * Created by ashish on 11/1/16.
 */
public final class DBHandlerBuilder {

  private DBHandlerBuilder() {
    throw new AssertionError();
  }

  public static DBHandler fetchRowlistExecutorHandlerBuilder(String name, String sql, String[] fieldsInJson) {
    return new FetchRowlistExecutorHandler(name, sql, fieldsInJson);
  }

}
