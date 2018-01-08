package com.carSharing.form;

import java.io.Serializable;

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

}
