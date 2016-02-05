package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc;

import org.gooru.nucleus.handlers.lookup.processors.repositories.Cen21SkillsRepo;
import org.gooru.nucleus.handlers.lookup.processors.repositories.MetadataRepo;

/**
 * Created by ashish on 29/12/15.
 */
public final class ActiveJdbcRepoBuilder {

  private ActiveJdbcRepoBuilder() {
    throw new AssertionError();
  }

  public static MetadataRepo buildMetadataRepo() {
    return new AJMetadataRepo();
  }

  public static Cen21SkillsRepo buildCen21SkillsRepo() {
    return new AJCen21SkillsRepo();
  }
}
