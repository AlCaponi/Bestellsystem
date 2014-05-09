package ch.hslu.dmg.Dataaccess;

import ch.hslu.dmg.library.Kunde;
import ch.hslu.dmg.library.collection.KundeCol;

/**
 * Created by Dave on 09.05.2014.
 */
public class KundeDAO extends BaseDao {
    public KundeDAO()
    {
        super();
    }

    String sqlReadKunden  = "SELECT  Kunde.ID\n"
            + "      ,Person.ID        AS [Person.ID]\n"
            + "      ,Person.Name   AS [Person.Name]\n"
            + "  FROM [dbo].[Kunde]\n"
            + "    INNER JOIN Person \n"
            + "ON Kunde.ID = Person.ID";

    public final KundeCol readKunden ()
    {
        return (KundeCol) Database.FillList(new KundeCol(), Kunde.class, String.format(sqlReadKunden));
    }


}
