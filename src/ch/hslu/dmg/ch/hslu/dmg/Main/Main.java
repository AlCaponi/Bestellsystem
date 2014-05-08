package ch.hslu.dmg.ch.hslu.dmg.Main;

import ch.hslu.dmg.Dataaccess.Database;
import ch.hslu.dmg.Dataaccess.PersonDAO;

/**
 * @author Angelo on 08.05.2014.
 */
public class Main {

    public static void main(String[] args) {
//        Database database = new Database("");
//        database.connect();
        PersonDAO personDAO = new PersonDAO();
        personDAO.readPerson(1);

    }
}
