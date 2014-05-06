package ch.hslu.dmg.library;

/**
 * @author Angelo on 06.05.2014.
 */
public class Adresse extends ObjectBase {
    private String _Ort;
    private int _Postleitzahl;
    private String _Strasse;

    public String get_Strasse() {
        return _Strasse;
    }

    public void set_Strasse(String _Strasse) {
        this._Strasse = _Strasse;
    }

    public int get_Postleitzahl() {
        return _Postleitzahl;
    }

    public void set_Postleitzahl(int _Postleitzahl) {
        this._Postleitzahl = _Postleitzahl;
    }

    public String get_Ort() {
        return _Ort;
    }

    public void set_Ort(String _Ort) {
        this._Ort = _Ort;
    }
}
