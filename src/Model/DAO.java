package Model;

public interface DAO<T> {
    boolean insertar(T obj);
    boolean actualitzar(T obj);
    boolean esborrar(T obj);
    T cercar(int id);
}
