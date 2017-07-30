package designPatterns.creational.factory;

/**
 * Created by Mati on 09.07.2017.
 */
public class MSSqlDatabase implements IDatabase {
    @Override
    public IDBConnection connect() {
        // MySQL specific stuff
        return null;
    }

    @Override
    public IDBCommand createCommand(IDBConnection connection) {
        // MySQL specific stuff
        return null;
    }

    @Override
    public IDBResult executeCommand(IDBConnection connection, IDBCommand command) {
        // MySQL specific stuff
        return null;
    }
}
