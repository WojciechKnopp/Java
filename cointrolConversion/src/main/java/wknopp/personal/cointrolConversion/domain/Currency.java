package wknopp.personal.cointrolConversion.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Currency {
    private long id;
    private String name;
    private String sign;

    public Currency() {
    }

    public Currency(long id, String name, String sign) {
        this.id = id;
        this.name = name;
        this.sign = sign;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "Currency [id=" + id + ", name=" + name + ", sign=" + sign + "]";
    }
}
