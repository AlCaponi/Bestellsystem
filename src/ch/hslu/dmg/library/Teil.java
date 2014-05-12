package ch.hslu.dmg.library;

import java.io.Serializable;

/**
 * @author Angelo on 06.05.2014.
 */
public class Teil extends ObjectBase implements Serializable {
    private String _Bezeichnung;
    private float _Groesse;
    private float _Preis;
    private Boolean _IsFertigungsteil;
    private int _TreeLevel;


    public String get_Bezeichnung() {
        return _Bezeichnung;
    }

    public void set_Bezeichnung(String _Bezeichnung) {
        this._Bezeichnung = _Bezeichnung;
    }

    public float get_Groesse() {
        return _Groesse;
    }

    public void set_Groesse(float _Groesse) {
        this._Groesse = _Groesse;
    }

    public float get_Preis() {
        return _Preis;
    }

    public void set_Preis(float _Preis) {
        this._Preis = _Preis;
    }

    public Boolean get_IsFertigungsteil() {
        return _IsFertigungsteil;
    }

    public void set_IsFertigungsteil(Boolean _IsFertigungsteil) {
        this._IsFertigungsteil = _IsFertigungsteil;
    }

    public int get_TreeLevel(){return _TreeLevel;}

    public void set_TreeLevel(int treeLevel){ _TreeLevel = treeLevel;}

    @Override
    public String toString() {
        return String.format("%s", this.get_Bezeichnung());
    }
}
