package ch.hslu.dmg.ch.hslu.dmg.Main;

import ch.hslu.dmg.library.Maschine;
import ch.hslu.dmg.library.Teil;
import ch.hslu.dmg.library.collection.KundeCol;
import ch.hslu.dmg.library.collection.MaschineCol;
import ch.hslu.dmg.library.collection.MitarbeiterCol;
import ch.hslu.dmg.library.collection.TeilCol;
import ch.hslu.dmg.service.BestellService;

import java.util.Date;

/**
 * Created by Dave on 09.05.2014.
 */
public class BestellBusiness {

    private static BestellBusiness _instance;
    private BestellService _bestellService = new BestellService();
    private TeilCol _teilCol;

    private BestellBusiness() {
    }

    public static BestellBusiness getInstance() {
        if (_instance == null)
            _instance = new BestellBusiness();
        return _instance;
    }

    public KundeCol getAllKundeCol() {
        return _bestellService.GetAllKunde();
    }

    public TeilCol getAllTeilCol() {
        if (_teilCol == null) {
            _teilCol = _bestellService.GetAllTeil();
        }
        return _teilCol;
    }

    public Date bestellen(Teil bestellTeil, int anzahl) {
        Date shippingDate = new Date();
        //Ist es ein Fertigungsteil?
        if (bestellTeil.get_IsFertigungsteil()) {
            TeilCol alleTeile = getAllFertigungsTeile(bestellTeil);
            MitarbeiterCol verfuegbareMitarbeiter = _bestellService.GetAllVerfuegbareMitarbeiter();
            MaschineCol maschineCol = _bestellService.GetAllVerfuegbareMaschinen();
            int arbeitstage = alleTeile.size();
            int mitarbeiter = verfuegbareMitarbeiter.size();
            int maschineCount = 0;
            for (Maschine maschine : maschineCol) {
                for (Teil fertigungsTeil : alleTeile) {
                    for (Teil teil : maschine.get_produzierteTeile()) {
                        if (teil.get_ID() == fertigungsTeil.get_ID()) {
                            maschineCount++;
                        }
                    }
                }
            }

        }
        return shippingDate;
    }

    public TeilCol getAllFertigungsTeile(Teil fertigungsTeil) {

        TeilCol fertigungsTeile = new TeilCol();
        TeilCol subTeile = _bestellService.GetSubTeile(fertigungsTeil.get_ID());
        for (Teil subTeil : subTeile) {
            if (subTeil.get_IsFertigungsteil()) {
                fertigungsTeile.addAll(getAllFertigungsTeile(subTeil));
            }
        }
        return fertigungsTeile;
    }


}
