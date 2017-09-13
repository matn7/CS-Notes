package design_patterns.creational.factory;

/**
 * Created by Mati on 09.07.2017.
 */
public class OracleDatabase implements IDatabase {
    @Override
    public IDBConnection connect() {
        // Oracle specific stuff
        return null;
    }

    @Override
    public IDBCommand createCommand(IDBConnection connection) {
        // Oracle specific stuff
        return null;
    }

    @Override
    public IDBResult executeCommand(IDBConnection connection, IDBCommand command) {
        // Oracle specific stuff
        return null;
    }
}
