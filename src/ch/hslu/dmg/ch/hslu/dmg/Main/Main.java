package ch.hslu.dmg.ch.hslu.dmg.Main;

import ch.hslu.dmg.library.Fertigungsschritt;
import ch.hslu.dmg.library.Fertigungsteil;
import ch.hslu.dmg.library.Maschine;
import ch.hslu.dmg.library.Mitarbeiter;

import java.util.Date;

/**
 * @author Angelo on 08.05.2014.
 */
public class Main {

    public static void main(String[] args) {

        Fertigungsschritt fertigungsschritt = new Fertigungsschritt();
        Maschine maschine = new Maschine();
        Mitarbeiter mitarbeiter = new Mitarbeiter();
        Fertigungsteil fertigungsteil = new Fertigungsteil();

        maschine.set_ID(1);
        mitarbeiter.set_ID(2);
        fertigungsteil.set_ID(1);

        fertigungsschritt.set_Maschine(maschine);
        fertigungsschritt.set_Mitarbeiter(mitarbeiter);
        fertigungsschritt.set_Fertigungsteil(fertigungsteil);
        fertigungsschritt.set_datum(new Date());

        BestellBusiness.getInstance().saveFertigungsschritt(fertigungsschritt, 2);

        BestellEingang b = new BestellEingang();
        b.setVisible(true);
    }
}
