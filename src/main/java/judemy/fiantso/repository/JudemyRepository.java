package judemy.fiantso.repository;

import judemy.fiantso.models.Users;

import java.util.List;

public interface JudemyRepository<T> {
    T create(T user);

    T getById(Long id);

    List<T> getAll();

    Users update(T model);

    void delete(Long id);
}
