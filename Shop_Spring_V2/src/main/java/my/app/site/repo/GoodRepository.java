package my.app.site.repo;

import my.app.site.models.Good;
import org.springframework.data.repository.CrudRepository;

//import java.util.List;

public interface GoodRepository extends CrudRepository<Good,Integer> {
    Iterable<Good> findByCat (int cat);
}
