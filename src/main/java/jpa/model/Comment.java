package jpa.model;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    private String content;
    private int votes;
    private Date date;

    @ManyToOne
    private User user;

    @ManyToOne
    private Proposal proposal;

    public Comment() {}

    public Comment(Long idComment, String content, Integer votes, Date date) {
        super();
        this.id = idComment;
        this.content = content;
        this.votes = votes;
        this.date = date;
    }

    public Comment(String content, int votes, Date date, User user, Proposal proposal) {
        super();
        this.content = content;
        this.votes = votes;
        this.date = date;
        this.user = user;
        this.proposal = proposal;
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

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Proposal getProposal() {
        return proposal;
    }

    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }

    @Override
    public boolean equals(Object obj) {
        return obj.toString().equals(this.toString());
    }

    @Override
    public String toString() {
        String simpleDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
        return "Content: " + content + "; Votes: " + votes + "; " + "Date: " + simpleDate + "; Proposal: "
                + "; User: " ;//+ userId  //+ proposalId

    }
}
