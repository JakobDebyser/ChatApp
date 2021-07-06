package be.multimedi.chatapp.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "Request")
public class Request {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long Id;

    @Column(name = "ACCEPT")
    private Boolean accept;


    @ManyToOne
    private User requestName;

    @Column(name = "DATE_TIME")
    private LocalDateTime dateTime;

    @ManyToOne
    private User user;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Boolean getAccept() {
        return accept;
    }

    public void setAccept(Boolean accept) {
        this.accept = accept;
    }

    public User getRequestName() {
        return requestName;
    }

    public void setRequestName(User requestName) {
        this.requestName = requestName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return   requestName.getUserName() +" heeft een request gestuurd " + "op "+ dateTime;
    }
}
