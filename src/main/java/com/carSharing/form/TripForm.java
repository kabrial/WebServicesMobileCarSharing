package com.carSharing.form;

import java.io.Serializable;
import java.util.List;

import com.carSharing.model.TripChild;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TripForm implements Serializable {

    /**
    * 
    */
    private static final long serialVersionUID = 6129880764934113529L;

    private Long id;
    private String dateDepart;
    private List<Long> childs;
    private String presenceParent;
    private long numberPlaces;
    private long placesReserved;

    public List<Long> getChilds() {

        return childs;
    }

    public TripForm() {
    }

    public void setChilds(List<Long> childs) {

        this.childs = childs;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getDateDepart() {

        return dateDepart;
    }

    public void setDateDepart(String dateDepart) {

        this.dateDepart = dateDepart;
    }

    public String getPresenceParent() {

        return presenceParent;
    }

    public void setPresenceParent(String presenceParent) {

        this.presenceParent = presenceParent;
    }

    
    public long getNumberPlaces() {
    
        return numberPlaces;
    }

    
    public void setNumberPlaces(long numberPlaces) {
    
        this.numberPlaces = numberPlaces;
    }

    
    public long getPlacesReserved() {
    
        return placesReserved;
    }

    
    public void setPlacesReserved(long placesReserved) {
    
        this.placesReserved = placesReserved;
    }

}
