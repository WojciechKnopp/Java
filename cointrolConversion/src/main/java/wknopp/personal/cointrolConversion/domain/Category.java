package wknopp.personal.cointrolConversion.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Category {

    public enum Scope {INCOME, EXPENSE};

    private long id;
    //TODO: Add user foerign key
    private String user;
    private String name;
    private Scope scope;

    public Category() {
    }

    public Category(long id, String user, String name, Scope scope) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.scope = scope;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "Category [id=" + id + ", user=" + user + ", name=" + name + ", scope=" + scope + "]";
    }
}
