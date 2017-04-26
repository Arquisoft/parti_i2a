package jpa.services.impl;

import jpa.model.Category;
import jpa.model.Comment;
import jpa.model.Proposal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Daniel on 05-Apr-17.
 */
@Component
public class Report {

    private List<Category> categories;

    @Autowired
    private CategoryServiceImpl cs;

    public Report(){}

    public Report(List<Category> categories) {
        this.categories = categories;
    }

    public void loadDatabaseData() {
        List<Category> categoriesDB = cs.findAll();

        categoriesDB.forEach(category -> category.getProposals().forEach(proposal -> {
            proposal.getComments().forEach(comment -> comment.getUser());
            proposal.getUser();
        }));

        this.categories = categoriesDB;


    }

    public void add(Proposal proposal) {
        categories.forEach(category -> {
            if (category.getId().equals(proposal.getCategory().getId()))
                category.getProposals().add(proposal);
        });
    }

    public void add(Comment comment) {
        categories.forEach(category -> category.getProposals().forEach(proposal -> {
            if (proposal.getId().equals(comment.getProposal().getId()))
                proposal.getComments().add(comment);
        }));
    }

    public void add(Category category) {
        categories.add(category);
    }

    public void voteProposal(String content, Integer userID, Integer categoryID) {
        for (Category category : categories) {
            for (Proposal proposal : category.getProposals()) {
                if (proposal.getContent().equals(content) &&
                        proposal.getCategory().getId().equals(Long.valueOf(categoryID)) &&
                        proposal.getUser().getId().equals(Long.valueOf(userID))) {
                    proposal.setVotes(proposal.getVotes() + 1);
                }
            }
        }
    }

    public void voteComment(String content, Integer userID, Integer proposalID) {
        for (Category category : categories) {
            for (Proposal proposal : category.getProposals()) {
                for (Comment comment : proposal.getComments()) {
                    if (comment.getContent().equals(content) &&
                            comment.getProposal().getId().equals(Long.valueOf(proposalID)) &&
                            comment.getUser().getId().equals(Long.valueOf(userID))) {

                        comment.setVotes(comment.getVotes() + 1);
                    }
                }
            }
        }
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void delete(Proposal proposal) {
        categories.forEach(category -> {
            if (category.getProposals().contains(proposal))
                category.getProposals().remove(proposal);
        });
    }
}
