package org.gooru.nucleus.handlers.lookup.processors.repositories;

import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.ActiveJdbcRepoBuilder;

/**
 * Created by ashish on 29/12/15.
 */
public class RepoBuilder {
  public MetadataRepo buildMetadataRepo() {
    return new ActiveJdbcRepoBuilder().buildMetadataRepo();
  }

  public Cen21SkillsRepo buildCen21SkillsRepo() {
    return new ActiveJdbcRepoBuilder().buildCen21SkillsRepo();
  }
}
