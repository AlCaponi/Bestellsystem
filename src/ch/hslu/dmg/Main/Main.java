package ch.hslu.dmg.Main;

import ch.hslu.dmg.Dataaccess.TeilDao;
import ch.hslu.dmg.library.*;

import java.util.Date;

/**
 * @author Angelo on 08.05.2014.
 */
public class Main {

    public static void main(String[] args) {
        TeilDao teilDao = new TeilDao();
        teilDao.readSubTeileWithLevel(1);
        BestellEingang b = new BestellEingang();
        b.setVisible(true);
    }
}
