package participants;


import jpa.model.User;
import jpa.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

    private static final Logger LOG = LoggerFactory.getLogger(APIController.class);

    @Autowired
    private UserRepository repository;

    @PostMapping("/user")
    public ResponseEntity<User> getParticipantInfo(
            @RequestBody Credentials credentials) {
        LOG.info("Email trying to log-in:  " + credentials.getEmail());
        User participant = repository.findUserByEmailAndPassword(credentials.getEmail()
                                        ,credentials.getPassword());

        if(participant != null)
            return new ResponseEntity<>(participant,
                    HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}