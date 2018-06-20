package simple.microservices.resttagshandler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simple.microservices.resttagshandler.model.Tag;
import simple.microservices.resttagshandler.service.TagService;

import java.util.Set;

@RestController
@RequestMapping("/tags")
public class TagController {
    private TagService service;

    @Autowired
    public TagController(TagService tagService){
        this.service = tagService;
    }

    @RequestMapping("/getAll")
    public ResponseEntity<Set<Tag>> getTags(){
        return new ResponseEntity<>(service.getTags(), HttpStatus.OK);
    }
}
