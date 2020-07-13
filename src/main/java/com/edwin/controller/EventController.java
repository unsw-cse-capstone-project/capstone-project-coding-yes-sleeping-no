package com.edwin.controller;

import com.edwin.dao.EventDao;
import com.edwin.entity.Event;
import com.edwin.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/event")
@Slf4j
public class EventController {


    @Value("${photo.dir}")
    private String realPath;

    @Autowired
    private EventService eventService;

    @Autowired
    private EventDao eventDao;

    @GetMapping("/findAll")
    public List<Event> findAll() {
        return eventService.findAll();
    }

    @GetMapping("/findOne")
    public Event findOne(Integer id) {
        log.info("event id:[{}]", id);
        return eventService.findOne(id);
    }

    @GetMapping("/delete")
    public Map<String, Object> delete(Integer id) {
        log.info("deleter user id:[{}]", id);
        Map<String, Object> map = new HashMap<>();
        try {
            Event event = eventService.findOne(id);
//            File file = new File(realPath,event.getPath());
//            if (file.exists()){
//                file.delete();
//            }
            eventService.delete(id);
            map.put("state", true);
            map.put("msg", "Event deletes success");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", false);
            map.put("msg", "Event fails to delete");
        }
        return map;
    }

    @PostMapping("/create")
    public Map<String, Object> save(Event event, MultipartFile photo) throws IOException {
        Map<String, Object> map = new HashMap<>();
        log.info("event:[{}]", event.toString());
        log.info("photo:[{}]", photo.getOriginalFilename());
        try {
            String fileName = FilenameUtils.getExtension(photo.getOriginalFilename());
            photo.transferTo(new File(realPath, fileName));
            event.setEvent_image(fileName);
            event.setCreated_at(new Date());
            event.setUpdate_at(new Date());
            eventService.save(event);
            map.put("state", true);
            map.put("msg", "Event information saves success");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", false);
            map.put("msg", "Event information fails to save");
        }
        return map;
    }

    @PostMapping("/update")
    public Map<String,Object> update(Event event, MultipartFile photo) throws IOException {
        log.info("event info: [{}]", event.toString());
        Map<String, Object> map = new HashMap<>();
        try {
            if (photo != null && photo.getSize() != 0){
                log.info("photo info[{}]", photo.getOriginalFilename());
                String fileName = FilenameUtils.getExtension(photo.getOriginalFilename());
                photo.transferTo(new File(realPath,fileName));
                event.setEvent_image(fileName);
            }
            event.setUpdate_at(new Date());
            event.setCreated_at(event.getCreated_at());
            eventService.update(event);
            map.put("state", true);
            map.put("msg","Update event information success");
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", false);
            map.put("msg","Fail to update event");
            return map;
        }
    }
}
