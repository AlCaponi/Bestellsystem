package ch.hslu.dmg.Main;

import ch.hslu.dmg.library.*;
import ch.hslu.dmg.library.collection.KundeCol;
import ch.hslu.dmg.library.collection.MaschineCol;
import ch.hslu.dmg.library.collection.MitarbeiterCol;
import ch.hslu.dmg.library.collection.TeilCol;
import ch.hslu.dmg.service.BestellService;

import javax.swing.*;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

    public Date bestellen(Teil bestellTeil, int anzahl, Kunde kunde) {
        // Bestellung erstellen
        Bestellung bestellung = new Bestellung();
        bestellung.set_Teil(bestellTeil);
        bestellung.set_Anzahl(anzahl);
        bestellung.set_Kunde(kunde);
        int bestellNummer =  _bestellService.SaveBestellung(bestellung);

        Date shippingDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(shippingDate);

        //Ist es ein Fertigungsteil?
        if (bestellTeil.get_IsFertigungsteil()) {
            // Collection mit mehrfachnennung von Teilen (Anzahl) und TreeLevel lesen
            TeilCol alleTeile = _bestellService.GetSubTeilWithLevel(bestellTeil.get_ID());
            HashMap<Integer, TeilCol> tree = new HashMap<Integer, TeilCol>();
            TeilCol tc = new TeilCol();
            tc.add(bestellTeil);
            tree.put(1,tc);

            for(Teil unterteil : alleTeile)
            {
                if(!tree.containsKey(unterteil.get_TreeLevel())){
                    tree.put(unterteil.get_TreeLevel(),new TeilCol());
                }

                tree.get(unterteil.get_TreeLevel()).add(unterteil);
            }


            for(int i =tree.size();i<=1;i--) {
                TeilCol prodUnterTeilAll = tree.get(i);
                TeilCol prodUnterTeilPending = tree.get(i);

                while(prodUnterTeilPending.size()!=0) {
                    prodUnterTeilAll.clear();
                    prodUnterTeilAll.addAll(prodUnterTeilPending);
                    
                    calendar.add(Calendar.DATE,1);
                    shippingDate=calendar.getTime();

                    // TODO: verfügbare mitarbeiter von shippingdate lesen
                    MitarbeiterCol verfuegbareMitarbeiter = _bestellService.GetAllVerfuegbareMitarbeiter(shippingDate);
                    //MitarbeiterCol verfuegbareMitarbeiter = _bestellService.GetAllVerfuegbareMitarbeiter();
                    // TODO: verfügbare Maschinen von shippingdate lesen
                     MaschineCol maschineCol = _bestellService.GetAllVerfuegbareMaschinen(shippingDate);
                    //MaschineCol maschineCol = _bestellService.GetAllVerfuegbareMaschinen();
                    //int arbeitstage = alleTeile.size();
                    //int mitarbeiter = verfuegbareMitarbeiter.size();
                    //int maschineCount = 0;
                    if(maschineCol.size()==0 || verfuegbareMitarbeiter.size()==0){
                        continue;
                    }
                    for (Maschine maschine : maschineCol) {
                        for (Teil fertigungsTeil : prodUnterTeilAll) {
                            for (Teil teil : maschine.get_produzierteTeile()) {
                                if (teil.get_ID() == fertigungsTeil.get_ID() && verfuegbareMitarbeiter.size() > 0) {
                                    // write Fertigungsschritt
                                    Fertigungsschritt fertigungsschritt = new Fertigungsschritt();
                                    fertigungsschritt.set_datum(shippingDate);
                                    Fertigungsteil fertigungsteil = new Fertigungsteil();
                                    fertigungsteil.set_Teil(fertigungsTeil);
                                    fertigungsschritt.set_Fertigungsteil(fertigungsteil);
                                    fertigungsschritt.set_Maschine(maschine);
                                    fertigungsschritt.set_Mitarbeiter(verfuegbareMitarbeiter.get(verfuegbareMitarbeiter.size() - 1));
                                    verfuegbareMitarbeiter.remove(verfuegbareMitarbeiter.size() - 1);
                                    _bestellService.SaveFertigungsschritt(fertigungsschritt, bestellNummer);
                                    prodUnterTeilPending.remove(fertigungsTeil);

                                }
                            }
                        }
                    }
                }
            }

        }
        calendar.add(Calendar.DATE,1);
        shippingDate=calendar.getTime();
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

    public int saveBestellung(Bestellung bestellung){
        return _bestellService.SaveBestellung(bestellung);
    }

    public void saveFertigungsschritt(Fertigungsschritt fertigungsschritt, int bestellNr){
        _bestellService.SaveFertigungsschritt(fertigungsschritt, bestellNr);
    }


}
