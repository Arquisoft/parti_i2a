package main;

import common.dto.User;
import common.persistence.CommonPersistence;
import jpa.repositories.UserRepository;
import jpa.services.impl.Report;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import participationSystem.hello.dto.Category;
import participationSystem.hello.dto.Comment;
import participationSystem.hello.dto.Proposal;
import participationSystem.hello.model.AddComment;
import participationSystem.hello.model.AddProposal;
import participationSystem.hello.model.ControlAdmin;
import participationSystem.hello.persistence.CommentDao;
import participationSystem.hello.persistence.ProposalDao;
import participationSystem.hello.producers.KafkaProducer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@ComponentScan(basePackages = {"jpa","participationSystem"})
@SessionAttributes("participant")
public class MainController {

	private ProposalDao pDao = CommonPersistence.getProposalDao();

	private CommentDao cDao = CommonPersistence.getCommentaryDao();
	private User user;
	private jpa.model.User userJpa;
	private static final Logger logger = Logger.getLogger(MainController.class);

	private List<SseEmitter> sseEmitters = Collections.synchronizedList(new ArrayList<>());
	private boolean latch = true;

	//Beans
	private final KafkaProducer kafkaProducer;
    private final Report report;
	private final UserRepository repository;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    public MainController(KafkaProducer kafkaProducer, Report report, UserRepository repository) {
        this.kafkaProducer = kafkaProducer;
        this.report = report;
		this.repository = repository;
	}

    @RequestMapping("/dashboard")
	public String landing() throws InterruptedException {
		if (latch) {
			latch = false;
			report.loadDatabaseData();
		}
		return "dashboard";
	}

	@ModelAttribute
	public AddProposal getAddProposal() {
		return new AddProposal();
	}

	@ModelAttribute
	public List<Proposal> getProposals() {
		return pDao.getProposals();
	}

	@RequestMapping("/")
	public String landing(Model model) {
		model.addAttribute("addProposal", new AddProposal());
		model.addAttribute("proposals", pDao.getProposals());
		model.addAttribute("controlAdmin", new ControlAdmin());
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		this.user = CommonPersistence.getUserDao().getUserByEmail(email);
		this.userJpa = repository.findUserByEmail(email);

		if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().equals("[ROLE_ADMIN]"))
			return "/admin";
		return "/user/home";
	}

	@RequestMapping("/login")
	public String login(Model model) {
		return "login";
	}

	@RequestMapping("/logout")
	public String logout(Model model) {
		SecurityContextHolder.clearContext();
		return "login";
	}

	@RequestMapping("/user/home")
	public String send(Model model) {
		model.addAttribute("proposals", pDao.getProposals());
		return "/user/home";
	}

	@RequestMapping("/admin")
	public String go(Model model) {
		model.addAttribute("proposals", pDao.getProposals());
		model.addAttribute("controlAdmin", new ControlAdmin());
		return "/admin";
	}

	@RequestMapping(value = "/user/addForm", method = RequestMethod.GET)
	public String goToForm(Model model) {
		model.addAttribute("addProposal", new AddProposal());
		model.addAttribute("categoriesList", CommonPersistence.getCategoryDao().findAllCategories());
		return "/user/add-form";
	}

	@RequestMapping(value = "/user/viewProposal/{id}", method = RequestMethod.GET)
	public String viewProposal(Model model, @PathVariable("id") String id) {
		Integer idInt = Integer.parseInt(id);
		Proposal proposal = pDao.getProposalById(idInt);
		List<Comment> commentsList = cDao.getCommentsFromProposalId(idInt);

		model.addAttribute("proposal", proposal);
		model.addAttribute("commentsList", commentsList);
		model.addAttribute("addComment", new AddComment());

		return "/user/proposal";
	}

	@RequestMapping("/user/addProposal")
	public String addProposal(Model model, @ModelAttribute AddProposal addProposal) {
		Proposal proposal = new Proposal();
		proposal.setContent(addProposal.getText());
		proposal.setCategoryId(Integer.parseInt(addProposal.getCategory().getName()));
		proposal.setUserId(user.getId());
		proposal.setVotes(0);
		try {
			pDao.createProposal(proposal);
			kafkaProducer.send("addProposal", proposal.kafkaStringFormat());
		} catch (Exception e) {
			System.out.println("funca bien");
			return "/mensajeError";
		}
		return "redirect:/user/home";
	}

	@RequestMapping(value = "/user/voteProposal/{id}", method = RequestMethod.GET)
	public String voteProposal(@PathVariable("id") String id) {
		Proposal proposal = pDao.getProposalById(Integer.parseInt(id));
		pDao.voteProposal(proposal);
		kafkaProducer.send("voteProposal", proposal.kafkaStringFormat());
		return "redirect:/user/home";
	}

	@RequestMapping(value = "/user/voteComment/{id}", method = RequestMethod.GET)
	public String voteComment(@PathVariable("id") String id) {
		Comment comment = cDao.getCommentById(Integer.parseInt(id));
		cDao.voteComment(comment);
		kafkaProducer.send("voteComment", comment.kafkaStringFormat());
		return "redirect:/user/viewProposal/" + comment.getProposalId();
	}

	@RequestMapping("/user/commentProposal/{id}")
	public String commentProposal(Model model, @ModelAttribute AddComment addComment, @PathVariable("id") String id) {
		String content = addComment.getComment();

		Comment comment = new Comment(content, Integer.parseInt(id), user.getId());
		try {
			cDao.createComment(comment);
			kafkaProducer.send("addComment", comment.kafkaStringFormat());
		} catch (Exception e) {
			return "/mensajeError";
		}

		return "redirect:/user/viewProposal/" + comment.getProposalId();
	}

	@RequestMapping("/addCategory")
	public String addCategory(Model model, @ModelAttribute ControlAdmin controlAdmin) {
		Category category = new Category(controlAdmin.getCategory());
		CommonPersistence.getCategoryDao().createCategory(category);
		kafkaProducer.send("addCategory", category.getName());
		return "/admin";
	}

	@RequestMapping("/addNotAllowedWords")
	public String addNotAllowedWords(Model model, @ModelAttribute ControlAdmin controlAdmin) {
		CommonPersistence.getWordDao().add(controlAdmin.getPalabras());
		kafkaProducer.send("addedNotAllowedWords", controlAdmin.getPalabras().toString());
		return "/admin";
	}

	@RequestMapping("/deleteProposal")
	public String deleteProposal(Model model, @ModelAttribute ControlAdmin controlAdmin) {
		Integer id = Integer.parseInt(controlAdmin.getProposal());
		Proposal proposal = CommonPersistence.getProposalDao().getProposalById(id);
		CommonPersistence.getProposalDao().deleteProposalById(proposal.getId());

		model.addAttribute("controlAdmin", new ControlAdmin());
		model.addAttribute("proposals", pDao.getProposals());
		kafkaProducer.send("deleteProposal", proposal.kafkaStringFormat());
		return "/admin";
	}

	@RequestMapping("/mensajeError")
	public String mensajeError(Model model) {
		return "/mensajeError";
	}

	@RequestMapping(value = "/user/orderCommentsPopularity/{id}", method = RequestMethod.GET)
	public String orderCommentsPopularity(Model model, @PathVariable("id") String id) {
		Integer idInt = Integer.parseInt(id);
		Proposal proposal = pDao.getProposalById(idInt);
		List<Comment> commentsList = cDao.getCommentsFromProposalIdOrderedByPopularity(idInt);

		model.addAttribute("proposal", proposal);
		model.addAttribute("commentsList", commentsList);
		model.addAttribute("addComment", new AddComment());

		return "/user/proposal";
	}

	@RequestMapping(value = "/user/orderCommentsDate/{id}", method = RequestMethod.GET)
	public String orderCommentsDate(Model model, @PathVariable("id") String id) {
		Integer idInt = Integer.parseInt(id);
		Proposal proposal = pDao.getProposalById(idInt);
		List<Comment> commentsList = cDao.getCommentsFromProposalIdOrderedByDate(idInt);

		model.addAttribute("proposal", proposal);
		model.addAttribute("commentsList", commentsList);
		model.addAttribute("addComment", new AddComment());

		return "/user/proposal";
	}

	@RequestMapping("/participants/update")
	public String updateInfo(Model model,@ModelAttribute("participant") jpa.model.User participant) {
		repository.save(participant);
		return "/participants/update";
	}

	@RequestMapping("/participants/userInfo")
	public String showData(Model model){
		model.addAttribute("participant",userJpa);
		return "/participants/update";
	}

	@PostMapping("/participants/changePassword")
	public String changePassword(Model model){
		model.addAttribute("participant",repository
				.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
		return "/participants/changePassword";
	}
}