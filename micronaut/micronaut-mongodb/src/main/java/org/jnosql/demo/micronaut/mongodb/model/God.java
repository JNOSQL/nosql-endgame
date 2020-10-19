package org.jnosql.demo.micronaut.mongodb.model;

import java.util.Objects;

public class God {
    public static final String DISCRIMINATOR_KEY = "name";

    private String name;
    private String power;
    
    public God() {    	
    }
    
    public God(String type) {
        this.name = type;
    }

    public void setName(String type) {
        this.name = type;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        God god = (God) o;
        return Objects.equals(name, god.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("God{");
        sb.append("name='").append(name).append('\'');
        sb.append("power='").append(power).append('\'');
        sb.append('}');
        return sb.toString();
    }

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}
}
