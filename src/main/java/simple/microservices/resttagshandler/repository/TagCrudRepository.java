package simple.microservices.resttagshandler.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import simple.microservices.resttagshandler.model.Tag;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TagCrudRepository extends CrudRepository<Tag, Integer> {
}
