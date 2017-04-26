package citizensLoader.parser;

import java.util.List;

import common.dto.User;

/**
 * Interface for all Parser objects. 
 * Expresses functionality to read a file and 
 * return a list of citizens.
 */
public interface Parser {
    List<User> readList();
}
