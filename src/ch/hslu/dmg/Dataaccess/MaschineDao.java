package ch.hslu.dmg.Dataaccess;

import ch.hslu.dmg.library.Maschine;
import ch.hslu.dmg.library.collection.MaschineCol;

import java.util.Date;

/**
 * @author Angelo on 09.05.2014.
 */
public class MaschineDao extends BaseDao {


    private String _SqlReadVerfuegbareMaschinen = "SELECT Maschine.MaschineID AS ID,\n"
            + "	  Maschine.Bezeichnung AS Bezeichnung,\n"
            + "	  Maschine.Wartungsintervall AS Wartungsintervall\n"
            + "  FROM Maschine\n"
            + "  WHERE Maschine.MaschineID NOT IN( \n"
            + "                                    SELECT MaschineID\n"
            + "                                      FROM DMG_Project.dbo.Fertigungsschritt\n"
            + "                                      WHERE datum > '%s'\n"
            + "                                  );";

    public MaschineDao() {
        super();
    }


    public MaschineCol readVerfuegbareMaschinen(Date date) {
        String dateString = String.format("%d.%d.2014", date.getDate(), date.getMonth()+1);
        return (MaschineCol) Database.FillList(new MaschineCol(), Maschine.class, String.format(_SqlReadVerfuegbareMaschinen, dateString));
    }
}
