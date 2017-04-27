package participants;

import jpa.model.User;
import jpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Dax on 21-Feb-17.
 */
@Controller
public class WebController extends WebMvcConfigurerAdapter {

    @Autowired
    private UserRepository repository;

    @GetMapping("/participants/login")
    public String loginForm(Model model){
        model.addAttribute("credentials", new Credentials());
        return "login";
    }

    @PostMapping("/participants/update")
    public String updateInfo(User participant, Model model){
        model.addAttribute("participant",repository.save(participant));
        return "update";
    }

    @PostMapping("/participants/userInfo")
    public String showData(Credentials credentials, Model model){
        User participant = repository.findUserByEmailAndPassword(
                credentials.getEmail(),credentials.getPassword());
        model.addAttribute("participant",participant);
        return "update";
    }

    @PostMapping("/participants/changePassword")
    public String showData(User participant,Model model){
        model.addAttribute("participant",repository
                .findUserByEmailAndPassword(participant.getEmail(),participant.getPassword()));
        return "changePassword";
    }
}
