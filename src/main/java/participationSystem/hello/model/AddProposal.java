package participationSystem.hello.model;

import participationSystem.dto.Category;

public class AddProposal {

	private Category category;
	private String text;

	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Override
	public String toString() {
		return "AddProposal [category=" + category + ", text=" + text + "]";
	}
	
	
	
}
