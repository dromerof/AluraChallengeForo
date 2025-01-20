package com.alura.callenge.foro.controller;


import com.alura.callenge.foro.Service.TopicService;
import com.alura.callenge.foro.domain.topic.*;
import com.alura.callenge.foro.domain.topic.dto.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicController {


    @Autowired
    TopicService topicService;

    @PostMapping
    public ResponseEntity<DataReplayTopic> recordNewTopic(@RequestBody @Valid DataNewTopic dataNewTopic,
                                                          UriComponentsBuilder uriComponentsBuilder){

        DataReplayTopic dataReplayTopic = topicService.createNewTopic(dataNewTopic);

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(dataReplayTopic.id()).toUri();
        return ResponseEntity.created(url).body(dataReplayTopic);

    }

    @GetMapping
    public ResponseEntity<Page<DataListTopic>> listAllTopics(@PageableDefault(size = 10)
                                                             @SortDefault(sort = "timestamp",
                                                                     direction = Sort.Direction.ASC)
                                                             Pageable pageable){

        return ResponseEntity.ok(topicService.listAllTopics(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataTopicDetails> getTopicDetails(@PathVariable Long id){

        Topic topic = topicService.getTopicById(id);
        DataTopicDetails dataTopicDetails = new DataTopicDetails(topic.getTitle(),
                topic.getMessage(),
                topic.getTimestamp(),
                topic.isStatus(),
                topic.getAuthor(),
                topic.getCourse(),
                topic.getLast_update()
        );


        return ResponseEntity.ok(dataTopicDetails);
    }

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<DataReplayTopic> updateTopic(@PathVariable Long id,
                                                       @RequestBody @Valid DataUpdateTopic dataUpdateTopic,
                                                       UriComponentsBuilder uriComponentsBuilder){

        DataReplayTopic dataReplayTopic = topicService.updateTopic(id, dataUpdateTopic);

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(dataReplayTopic.id()).toUri();
        return ResponseEntity.created(url).body(dataReplayTopic);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletedTopic(@PathVariable Long id){

        topicService.deleteTopic(id);

        return ResponseEntity.noContent().build();

    }
}
