package participationSystem.hello.model;

import java.util.ArrayList;
import java.util.List;


public class ControlAdmin {
	
	private String text;
	private List<String> palabras = new ArrayList<>();
	private String category;
	private String proposal;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		String[] p = text.split(",");
		for(int i = 0; i < p.length; i++) {
			palabras.add(p[i]);
		}
		
		this.text = text;
	}
	public List<String> getPalabras() {
		return palabras;
	}
	public void setPalabras(List<String> palabras) {
		this.palabras = palabras;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getProposal() {
		return proposal;
	}
	public void setProposal(String proposal) {
		this.proposal = proposal;
	}
	
	

}
