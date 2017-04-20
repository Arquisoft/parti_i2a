package citizensLoader.letters;

import java.io.IOException;

import participationSystem.dto.User;

public interface Writtable {

	//interface for all the different output formats
	
	/**
	 * Writes in a file a letter for each citizen,
	 * specifying its own login email and password
	 * @param c - citizen for which the letter will be written
	 * @throws IOException
	 */
	void write(User c) throws IOException;

}
