package ca.sheridancollege.codeavengers.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
public class Role {
  @Id
  private String id;

  private eRole name;

  public Role() {

  }

  public Role(eRole name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public eRole getName() {
    return name;
  }

  public void setName(eRole name) {
    this.name = name;
  }
}