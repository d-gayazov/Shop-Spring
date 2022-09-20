package my.app.site.repo;

import my.app.site.models.Cart;
import org.springframework.data.repository.CrudRepository;


public interface CartRepository extends CrudRepository<Cart,Integer> {

}
