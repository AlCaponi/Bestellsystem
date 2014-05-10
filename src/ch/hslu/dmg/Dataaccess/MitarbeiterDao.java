package ch.hslu.dmg.Dataaccess;

import ch.hslu.dmg.library.Mitarbeiter;
import ch.hslu.dmg.library.collection.MitarbeiterCol;

import java.util.Date;

/**
 * @author Angelo on 09.05.2014.
 */
public class MitarbeiterDao extends BaseDao {

    String _SqlReadVerfuegbareMitarbeiter = "SELECT Mitarbeiter.ID\n"
            + "  FROM Mitarbeiter\n"
            + "  WHERE Mitarbeiter.ID NOT IN( \n"
            + "                               SELECT MitarbeiterID\n"
            + "                                 FROM DMG_Project.dbo.Fertigungsschritt\n"
            + "                                 WHERE datum = '%s'\n"
            + "                             );";

    public MitarbeiterDao(){
        super();
    }

    public MitarbeiterCol readVerfuegbareMitarbeiter(Date date) {
        String dateString = String.format("%d.%d.%s", date.getDate(), date.getMonth() + 1, "2014");
        return (MitarbeiterCol)Database.FillList(new MitarbeiterCol(), Mitarbeiter.class, String.format(this._SqlReadVerfuegbareMitarbeiter, dateString));
    }

}
