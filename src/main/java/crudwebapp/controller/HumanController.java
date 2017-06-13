package crudwebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import crudwebapp.model.Human;
import crudwebapp.repository.HumanRepository;
import crudwebapp.repository.HumanRepositoryImplDB;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by arnoldas on 17.6.12.
 */

@RestController
@Api //swagger
@RequestMapping("/api/human")
public class HumanController {

    @Autowired
    private HumanRepository humanRepository = new HumanRepositoryImplDB();

    @ApiOperation(value = "Get all humans")
    @RequestMapping(method = GET)
    @ResponseStatus(OK)
    public List<Human> findAllHumans() {
        return humanRepository.findAllHumans();
    }

    @ApiOperation(value = "Create or update human")
    @RequestMapping(method = POST)
    @ResponseStatus(CREATED)
    public void createOrUpdateHuman(@RequestBody Human human) {
        humanRepository.createOrUpdateHuman(human);
    }

    @ApiOperation(value = "Get human by id")
    @RequestMapping(value = "/{id}", method = GET)
    @ResponseStatus(OK)
    public Human getHumanById(@PathVariable("id") Long id) {
        return humanRepository.findHumanById(id);
    }

    @ApiOperation(value = "Delete human by id")
    @RequestMapping(value = "/{id}", method = DELETE)
    @ResponseStatus(NO_CONTENT)
    public void deleteHumanById(@PathVariable("id") Long id) {
        humanRepository.deleteHuman(id);
    }

}

