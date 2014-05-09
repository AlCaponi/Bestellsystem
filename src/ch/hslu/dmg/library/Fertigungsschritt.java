package ch.hslu.dmg.library;

import java.util.Date;

/**
 * @author Angelo on 06.05.2014.
 */
public class Fertigungsschritt extends ObjectBase {
    private Date _datum;
    private Mitarbeiter _Mitarbeiter;
    private Fertigungsteil _Fertigungsteil;
    private Maschine _Maschine;

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

    public Fertigungsteil get_Fertigungsteil() {
        return _Fertigungsteil;
    }

    public void set_Fertigungsteil(Fertigungsteil _Fertigungsteil) {
        this._Fertigungsteil = _Fertigungsteil;
    }

    public Maschine get_Maschine() {
        return _Maschine;
    }

    public void set_Maschine(Maschine _Maschine) {
        this._Maschine = _Maschine;
    }
}
