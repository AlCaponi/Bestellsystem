package ch.hslu.dmg.library;

/**
 * @author Angelo on 06.05.2014.
 */
public class Kunde extends ObjectBase{
    private Person _Person;

    public Person get_Person() {
        return _Person;
    }

    public void set_Person(Person _Person) {
        this._Person = _Person;
    }

    @Override public String toString()
    {
        return _Person.get_Name();
    }
}
