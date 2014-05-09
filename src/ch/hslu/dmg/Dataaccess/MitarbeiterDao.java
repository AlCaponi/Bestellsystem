package ch.hslu.dmg.Dataaccess;

import ch.hslu.dmg.library.Mitarbeiter;
import ch.hslu.dmg.library.collection.MitarbeiterCol;

/**
 * @author Angelo on 09.05.2014.
 */
public class MitarbeiterDao extends BaseDao {

    String _SqlReadVerfuegbareMitarbeiter = "SELECT Mitarbeiter.ID\n"
            + "  FROM Mitarbeiter\n"
            + "  WHERE Mitarbeiter.ID NOT IN( \n"
            + "                               SELECT MitarbeiterID\n"
            + "                                 FROM DMG_Project.dbo.Fertigungsschritt\n"
            + "                                 WHERE datum > GETDATE(\n"
            + "                                                      )\n"
            + "                             );";

    public MitarbeiterDao(){
        super();
    }

    public MitarbeiterCol readVerfuegbareMitarbeiter() {
        return (MitarbeiterCol)Database.FillList(new MitarbeiterCol(), Mitarbeiter.class, this._SqlReadVerfuegbareMitarbeiter);
    }

}
