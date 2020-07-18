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

    @GetMapping("/find/{id}")
    public Map<String, Object> findOne(@PathVariable(value = "id") Integer id) {
        Map<String, Object> map = new HashMap<>();
        log.info("event id:[{}]", id);
        Event eventById = eventService.findOne(id);
        if (eventById == null){
            map.put("state", false);
            map.put("msg", "Event id is invalid");
        }else {
            map.put("state", false);
            map.put("msg", "Find event success");
            map.put("event", eventById);
        }
        return map;
    }

    @GetMapping("/delete/{id}")
    public Map<String, Object> delete(@PathVariable(value = "id")Integer id) {
        log.info("deleter user id:[{}]", id);
        Map<String, Object> map = new HashMap<>();
        try {
            Event event = eventService.findOne(id);
            if (event == null){
                map.put("state", false);
                map.put("msg", "Event id is invalid");
                return map;
            }else {
                File file = new File(realPath, event.getCover_image());
                if (file.exists()) {
                    file.delete();
                }
                eventService.delete(id);
                map.put("state", true);
                map.put("msg", "Event deletes success");
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", false);
            map.put("msg", "Event fails to delete");
            return map;
        }
    }
////user id
    @PostMapping("/create")
    public Map<String, Object> save(Event event, MultipartFile cover_image) throws IOException {
        Map<String, Object> map = new HashMap<>();
        log.info("event:[{}]", event.toString());
        log.info("photo:[{}]", cover_image.getOriginalFilename());
        try {
            String fileName = FilenameUtils.getExtension(cover_image.getOriginalFilename());
            cover_image.transferTo(new File(realPath, fileName));
            event.setCover_image(fileName);
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
    public Map<String, Object> update(Event event, MultipartFile cover_image) throws IOException {
        log.info("event info: [{}]", event.toString());
        Map<String, Object> map = new HashMap<>();
        try {
            if (cover_image != null && cover_image.getSize() != 0) {
                log.info("photo info[{}]", cover_image.getOriginalFilename());
                String fileName = FilenameUtils.getExtension(cover_image.getOriginalFilename());
                cover_image.transferTo(new File(realPath, fileName));
                event.setCover_image(fileName);
            }
            event.setUpdate_at(new Date());
            eventService.update(event);
            map.put("state", true);
            map.put("msg", "Update event information success");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", false);
            map.put("msg", "Fail to update event");
        }
        return map;
    }
}
