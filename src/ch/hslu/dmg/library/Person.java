package ch.hslu.dmg.library;

/**
 * @author Angelo on 06.05.2014.
 */
public class Person extends ObjectBase {
    private String _Name;
    private Adresse _Adresse;
    private Bankverbindung _Bankverbindung;

    public String get_Name() {
        return _Name;
    }

    public void set_Name(String _Name) {
        this._Name = _Name;
    }

    public Adresse get_Adresse() {
        return _Adresse;
    }

    public void set_Adresse(Adresse _Adresse) {
        this._Adresse = _Adresse;
    }

    public Bankverbindung get_Bankverbindung() {
        return _Bankverbindung;
    }

    public void set_Bankverbindung(Bankverbindung _Bankverbindung) {
        this._Bankverbindung = _Bankverbindung;
    }
}

