package dao;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface GenericDao<T> extends AutoCloseable {
    Optional<T> create(T entity);

    T findById(int id);

    List<T> findAll();

    Optional<T> update(T entity);

}
