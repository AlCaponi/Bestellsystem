package ch.hslu.dmg.Dataaccess;

import ch.hslu.dmg.library.Teil;
import ch.hslu.dmg.library.collection.TeilCol;

/**
 * Created by Dave on 09.05.2014.
 */
public class TeilDao extends BaseDao {


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

    public TeilDao()
    {
        super();
    }


    String sqlReadTeile  = "SELECT  TeilNr AS [ID]\n"
            + "      ,Bezeichnung       AS [Bezeichnung]\n"
            + "      ,Grösse    AS [Groesse]\n"
            + "      ,Preis    AS [Preis]\n"
            + "      ,IsFertigungsteil    AS [IsFertigungsteil]\n"
            + "  FROM [dbo].[Teil]";

    private String sqlReadSubTeileWithLevel = "\n"
            + "WITH cte( TeilNr , \n"
            + "          Anzahl , \n"
            + "          UnterTeilNr , \n"
            + "          TreeLevel\n"
            + "        )\n"
            + "    AS ( SELECT TeilNr , \n"
            + "                Anzahl , \n"
            + "                UnterteilNr , \n"
            + "                1\n"
            + "           FROM Teil_besteht_aus\n"
            + "           WHERE TeilNr = %d\n"
            + "         UNION ALL\n"
            + "         SELECT t.TeilNr , \n"
            + "                t.Anzahl , \n"
            + "                t.UnterteilNr , \n"
            + "                c.TreeLevel + 1\n"
            + "           FROM\n"
            + "                Teil_besteht_aus t INNER JOIN cte c\n"
            + "                                   ON c.UnterTeilNr = t.TeilNr\n"
            + "       )\n"
            + "    SELECT ct.UnterTeilNr AS TeilNr , \n"
            + "           Teil.Bezeichnung , \n"
            + "           Teil.Grösse AS [Teil.Groesse] , \n"
            + "           Teil.IsFertigungsteil , \n"
            + "           Teil.Preis , \n"
            + "           ct.TreeLevel + 1 AS TreeLevel\n"
            + "      FROM\n"
            + "           cte ct CROSS JOIN dbo.Tally ta INNER JOIN Teil\n"
            + "                                          ON Teil.TeilNr = ct.UnterTeilNr\n"
            + "      WHERE ta.Num <= ct.Anzahl AND ct.UnterTeilNr IN( \n"
            + "                                                       SELECT ct2.TeilNr\n"
            + "                                                         FROM cte ct2\n"
            + "                                                     )\n"
            + "      ORDER BY TreeLevel , TeilNr;";

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

    public TeilCol readSubTeileWithLevel(int fertigungsTeilID){
        return (TeilCol)Database.FillList(new TeilCol(),Teil.class, String.format(sqlReadSubTeileWithLevel, fertigungsTeilID));
    }
}
