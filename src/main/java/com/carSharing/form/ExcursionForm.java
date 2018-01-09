package com.carSharing.form;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ExcursionForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -3106847554667809748L;
    private long days;
    private String name;
    private List<String> groups;

    public long getDays() {

        return days;
    }

    public void setDays(long days) {

        this.days = days;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public List<String> getGroups() {

        return groups;
    }

    public void setGroups(List<String> groups) {

        this.groups = groups;
    }

}
