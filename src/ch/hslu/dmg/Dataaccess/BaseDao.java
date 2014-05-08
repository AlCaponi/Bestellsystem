package ch.hslu.dmg.Dataaccess;

/**
 * @author Angelo on 08.05.2014.
 */
public class BaseDao {
    protected Database Database;

    protected BaseDao(){
        this.Database = new Database("");
    }
}
