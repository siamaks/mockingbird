package com.smx.entity;

import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author SiamaX
 */
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Long id;

    private String name;

    protected Map<String, Boolean> emails;

    private long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, Boolean> getEmails() {
        return emails;
    }

    public void setEmails(Map<String, Boolean> emails) {
        this.emails = emails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smx.employeedirectory.core.entity.Person[id=" + id + "]";
    }
}
