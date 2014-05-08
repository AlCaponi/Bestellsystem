package ch.hslu.dmg.library;

/**
 * @author Angelo on 06.05.2014.
 */
public class Bestellung extends ObjectBase {
    private int _Anzahl;
    private Teil _Teil;
    private Fertigungsplan _Feritungsplan;
    private Kunde _Kunde;

    public int get_Anzahl() {
        return _Anzahl;
    }

    public void set_Anzahl(int _Anzahl) {
        this._Anzahl = _Anzahl;
    }

    public Teil get_Teil() {
        return _Teil;
    }

    public void set_Teil(Teil _Teil) {
        this._Teil = _Teil;
    }

    public Fertigungsplan get_Feritungsplan() {
        return _Feritungsplan;
    }

    public void set_Feritungsplan(Fertigungsplan _Feritungsplan) {
        this._Feritungsplan = _Feritungsplan;
    }

    public Kunde get_Kunde() {
        return _Kunde;
    }

    public void set_Kunde(Kunde _Kunde) {
        this._Kunde = _Kunde;
    }
}
