package my.app.site.repo;

import my.app.site.models.Info;
import org.springframework.data.repository.CrudRepository;

public interface InfoRepository extends CrudRepository<Info,Integer> {

}
