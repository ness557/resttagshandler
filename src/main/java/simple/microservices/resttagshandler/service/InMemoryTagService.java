package simple.microservices.resttagshandler.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import simple.microservices.resttagshandler.model.Tag;
import simple.microservices.resttagshandler.repository.TagCrudRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class InMemoryTagService implements TagService {

    @Value("application.update_interval")
    private int updateInterval;

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    private final ScheduledExecutorService scheduler;

    private final TagCrudRepository repository;
    private final Set<Tag> inMemoryTags;

    @Autowired
    public InMemoryTagService(TagCrudRepository tagCrudRepository){
        this.repository = tagCrudRepository;
        inMemoryTags = new HashSet<>();
        scheduler = Executors.newSingleThreadScheduledExecutor();

        Runnable runnable = ()->{
            logger.info("Updating tags inMemory list");
            Iterable<Tag> tags = repository.findAll();

            for (Tag tag: tags) {
                inMemoryTags.add(tag);
            }
            logger.info("List updated");
        };
        scheduler.scheduleAtFixedRate( runnable, updateInterval, updateInterval, TimeUnit.SECONDS);
    }

    @Override
    public Set<Tag> getTags() {
        logger.info("Returning list of tags: " + inMemoryTags.toString());
        return inMemoryTags;
    }
}