package com.emusic.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    
	@ManyToMany(mappedBy = "privileges", cascade = CascadeType.PERSIST)
    private Collection<Role> roles;

    @Override
    public int hashCode() {
        final int prime = 31;
        return prime * ((name == null) ? 0 : name.hashCode()) + 29;
    }
	
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Privilege privilege = (Privilege) obj;
        if (!privilege.getName().equals(getName())) {
            return false;
        }
        return true;
    }
    
    public Privilege() {
        super();
    }

    public Privilege(final String name) {
        super();
        this.name = name;
    }
    
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}


}
