package ch.hslu.dmg.Dataaccess;

import ch.hslu.dmg.library.Maschine;
import ch.hslu.dmg.library.collection.MaschineCol;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
            + "                                      FROM [dbo].[Fertigungsschritt]\n"
            + "                                      WHERE datum = '%s'\n"
            + "                                  );";

    public MaschineDao() {
        super();
    }


    public MaschineCol readVerfuegbareMaschinen(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String dateString = sdf.format(date);

        //String dateString = String.format("%d-%d-%d", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
        //String dateString = String.format("2014-%d-%d", date.getMonth()+1, date.getDate());
        return (MaschineCol) Database.FillList(new MaschineCol(), Maschine.class, String.format(_SqlReadVerfuegbareMaschinen, dateString));
    }
}
