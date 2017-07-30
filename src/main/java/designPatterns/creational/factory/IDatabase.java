package designPatterns.creational.factory;

/**
 * Created by Mati on 09.07.2017.
 */
public interface IDatabase {
    IDBConnection connect();
    IDBCommand createCommand(IDBConnection connection);
    IDBResult executeCommand(IDBConnection connection, IDBCommand command);
}
