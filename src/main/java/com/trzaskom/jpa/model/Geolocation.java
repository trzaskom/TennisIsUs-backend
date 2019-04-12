package com.trzaskom.jpa.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by miki on 2019-01-19.
 */

@Entity
@Table(name = "Geolocations")
public class Geolocation extends AuditModel {

    @Id
    private Long id;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
