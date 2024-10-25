package at.htlleonding.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
public class Field extends PanacheEntity {

    @Column(name = "f_name", nullable = false)
    private String name;

    @Column(name = "f_timeslot_duration", nullable = false)
    private int timeslotDuration;

    @Column(name = "f_open_time", nullable = false)
    private LocalTime openTime = LocalTime.of(8, 0);

    @Column(name = "f_close_time", nullable = false)
    private LocalTime closeTime = LocalTime.of(20, 0);

    @JoinColumn(name = "f_type", nullable = false)
    @ManyToOne
    private FieldType type;

    //region Getter and Setter
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

    public int getTimeslotDuration() {
        return timeslotDuration;
    }

    public void setTimeslotDuration(int timeslotDuration) {
        this.timeslotDuration = timeslotDuration;
    }

    public LocalTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalTime openTime) {
        this.openTime = openTime;
    }

    public LocalTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalTime closeTime) {
        this.closeTime = closeTime;
    }

    public FieldType getType() {
        return type;
    }

    public void setType(FieldType type) {
        this.type = type;
    }

    //endregion
}
