package minibank.bbva.model.dao;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface DAO<T> {
	boolean save(T objeto);

	Optional<T> read(String id);

	void delete(T t);

	Collection<T> readAll();

	boolean update(T objeto);

	List<T> readSpecific(String id);

	Optional<T> getSpecialParam(String id);

}
