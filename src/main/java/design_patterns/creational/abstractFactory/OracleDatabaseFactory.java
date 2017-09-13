package design_patterns.creational.abstractFactory;

/**
 * Created by Mati on 09.07.2017.
 */
public class OracleDatabaseFactory extends AbstractDatabaseFactory  {
    @Override
    IDatabase getDatabase() {
        return new OracleDatabase();
    }
}
