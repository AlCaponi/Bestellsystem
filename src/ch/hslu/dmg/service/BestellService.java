package ch.hslu.dmg.service;

import ch.hslu.dmg.Dataaccess.*;
import ch.hslu.dmg.library.Maschine;
import ch.hslu.dmg.library.collection.*;

/**
 * @author Angelo on 08.05.2014.
 */
public class BestellService {
    private PersonDAO personDAO;
    private KundeDAO kundeDAO;
    private TeilDAO teilDAO;
    private MitarbeiterDao mitarbeiterDao;
    private MaschineDao maschineDao;


    public BestellService() {
        personDAO = new PersonDAO();
        kundeDAO = new KundeDAO();
        teilDAO = new TeilDAO();
        mitarbeiterDao = new MitarbeiterDao();
        maschineDao = new MaschineDao();
    }

    public PersonCol GetAllPersonen() {
        return personDAO.readPersonen();
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
        return teilDAO.readTeil();
    }

    public KundeCol GetAllKunde() {
        return kundeDAO.readKunden();
    }

    public TeilCol GetSubTeile(int fertigungsTeilID) {
        return teilDAO.readSubtTeile(fertigungsTeilID);
    }

    public MitarbeiterCol GetAllVerfuegbareMitarbeiter() {
        return mitarbeiterDao.readVerfuegbareMitarbeiter();
    }

    public MaschineCol GetAllVerfuegbareMaschinen() {
        MaschineCol verfuegbareMaschinen = maschineDao.readVerfuegbareMaschinen();
        for (Maschine maschine : verfuegbareMaschinen) {
            maschine.set_produzierteTeile(teilDAO.readTeileByMaschineID(maschine.get_ID()));
        }
        return verfuegbareMaschinen;
    }
}
