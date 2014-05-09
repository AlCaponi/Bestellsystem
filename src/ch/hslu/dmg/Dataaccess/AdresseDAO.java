package ch.hslu.dmg.Dataaccess;

import ch.hslu.dmg.library.Adresse;

/**
 * @author Angelo on 08.05.2014.
 */
public class AdresseDao extends BaseDao {

    String sqlReadAdresse  = "SELECT   [AdresseID]      AS ID\n"
                             + "      ,[Ort]            AS Ort\n"
                             + "      ,[Postleitzahl]   AS Postleitzahl\n"
                             + "      ,[Strasse]        AS Strasse\n"
                             + "  FROM [dbo].[Adresse]\n"
                             + "  WHERE AdresseID = %d";

    String sqlSaveAdresse  = "SELECT   [AdresseID]      AS [Adresse.ID]\n"
                             + "      ,[Ort]            AS [Adresse.Ort]\n"
                             + "      ,[Postleitzahl]   AS [Adresse.Postleitzahl]\n"
                             + "      ,[Strasse]        AS [Adresse.Strasse]\n"
                             + "  FROM [dbo].[Adresse]\n";

    public AdresseDao(){
        super();
    }

    public Adresse readAdresse(int adresseId){
        return (Adresse) Database.FillObject(new Adresse(), String.format(sqlReadAdresse, adresseId));
    }

    public void saveAdresse(Adresse adresse){
        Database.save(adresse, sqlSaveAdresse, "");
    }
}
