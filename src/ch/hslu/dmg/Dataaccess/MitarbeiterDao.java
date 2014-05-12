package ch.hslu.dmg.Dataaccess;

import ch.hslu.dmg.library.Mitarbeiter;
import ch.hslu.dmg.library.collection.MitarbeiterCol;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Angelo on 09.05.2014.
 */
public class MitarbeiterDao extends BaseDao {

    String _SqlReadVerfuegbareMitarbeiter = "SELECT Mitarbeiter.ID\n"
            + "  FROM Mitarbeiter\n"
            + "  WHERE Mitarbeiter.ID NOT IN( \n"
            + "                               SELECT MitarbeiterID\n"
            + "                                 FROM [dbo].[Fertigungsschritt]\n"
            + "                                 WHERE datum = '%s'\n"
            + "                             );";

    public MitarbeiterDao(){
        super();
    }

    public MitarbeiterCol readVerfuegbareMitarbeiter(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String dateString = sdf.format(date);

        //String dateString = String.format("%d-%d-%d",calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE) );
        //String dateString = String.format("%s-%d-%d","2014", date.getMonth() + 1, date.getDate() );
        return (MitarbeiterCol)Database.FillList(new MitarbeiterCol(), Mitarbeiter.class, String.format(this._SqlReadVerfuegbareMitarbeiter, dateString));
    }

}
