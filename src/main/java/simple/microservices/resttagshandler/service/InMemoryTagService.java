package simple.microservices.resttagshandler.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
//import org.springframework.core.env.Environment;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import simple.microservices.resttagshandler.config.AppProperties;
import simple.microservices.resttagshandler.model.Tag;
import simple.microservices.resttagshandler.repository.TagCrudRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class InMemoryTagService implements TagService {

    private AppProperties properties;
    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    private final ScheduledExecutorService scheduler;

    private final TagCrudRepository repository;
    private final Set<Tag> inMemoryTags;

    @Autowired
    public InMemoryTagService(TagCrudRepository tagCrudRepository, AppProperties appProperties){
        this.repository = tagCrudRepository;
        this.properties = appProperties;
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
        scheduler.scheduleAtFixedRate( runnable, properties.getUpdate_interval(), properties.getUpdate_interval(), TimeUnit.SECONDS);
    }

    @Override
    public Set<Tag> getTags() {
        logger.info("Returning list of tags: " + inMemoryTags.toString());
        return inMemoryTags;
    }
}
