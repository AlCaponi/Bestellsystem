package ch.hslu.dmg.library;

/**
 * @author Angelo on 06.05.2014.
 */
public class Fertigungsteil extends ObjectBase {
    private Teil _Teil;
    private Maschine _Maschine;

    public Maschine get_Maschine() {
        return _Maschine;
    }

    public void set_Maschine(Maschine _Maschine) {
        this._Maschine = _Maschine;
    }

    public Teil get_Teil() {
        return _Teil;
    }

    public void set_Teil(Teil _Teil) {
        this._Teil = _Teil;
    }
}
