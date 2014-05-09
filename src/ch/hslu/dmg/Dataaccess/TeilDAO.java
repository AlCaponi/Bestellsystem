package ch.hslu.dmg.Dataaccess;

import ch.hslu.dmg.library.Teil;
import ch.hslu.dmg.library.collection.TeilCol;

/**
 * Created by Dave on 09.05.2014.
 */
public class TeilDAO extends BaseDao {


    private  String sqlReadSubTeilCol = "SELECT Teil.TeilNr AS ID , \n"
            + "       Teil.Bezeichnung AS Bezeichnung , \n"
            + "       Teil.Grösse AS Groesse , \n"
            + "       Teil.Preis AS Preis , \n"
            + "       Teil.IsFertigungsteil AS IsFertigungsteil\n"
            + "FROM Teil\n"
            + "    INNER JOIN Teil_besteht_aus \n"
            + "    ON Teil_besteht_aus.UnterteilNr = Teil.TeilNr\n"
            + "WHERE Teil_besteht_aus.TeilNr = %d";

    private String sqlReadTeileByMaschineId = "";

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

    public TeilCol readSubtTeile(int fertigungsTeilID) {
        return (TeilCol)Database.FillList(new TeilCol(), Teil.class, String.format(sqlReadSubTeilCol, fertigungsTeilID));
    }

    public TeilCol readTeileByMaschineID(int maschine_ID) {
        return (TeilCol)Database.FillList(new TeilCol(), Teil.class, String.format(sqlReadTeileByMaschineId, maschine_ID));
    }
}
