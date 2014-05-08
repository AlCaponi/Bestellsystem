package ch.hslu.dmg.library;

/**
 * @author Angelo on 06.05.2014.
 */
public class Bankverbindung extends ObjectBase {
    private String _IBAN;
    private String _BankName;

    public String get_IBAN() {
        return _IBAN;
    }

    public void set_IBAN(String _IBAN) {
        this._IBAN = _IBAN;
    }

    public String get_BankName() {
        return _BankName;
    }

    public void set_BankName(String _BankName) {
        this._BankName = _BankName;
    }
}
