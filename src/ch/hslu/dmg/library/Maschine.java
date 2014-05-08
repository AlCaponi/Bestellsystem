package ch.hslu.dmg.library;

/**
 * @author Angelo on 06.05.2014.
 */
public class Maschine extends ObjectBase {
    private String _Bezeichnung;
    private int _Wartungsintervall;

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
}
