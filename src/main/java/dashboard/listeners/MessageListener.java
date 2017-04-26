package dashboard.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import jpa.model.Category;
import jpa.model.Comment;
import jpa.model.Proposal;
import jpa.services.CategoryService;
import jpa.services.ProposalService;
import jpa.services.UserService;
import jpa.services.impl.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by herminio on 28/12/16.
 * Modified by Daniel.
 */
public class MessageListener {

    @Autowired
    private Report report;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProposalService proposalService;
    @Autowired
    private UserService userService;

    private static final Logger logger = Logger.getLogger(MessageListener.class);
    private boolean test = false;

    public void setTest(boolean test) {
        this.test = test;
    }

    @KafkaListener(topics = "test")
    public void processMessage(String content) throws InterruptedException {
        logger.info("New message received: \"" + content + "\"");
        System.out.println("New message received: \"" + content + "\"");
        test = true;
    }

    @KafkaListener(topics = "proposals")
    public void processJSONProposal(String jsonString) throws IOException {
        Proposal proposal = new ObjectMapper().readValue(jsonString, Proposal.class);
        String data = "Proposal:\tVotes: " + proposal.getVotes()
                + " Content: "  + proposal.getContent();
        Files.write(Paths.get("output.txt"),"".getBytes());
    }

    @KafkaListener(topics = "comments")
    public void processJSONComments(String jsonString) throws IOException {
        Comment comment = new ObjectMapper().readValue(jsonString,Comment.class);
        String data = "Comment:\tVotes: " + comment.getVotes()
                + " Date: "  + comment.getDate();
        Files.write(Paths.get("output.txt"),"".getBytes());
    }

    @KafkaListener(topics = "addComment")
    public void addComment(String comment) throws ParseException {
        String[] commentData = comment.split(";");
        report.add(
                new Comment(
                        commentData[0],
                        Integer.valueOf(commentData[1]),
                        new SimpleDateFormat("yyyy/MM/dd").parse(commentData[2]),
                        userService.findUserById(Long.valueOf(commentData[3])),
                        proposalService.findProposalById(Long.valueOf(commentData[4]))
                )
        );
    }

    @KafkaListener(topics = "addProposal")
    public void addProposal(String proposal) {
        String[] proposalData = proposal.split(";");
        report.add(
                new Proposal(
                        proposalData[0],
                        Integer.valueOf(proposalData[1]),
                        userService.findUserById(Long.parseLong(proposalData[2])),
                        categoryService.findCategoryById(Long.valueOf(proposalData[3]))
                )
        );
    }

    @KafkaListener(topics = "deleteProposal")
    public void deleteProposal(String proposal) {
        String[] proposalData = proposal.split(";");
        report.delete(
                new Proposal(
                        proposalData[0],
                        Integer.valueOf(proposalData[1]),
                        userService.findUserById(Long.parseLong(proposalData[2])),
                        categoryService.findCategoryById(Long.valueOf(proposalData[3]))
                )
        );
    }

    @KafkaListener(topics = "addCategory")
    public void addCategory(String categoryName) {
        report.add(
                new Category(categoryName)
        );
    }

    @KafkaListener(topics= "voteProposal")
    public void voteProposal(String proposal) {
        String[] proposalData = proposal.split(";");
        report.voteProposal(proposalData[0],
                Integer.valueOf(proposalData[2]),
                Integer.valueOf(proposalData[3]));
    }

    @KafkaListener(topics= "voteComment")
    public void voteComment(String comment) {
        String[] proposalData = comment.split(";");
        report.voteComment(proposalData[0],
                Integer.valueOf(proposalData[3]),
                Integer.valueOf(proposalData[4]));
    }

    public boolean getTest() {
        return test;
    }
}
