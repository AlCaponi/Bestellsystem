package ch.hslu.dmg.Dataaccess;

import ch.hslu.dmg.library.Person;

/**
 * @author Angelo on 08.05.2014.
 */
public class PersonDAO extends BaseDao {

    private String SqlReadPerson =        "SELECT \n"
                    + "       [ID]\n"
                    + "	     ,[Name]\n"
                    + "      ,[AdresseID]\n"
                    + "      ,[BankverbindungID]\n"
                    + "  FROM [dbo].[Person]"
                    + "  WHERE [ID] = %i";


    public PersonDAO(){
        super();
    }

    public Person readPerson(int personId){
        return (Person) Database.FillObject(new Person(), String.format(SqlReadPerson, personId));
    }
}
