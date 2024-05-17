package Model;

import java.sql.SQLException;

public interface DAO<T> {
    boolean insertar(T obj) throws SQLException;
    boolean actualitzar(T obj) throws SQLException;
    boolean esborrar(T obj) throws SQLException;
    T cercar(int id) throws SQLException;
    int count() throws SQLException;
}