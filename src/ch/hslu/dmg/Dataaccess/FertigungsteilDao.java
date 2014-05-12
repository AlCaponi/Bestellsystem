package ch.hslu.dmg.Dataaccess;

import ch.hslu.dmg.library.Fertigungsteil;
import ch.hslu.dmg.library.collection.FertigungsteilCol;

/**
 * Created by Dave on 12.05.2014.
 */
public class FertigungsteilDao extends BaseDao {

    public FertigungsteilDao(){
        super();
    }

    private String sqlReadFertigungsteilByTeilID="SELECT [FertigungsteilID] AS ID\n"
            + "        ,[TeilNr] AS [Teil.ID]\n\n"
            + "        ,[MaschineID] AS [Maschine.ID]\n"
            + "        FROM [DMG_Project_Test].[dbo].[Fertigungsteil]\n"
            + "     WHERE [dbo].[Fertigungsteil].[TeilNr] =%d\n";

    public FertigungsteilCol readVerfuegbareMitarbeiter(int teilID) {
        return (FertigungsteilCol) Database.FillList(new FertigungsteilCol(), Fertigungsteil.class, String.format(this.sqlReadFertigungsteilByTeilID, teilID));
    }
}
