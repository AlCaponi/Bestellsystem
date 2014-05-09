package ch.hslu.dmg.service;

import ch.hslu.dmg.Dataaccess.*;
import ch.hslu.dmg.library.Fertigungsschritt;
import ch.hslu.dmg.library.Maschine;
import ch.hslu.dmg.library.collection.*;

/**
 * @author Angelo on 08.05.2014.
 */
public class BestellService {
    private PersonDao personDao;
    private KundeDao kundeDao;
    private TeilDao teilDao;
    private MitarbeiterDao mitarbeiterDao;
    private MaschineDao maschineDao;
    private FertigungsschrittDao fertigungsschrittDao;


    public BestellService() {
        personDao = new PersonDao();
        kundeDao = new KundeDao();
        teilDao = new TeilDao();
        mitarbeiterDao = new MitarbeiterDao();
        maschineDao = new MaschineDao();
        fertigungsschrittDao = new FertigungsschrittDao();
    }

    public PersonCol GetAllPersonen() {
        return personDao.readPersonen();
    }

    public MitarbeiterCol GetAllMitarbeiter() {
        return new MitarbeiterCol();
    }

    public MaschineCol GetAllMaschine() {
        return new MaschineCol();
    }

    public FertigungsteilCol GetAllFertigungsteil() {
        return new FertigungsteilCol();
    }

    public FertigungsschrittCol GetAllFertigungsschritt() {
        return new FertigungsschrittCol();
    }

    public TeilCol GetAllTeil() {
        return teilDao.readTeil();
    }

    public KundeCol GetAllKunde() {
        return kundeDao.readKunden();
    }

    public TeilCol GetSubTeile(int fertigungsTeilID) {
        return teilDao.readSubtTeile(fertigungsTeilID);
    }

    public MitarbeiterCol GetAllVerfuegbareMitarbeiter() {
        return mitarbeiterDao.readVerfuegbareMitarbeiter();
    }

    public MaschineCol GetAllVerfuegbareMaschinen() {
        MaschineCol verfuegbareMaschinen = maschineDao.readVerfuegbareMaschinen();
        for (Maschine maschine : verfuegbareMaschinen) {
            maschine.set_produzierteTeile(teilDao.readTeileByMaschineID(maschine.get_ID()));
        }
        return verfuegbareMaschinen;
    }

    public void SaveFertigungsschritt(Fertigungsschritt fertigungsschritt) {
        fertigungsschrittDao.Save(fertigungsschritt);
    }
}
