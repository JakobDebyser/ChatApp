package be.multimedi.chatapp.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "CHAT_MESSAGE")
public class Message {
   @Id
   @Column(name = "Id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

   @Column(name = "Text")
   private String text;

   @Column(name = "Timestamp")
   private LocalDateTime dateTime;

   @ManyToOne
   @JoinColumn(name = "UserId")
   private User user;

   public Message() {
   }

   public Message(String text, LocalDateTime dateTime) {
      this.text = text;
      this.dateTime = dateTime;
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getText() {
      return text;
   }

   public void setText(String text) {
      this.text = text;
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
      return
              "[" + dateTime +"]"+ user.getUserName() +"\n"+ ">" + text ;
   }
}
