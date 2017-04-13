package participationSystem.dto;

public class Category {

	private Integer id;
	private String name;

	public Category() {
	}

	public Category(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Category(String string) {
		this.name = string;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Category[Id: " + id + "; Name: " + name+"]";
	}

	@Override
	public boolean equals(Object obj) {
		return this.toString().equals(obj.toString());
	}

}
