package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc;

import org.gooru.nucleus.handlers.lookup.processors.repositories.Cen21SkillsRepo;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.dbhandlers.DBHandlerBuilder;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.transactions.TransactionExecutor;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AJCen21SkillsRepo implements Cen21SkillsRepo {
  private static final Logger LOGGER = LoggerFactory.getLogger(AJCen21SkillsRepo.class);

  @Override
  public MessageResponse getCen21Skills() {
    LOGGER.info("In handlers");
    return TransactionExecutor.executeTransaction(DBHandlerBuilder.build21CenSkillsHandlerBuilder());
  }
}
