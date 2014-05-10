package ch.hslu.dmg.Dataaccess;

        import ch.hslu.dmg.library.Bestellung;

/**
 * @author Angelo on 09.05.2014.
 */
public class BestellungDao extends BaseDao {
    public BestellungDao() {
        super();
    }


    public int Save(Bestellung bestellung) {
        String insertQuery = "INSERT INTO Bestellung (Anzahl, TeilNr, KundenNr) Values (%d, %d, %d)";
        insertQuery = String.format(insertQuery, bestellung.get_Anzahl(), bestellung.get_Teil().get_ID(), bestellung.get_Kunde().get_ID());
        return Database.ExecuteNonQuery(insertQuery);
    }
}
