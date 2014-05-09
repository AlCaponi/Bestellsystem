package ch.hslu.dmg.service;

import ch.hslu.dmg.Dataaccess.PersonDAO;
import ch.hslu.dmg.library.collection.*;

/**
 * @author Angelo on 08.05.2014.
 */
public class BestellService {
    private PersonDAO personDAO;

    public BestellService()
    {
        personDAO = new PersonDAO();
    }

    public PersonCol GetAllPersonen(){
        return personDAO.readPersonen();
    }
    public MitarbeiterCol GetAllMitarbeiter() { return new MitarbeiterCol(); }
    public MaschineCol GetAllMaschine(){return new MaschineCol();}
    public FertigungsteilCol GetAllFertigungsteil(){return new FertigungsteilCol();}
    public FertigungsschrittCol GetAllFertigungsschritt() {return new FertigungsschrittCol();}
    public TeilCol GetAllTeil(){return new TeilCol();}


}
