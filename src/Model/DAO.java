package Model;
import java.sql.Connection;
import java.sql.SQLException;

public interface DAO<T> {
    boolean insertar(T obj, Connection connexio) throws SQLException;
    boolean actualitzar(T obj, Connection connexio) throws SQLException;
    boolean esborrar(T obj, Connection connexio) throws SQLException;
    T cercar(int id, Connection connexio) throws SQLException;
    int count() throws SQLException;
}