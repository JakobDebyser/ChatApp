package be.multimedi.chatapp.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Entity
@Table(name = "User")
public class User {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long userId;

    @Column(name = "USERNAME", unique=true, length = 32)
    @Basic(optional = false)
    @NotEmpty
    @Size(max = 32)
    private String userName;

   /* @Transient // meaning it will not be saved in DB
    @Size(min=12, max=32, message="{register.password.size}")
    private String plainPassword; //unencrytped

    @Transient
    private String repeatPassword;*/

    @Column(name = "PASSWORD", length = 32)
    @Basic(optional = false)
    @NotNull
    @Size(min = 6, max = 32)
    private String password;

    @Column(name = "EMAIL_ADDRESS", unique = true)
    @Email(message="{register.email.invalid}")
    @NotBlank(message="{register.email.invalid}")
    @Size(max = 32)
    private String email;

    @ManyToMany
    public Set<User> friends = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private List<Message> messages = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Request> requests=new ArrayList<>();

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void addFriend(User user) {
        friends.add(user);

    }

    public void removeFriend(User user) {
        friends.remove(user);

    }

    public void addRequest(Request request) {
        requests.add(request);

    }

    public void removeRequest(Request request) {
        requests.remove(request);

    }

    public void addMessage(Message msg) {
        messages.add(msg);

    }

    public void removeMessage(Message msg) {
        messages.remove(msg);

    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    @Override
    public String toString() {
        return
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
