package ch.hslu.dmg.ch.hslu.dmg.Main;

import ch.hslu.dmg.Dataaccess.AdresseDAO;
import ch.hslu.dmg.Dataaccess.Database;
import ch.hslu.dmg.Dataaccess.PersonDAO;

/**
 * @author Angelo on 08.05.2014.
 */
public class Main {

    public static void main(String[] args) {

        //AdresseDAO adresseDAO = new AdresseDAO();
        //adresseDAO.readAdresse(1);
        BestellEingang b = new BestellEingang();
        b.setVisible(true);
    }
}
