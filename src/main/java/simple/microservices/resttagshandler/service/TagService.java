package simple.microservices.resttagshandler.service;

import simple.microservices.resttagshandler.model.Tag;

import java.util.Set;

public interface TagService {
    Set<Tag> getTags();
}
