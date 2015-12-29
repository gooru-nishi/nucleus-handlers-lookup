package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc;

import org.gooru.nucleus.handlers.lookup.processors.repositories.Cen21SkillsRepo;
import org.gooru.nucleus.handlers.lookup.processors.repositories.MetadataRepo;

/**
 * Created by ashish on 29/12/15.
 */
public class ActiveJdbcRepoBuilder {

  public MetadataRepo buildMetadataRepo() {
    return new AJMetadataRepo();
  }

  public Cen21SkillsRepo buildCen21SkillsRepo() {
    return new AJCen21SkillsRepo();
  }
}
