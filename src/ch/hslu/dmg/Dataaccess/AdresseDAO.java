package ch.hslu.dmg.Dataaccess;

import ch.hslu.dmg.library.Adresse;

/**
 * @author Angelo on 08.05.2014.
 */
public class AdresseDAO extends BaseDao {

    String sqlReadAdresse  = "SELECT   [AdresseID]      AS ID\n"
                             + "      ,[Ort]            AS Ort\n"
                             + "      ,[Postleitzahl]   AS Postleitzahl\n"
                             + "      ,[Strasse]        AS Strasse\n"
                             + "  FROM [dbo].[Adresse]\n"
                             + "  WHERE AdresseID = %d";
    public AdresseDAO(){
        super();
    }

    public Adresse readAdresse(int adresseId){
        return (Adresse) Database.FillObject(new Adresse(), String.format(sqlReadAdresse, adresseId));
    }
}
