package com.alura.callenge.foro.Service;

import com.alura.callenge.foro.domain.topic.Topic;
import com.alura.callenge.foro.domain.topic.dto.DataListTopic;
import com.alura.callenge.foro.domain.topic.dto.DataNewTopic;
import com.alura.callenge.foro.domain.topic.dto.DataReplayTopic;
import com.alura.callenge.foro.domain.topic.dto.DataUpdateTopic;
import com.alura.callenge.foro.infra.errors.exception.BusinessRulesValidationsException;
import com.alura.callenge.foro.repository.TopicRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public DataReplayTopic createNewTopic(DataNewTopic dataNewTopic){

        if(topicRepository.existsByTitleAndMessageAndDeletedFalse(dataNewTopic.title(), dataNewTopic.message())){
            throw new BusinessRulesValidationsException("Ya existe un tópico con este título y mensaje.");
        }

        Topic newTopic = new Topic();

        newTopic.setTitle(dataNewTopic.title());
        newTopic.setAuthor(dataNewTopic.author());
        newTopic.setCourse(dataNewTopic.course());
        newTopic.setMessage(dataNewTopic.message());

        newTopic.setStatus(true);
        newTopic.setDeleted(false);

        String timeStamp = this.getTimeStamp();

        newTopic.setTimestamp(timeStamp);
        newTopic.setLast_update(timeStamp);

        topicRepository.save(newTopic);


        return new DataReplayTopic(newTopic.getId(),
                newTopic.getTitle(),
                newTopic.getMessage(),
                newTopic.getTimestamp(),
                newTopic.isStatus(),
                newTopic.getCourse(),
                newTopic.getAuthor(),
                newTopic.getLast_update()
        );


    }

    public Page<DataListTopic> listAllTopics(Pageable pageable){
        return topicRepository.findByDeletedFalse(pageable).map(DataListTopic::new);
    }

    public Topic getTopicById(Long id){
        return topicRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new EntityNotFoundException(
                "Tópico no encontrado"));
    }

    public DataReplayTopic updateTopic(Long id, DataUpdateTopic dataUpdateTopic){

        //Search the topic
        Topic topic = this.getTopicById(id);

        //Verify that the update details will not create a duplicate topic
        if(!topic.getTitle().equals(dataUpdateTopic.title()) || !topic.getMessage().equals(dataUpdateTopic.message())){
            if(topicRepository.existsByTitleAndMessageAndDeletedFalse(dataUpdateTopic.title(),
                    dataUpdateTopic.message())){
                throw new BusinessRulesValidationsException("Ya existe un tópico con este título y mensaje.");
            }
        }

        topic.updateTopic(dataUpdateTopic, this.getTimeStamp());

        return new DataReplayTopic(topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getTimestamp(),
                topic.isStatus(),
                topic.getCourse(),
                topic.getAuthor(),
                topic.getLast_update());
    }

    public void deleteTopic(Long id){


        Topic topic = this.getTopicById(id);
        topic.deleteTopic(this.getTimeStamp());

    }

    private String getTimeStamp(){

        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Define the format (dd-MM-yyyy HH:mm:ss)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        // Format the current date and time
        return now.format(formatter);

    }



}
