package ch.hslu.dmg.Dataaccess;

import ch.hslu.dmg.library.Bestellung;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Angelo on 09.05.2014.
 */
public class BestellungDao extends BaseDao {
    public BestellungDao(){
        super();
    }

    public int Save(Bestellung bestellung) {
        String insertQuery = "INSERT INTO Bestellung (Anzahl, TeilNr, KundenNr) Values (%d, %d, %d)";
        insertQuery = String.format(insertQuery, bestellung.get_Anzahl(), bestellung.get_Teil().get_ID(), bestellung.get_Kunde().get_ID());
        int generatedId = 0;
        try {
            PreparedStatement statement = Database._connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                generatedId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating user failed, no generated key obtained.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
    }
}
