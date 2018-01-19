package com.carSharing.form;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ReservedForm implements Serializable {
    
    private static final long serialVersionUID = -5572511954850878396L;

    private Long presenceParent;
    private List<Long> childs;
    
    public Long getPresenceParent() {
    
        return presenceParent;
    }
    
    public void setPresenceParent(Long presenceParent) {
    
        this.presenceParent = presenceParent;
    }
    
    public List<Long> getChilds() {
    
        return childs;
    }
    
    public void setChilds(List<Long> childs) {
    
        this.childs = childs;
    }
    
}
