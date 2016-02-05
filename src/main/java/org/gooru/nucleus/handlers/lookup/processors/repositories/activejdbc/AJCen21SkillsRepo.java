package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc;

import org.gooru.nucleus.handlers.lookup.processors.repositories.Cen21SkillsRepo;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;

/**
 * Created by ashish on 29/12/15.
 */
public class AJCen21SkillsRepo implements Cen21SkillsRepo {
  @Override
  public MessageResponse getCen21Skills() {
    return null;
    // return TransactionExecutor.executeTransaction(DBHandlerBuilder.build21CenSkillsHandler());
  }
}
