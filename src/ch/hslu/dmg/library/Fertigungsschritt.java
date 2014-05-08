package ch.hslu.dmg.library;

import java.util.Date;

/**
 * @author Angelo on 06.05.2014.
 */
public class Fertigungsschritt extends ObjectBase {
    private Date _datum;
    private Mitarbeiter _Mitarbeiter;
    private Fertigungsplan _Fertigungsplan;
    private Fertigungsteil _Fertigungsteil;

    public Date get_datum() {
        return _datum;
    }

    public void set_datum(Date _datum) {
        this._datum = _datum;
    }

    public Mitarbeiter get_Mitarbeiter() {
        return _Mitarbeiter;
    }

    public void set_Mitarbeiter(Mitarbeiter _Mitarbeiter) {
        this._Mitarbeiter = _Mitarbeiter;
    }

    public Fertigungsplan get_Fertigungsplan() {
        return _Fertigungsplan;
    }

    public void set_Fertigungsplan(Fertigungsplan _Fertigungsplan) {
        this._Fertigungsplan = _Fertigungsplan;
    }

    public Fertigungsteil get_Fertigungsteil() {
        return _Fertigungsteil;
    }

    public void set_Fertigungsteil(Fertigungsteil _Fertigungsteil) {
        this._Fertigungsteil = _Fertigungsteil;
    }
}
