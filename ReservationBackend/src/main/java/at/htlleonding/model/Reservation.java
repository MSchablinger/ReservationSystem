package at.htlleonding.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Date;
import java.time.LocalTime;

@Entity
public class Reservation extends PanacheEntity {
    @Column(name = "r_start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "r_end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "r_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "r_reservation_timestamp", nullable = false)
    private Timestamp reservationTimestamp;

    @JoinColumn(name = "r_field", nullable = false)
    @ManyToOne
    private Field field;

    @JoinColumn(name = "r_customer", nullable = false)
    @ManyToOne
    private Account account;

    //region Getter and Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    //endregion
}
