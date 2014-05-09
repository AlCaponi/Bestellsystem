package ch.hslu.dmg.service;

import ch.hslu.dmg.Dataaccess.KundeDAO;
import ch.hslu.dmg.Dataaccess.PersonDAO;
import ch.hslu.dmg.Dataaccess.TeilDAO;
import ch.hslu.dmg.library.collection.*;

/**
 * @author Angelo on 08.05.2014.
 */
public class BestellService {
    private PersonDAO personDAO;
    private KundeDAO kundeDAO;
    private TeilDAO teilDAO;


    public BestellService()
    {
        personDAO = new PersonDAO();
        kundeDAO = new KundeDAO();
        teilDAO= new TeilDAO();
    }

    public PersonCol GetAllPersonen(){
        return personDAO.readPersonen();
    }
    public MitarbeiterCol GetAllMitarbeiter() { return new MitarbeiterCol(); }
    public MaschineCol GetAllMaschine(){return new MaschineCol();}
    public FertigungsteilCol GetAllFertigungsteil(){return new FertigungsteilCol();}
    public FertigungsschrittCol GetAllFertigungsschritt() {return new FertigungsschrittCol();}
    public TeilCol GetAllTeil(){return teilDAO.readTeil();}
    public KundeCol GetAllKunde() {return kundeDAO.readKunden();}


}
