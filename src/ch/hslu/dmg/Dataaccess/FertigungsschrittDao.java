package ch.hslu.dmg.Dataaccess;

import ch.hslu.dmg.library.Fertigungsschritt;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author Angelo on 09.05.2014.
 */
public class FertigungsschrittDao extends BaseDao {

    public void Save(Fertigungsschritt fertigungsschritt, int bestellNr) {
        String insertQuery = "INSERT INTO Fertigungsschritt (datum, MitarbeiterID, MaschineID, FertigungsteilID, BestellungID)"
                + "VALUES (?, %d, %d, %d, %d)";

        insertQuery = String.format(insertQuery, fertigungsschritt.get_Mitarbeiter().get_ID(), fertigungsschritt.get_Maschine().get_ID(),
                fertigungsschritt.get_Fertigungsteil().get_ID(), bestellNr);
        //Database.ExecuteNonQuery(insertQuery);
        //Date currentDatetime = new Date(System.currentTimeMillis());
        java.sql.Date sqlDate = new java.sql.Date(fertigungsschritt.get_datum().getTime());
        java.sql.Timestamp timestamp = new java.sql.Timestamp(fertigungsschritt.get_datum().getTime());
        try {
            CallableStatement statement = Database._connection.prepareCall(insertQuery);
            statement.setTimestamp(1, timestamp);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
