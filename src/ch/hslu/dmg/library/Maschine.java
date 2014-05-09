package ch.hslu.dmg.library;

import ch.hslu.dmg.library.collection.TeilCol;

/**
 * @author Angelo on 06.05.2014.
 */
public class Maschine extends ObjectBase {
    private String _Bezeichnung;
    private int _Wartungsintervall;
    private TeilCol _produzierteTeile;

    public String get_Bezeichnung() {
        return _Bezeichnung;
    }

    public void set_Bezeichnung(String _Bezeichnung) {
        this._Bezeichnung = _Bezeichnung;
    }

    public int get_Wartungsintervall() {
        return _Wartungsintervall;
    }

    public void set_Wartungsintervall(int _Wartungsintervall) {
        this._Wartungsintervall = _Wartungsintervall;
    }

    public TeilCol get_produzierteTeile() {
        return _produzierteTeile;
    }

    public void set_produzierteTeile(TeilCol _produzierteTeile) {
        this._produzierteTeile = _produzierteTeile;
    }
}
