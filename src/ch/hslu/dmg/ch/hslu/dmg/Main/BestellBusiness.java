package ch.hslu.dmg.ch.hslu.dmg.Main;

import ch.hslu.dmg.library.collection.KundeCol;
import ch.hslu.dmg.library.collection.TeilCol;
import ch.hslu.dmg.service.BestellService;

/**
 * Created by Dave on 09.05.2014.
 */
public class BestellBusiness {

    private BestellService _bestellService= new BestellService();
    private static BestellBusiness _instance;

    public static BestellBusiness getInstance()
    {
        if(_instance == null)
           _instance = new BestellBusiness();
        return _instance;
    }

    private BestellBusiness()
    {}

    public KundeCol getAllKundeCol()
    {
        return _bestellService.GetAllKunde();
    }

    public TeilCol getAllTeilCol()
    {
        return _bestellService.GetAllTeil();
    }

}
