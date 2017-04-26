package citizensLoader.letters;

import java.io.IOException;

import common.dto.User;

public class SendLetters {
	
	/**
	 * This method is a greater one which calls the method that prints a letter
	 * in the format you specify as a parameter.
	 * Prints a message informing which citizen has had its letter printed
	 * @param type of the letter (pdf, word, txt)
	 * @param c (citizen) which receives the letter and has its data printed
	 * @throws IOException 
	 */
	public static void send(User c, Writtable letter) throws IOException {
		letter.write(c);
		System.out.println("La carta para " + c.getFirstName() + " " + c.getLastName()+ " ha sido generada");
	}

}
