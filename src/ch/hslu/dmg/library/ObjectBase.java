package ch.hslu.dmg.library;

import java.io.Serializable;

/**
 * @author Angelo on 06.05.2014.
 */
public class ObjectBase implements Serializable {
    private int _ID;

    public ObjectBase() {

    }

    public int get_ID() {
        return _ID;
    }

    public void set_ID(int _ID) {
        this._ID = _ID;
    }
}
