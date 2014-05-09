package ch.hslu.dmg.Dataaccess;

import ch.hslu.dmg.library.Person;
import ch.hslu.dmg.library.collection.PersonCol;

/**
 * @author Angelo on 08.05.2014.
 */
public class PersonDAO extends BaseDao {

    private String SqlReadPerson =        "SELECT \n"
                    + "       [ID]           \n"
                    + "	     ,[Name]\n"
                    + "      ,[AdresseID]\n"
                    + "      ,[BankverbindungID]\n"
                    + "  FROM [dbo].[Person]"
                    + "  WHERE [ID] = %d";


    private String SqlReadPersonWithSub = "SELECT  ID,\n"
                                            + "	   Name,\n"
                                            + "	   Adresse.AdresseID AS [Adresse.ID],\n"
                                            + "	   Adresse.Ort       AS [Adresse.Ort],\n"
                                            + "	   Adresse.Postleitzahl AS [Adresse.Postleitzahl],\n"
                                            + "	   Adresse.Strasse    AS [Adresse.Strasse]\n"
                                            + "FROM Person \n"
                                            + "    INNER JOIN Adresse \n"
                                            + "ON Adresse.AdresseID = Person.AdresseID\n"
                                            + "  WHERE [ID] = %d";

    private String SqlReadPersonen = "SELECT  ID,\n"
                                            + "	   Name,\n"
                                            + "	   Adresse.AdresseID AS [Adresse.ID],\n"
                                            + "	   Adresse.Ort       AS [Adresse.Ort],\n"
                                            + "	   Adresse.Postleitzahl AS [Adresse.Postleitzahl],\n"
                                            + "	   Adresse.Strasse    AS [Adresse.Strasse]\n"
                                            + "FROM Person \n"
                                            + "    INNER JOIN Adresse \n"
                                            + "ON Adresse.AdresseID = Person.AdresseID\n";
    public PersonDAO(){
        super();
    }

    public final Person readPerson(final int personId){
        return (Person) Database.FillObject(new Person(), String.format(SqlReadPersonWithSub, personId));
    }

    public final PersonCol readPersonen(){
        return (PersonCol) Database.FillList(new PersonCol(), Person.class, String.format(SqlReadPersonen));
    }
}
