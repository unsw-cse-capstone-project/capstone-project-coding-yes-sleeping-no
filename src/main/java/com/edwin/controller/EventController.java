package com.edwin.controller;

import com.edwin.dao.EventDao;
import com.edwin.entity.Event;
import com.edwin.entity.Order;
import com.edwin.entity.User;
import com.edwin.service.EventService;
import com.edwin.utlis.Consts;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
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

    @ApiOperation("get all event information router in event controller")
    @GetMapping("/findAll")
    public List<Event> findAll() {
        return eventService.findAll();
    }

    @ApiOperation("get one event information by event id router in event controller")
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

    @ApiOperation("delete one event by event id router in event controller")
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
//                File file = new File(realPath, event.getCover_image());
//                if (file.exists()) {
//                    file.delete();
//                }
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

    @ApiOperation("create one event with cover image router in event controller")
    @PostMapping("/create")
    public Map<String, Object> save(@RequestBody  Event event, HttpSession session) throws IOException {
        Map<String, Object> map = new HashMap<>();
        User currentUser = (User)session.getAttribute(Consts.CURRENT_USER);
        log.info("event:[{}]", event.toString());
//        log.info("photo:[{}]", cover_image.getOriginalFilename());
        try {
//            String fileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(cover_image.getOriginalFilename());
//            cover_image.transferTo(new File(realPath, fileName));
//            event.setCover_image(fileName);
            event.setCreated_at(new Date());
            event.setUpdated_at(new Date());
            event.setUser_id(currentUser.getId());
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

    @ApiOperation("update one event with cover image router in event controller")
    @PostMapping("/update")
    public Map<String, Object> update(@RequestBody Event event) throws IOException {
        log.info("event info: [{}]", event.toString());
        Map<String, Object> map = new HashMap<>();
        try {
//            if (cover_image != null && cover_image.getSize() != 0) {
//                log.info("photo info[{}]", cover_image.getOriginalFilename());
//                String fileName = FilenameUtils.getExtension(cover_image.getOriginalFilename());
//                cover_image.transferTo(new File(realPath, fileName));
//                event.setCover_image(fileName);
//            }
            event.setUpdated_at(new Date());
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

//    @ApiOperation("get one event information by event id router in event controller")
//    @GetMapping("/find")
//    public Map<String, Object> findOne(@PathVariable(value = "id") Integer id) {
//        Map<String, Object> map = new HashMap<>();
//        log.info("event id:[{}]", id);
//        Event eventById = eventService.findOne(id);
//        if (eventById == null){
//            map.put("state", false);
//            map.put("msg", "Event id is invalid");
//        }else {
//            map.put("state", false);
//            map.put("msg", "Find event success");
//            map.put("event", eventById);
//        }
//        return map;
//    }
}
