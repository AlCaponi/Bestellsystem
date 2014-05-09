package ch.hslu.dmg.library;

import java.util.Date;

/**
 * @author Angelo on 06.05.2014.
 */
public class Mitarbeiter extends ObjectBase{
    private Person _Person;
    private float _Gehalt;
    private Date _Geburtstag;

    public float get_Gehalt() {
        return _Gehalt;
    }

    public void set_Gehalt(float _Gehalt) {
        this._Gehalt = _Gehalt;
    }

    public Date get_Geburtstag() {
        return _Geburtstag;
    }

    public void set_Geburtstag(Date _Geburtstag) {
        this._Geburtstag = _Geburtstag;
    }

    public Person get_Person() {
        return _Person;
    }

    public void set_Person(Person _Person) {
        this._Person = _Person;
    }
}
