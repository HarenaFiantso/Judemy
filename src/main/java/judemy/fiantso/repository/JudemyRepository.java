package judemy.fiantso.repository;

import java.util.List;

public interface JudemyRepository<T> {
    T create(T user);

    T getById(Long id);

    List<T> getAll();

    T update(T model);

    void delete(Long id);

}
