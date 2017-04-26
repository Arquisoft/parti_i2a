package main;

import jpa.model.Category;
import jpa.model.Comment;
import jpa.model.Proposal;
import jpa.model.User;
import jpa.repositories.CategoryRepository;
import jpa.repositories.CommentRepository;
import jpa.repositories.ProposalRepository;
import jpa.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertFalse;

/**
 * Created by Alex on 02/04/2017.
 * Checks the validity of the database using JPA.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersistenceTest {

    @Autowired
    private UserRepository userRepository;
    @Qualifier("proposalRepository")
    @Autowired
    private ProposalRepository proposalRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void testProposal() {
        List<Proposal> p = (List<Proposal>) proposalRepository.findAll();
        assertFalse(p.isEmpty());
    }


    @Test
    public void testCommentary(){
        List<Comment> c = (List<Comment>) commentRepository.findAll();
        assertFalse(c.isEmpty());
    }

    @Test
    public void testProposal2() {
        List<Proposal> p = (List<Proposal>) proposalRepository.findAll();
        assertFalse(p.isEmpty());
    }

    @Test
    public void testCommentary2(){
        List<Comment> c = (List<Comment>) commentRepository.findAll();
        assertFalse(c.isEmpty());
    }

    @Test
    public void testProposal3() {
        List<Proposal> p = (List<Proposal>) proposalRepository.findAll();
        assertFalse(p.isEmpty());
    }

    @Test
    public void testCommentary3(){
        List<Comment> c = (List<Comment>) commentRepository.findAll();
        assertFalse(c.isEmpty());
    }

    @Test
    public void testCategory(){
        List<Category> c = (List<Category>) categoryRepository.findAll();
        assertFalse(c.isEmpty());
    }

    @Test
    public void testUser(){
        List<User> c = (List<User>) userRepository.findAll();
        assertFalse(c.isEmpty());
    }
}
