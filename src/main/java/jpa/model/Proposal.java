package jpa.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "proposals")
public class Proposal {

    @Id
    @GeneratedValue
    private Long id;
    private String content;
    private Integer votes;

    @ManyToOne
    private Category category;
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "proposal",cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    private List<Comment> comments;

    public Proposal() {
    }

    public Proposal(String content, Integer votes, User user, Category category) {
        super();
        this.content = content;
        this.votes = votes;
        this.user = user;
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> commentaries) {
        this.comments = commentaries;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "Content: " + content + "; Votes: " + votes + "; "
                + "Category: "+ category.getId() + "; User: " + user.getId();

    }

    @Override
    public boolean equals(Object obj) {
        return obj.toString().equals(this.toString());
    }

}
