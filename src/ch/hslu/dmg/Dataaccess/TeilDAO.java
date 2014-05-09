package ch.hslu.dmg.Dataaccess;

import ch.hslu.dmg.library.Teil;
import ch.hslu.dmg.library.collection.TeilCol;

/**
 * Created by Dave on 09.05.2014.
 */
public class TeilDAO extends BaseDao {
    public TeilDAO()
    {
        super();
    }


    String sqlReadTeile  = "SELECT  TeilNr AS [ID]\n"
            + "      ,Bezeichnung       AS [Bezeichnung]\n"
            + "      ,Grösse    AS [Groesse]\n"
            + "      ,Preis    AS [Preis]\n"
            + "      ,IsFertigungsteil    AS [IsFertigungsteil]\n"
            + "  FROM [dbo].[Teil]";


    public final TeilCol readTeil ()
    {
        return (TeilCol) Database.FillList(new TeilCol(), Teil.class, String.format(sqlReadTeile));
    }
}
