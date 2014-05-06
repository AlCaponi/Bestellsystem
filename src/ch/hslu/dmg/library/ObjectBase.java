package ch.hslu.dmg.library;

import java.util.UUID;

/**
 * @author Angelo on 06.05.2014.
 */
public class ObjectBase {
    private UUID _ID;

    public UUID getID() {
        return _ID;
    }

    public void setID(UUID _ID) {
        this._ID = _ID;
    }

    public ObjectBase()
    {
        this._ID = UUID.randomUUID();
    }
}
