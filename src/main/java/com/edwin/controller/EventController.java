package com.edwin.controller;

import com.edwin.dao.EventDao;
import com.edwin.dao.EventReviewDao;
import com.edwin.dao.UserDao;
import com.edwin.entity.Event;
import com.edwin.entity.EventReview;
import com.edwin.entity.Order;
import com.edwin.entity.User;
import com.edwin.service.EventService;
import com.edwin.utlis.Consts;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
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

/**
 * event status 1: exist
 * 0: delete
 */
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
    private UserDao userDao;

    @Autowired
    private EventDao eventDao;

    @Autowired
    private EventReviewDao eventReviewDao;

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
            return map;
        }
        String type = eventById.getType();
        List<Event> recommendedEvents = eventDao.findType(type, 1);
        recommendedEvents.remove(eventById);
        List<EventReview> eventReviewsByEvent = eventReviewDao.findByEvent(id);
        List<User> users = new ArrayList<>();
        for (EventReview eventReview: eventReviewsByEvent){
            Integer sender_id = eventReview.getSender_id();
            users.add(userDao.findByUserId(sender_id));
        }
        for (Event event : recommendedEvents) {
            Date start_date = event.getStart_date();
            Long timeDiffernce = start_date.getTime() - new Date().getTime();
            int days = (int) (timeDiffernce / (1000 * 60 * 60 * 24));
            if (days < 1) {
                recommendedEvents.remove(event);
            }
            if (event.getAvailable_tickets() <= 1) {
                recommendedEvents.remove(event);
            }
        }
        map.put("state", true);
        map.put("msg", "Find event success");
        map.put("event", eventById);
        map.put("recommended", recommendedEvents);
        map.put("eventReviews", eventReviewsByEvent);
        map.put("eventReviewUsers", users);
        return map;
    }

    @ApiOperation("get event information for index page router in event controller")
    @GetMapping("/find/index")
    public Map<String, Object> findIndex() {
        Map<String, Object> map = new HashMap<>();
        List<Event> liveConcerts = eventDao.findType("Live Concerts", 1);
        List<Event> movies = eventDao.findType("Movies", 1);
        List<Event> drama = eventDao.findType("Drama", 1);
        List<Event> sport = eventDao.findType("Sport", 1);

        try {
            for (Event event : liveConcerts) {
                Date start_date = event.getStart_date();
                Long timeDiffernce = start_date.getTime() - new Date().getTime();
                int days = (int) (timeDiffernce / (1000 * 60 * 60 * 24));
                if (days < 15) {
                    liveConcerts.remove(event);
                }
                if (event.getAvailable_tickets() <= 1) {
                    liveConcerts.remove(event);
                }
            }
            for (Event event : movies) {
                Date start_date = event.getStart_date();
                Long timeDiffernce = start_date.getTime() - new Date().getTime();
                int days = (int) (timeDiffernce / (1000 * 60 * 60 * 24));
                if (days < 15) {
                    movies.remove(event);
                }
                if (event.getAvailable_tickets() <= 1) {
                    movies.remove(event);
                }
            }
            for (Event event : drama) {
                Date start_date = event.getStart_date();
                Long timeDiffernce = start_date.getTime() - new Date().getTime();
                int days = (int) (timeDiffernce / (1000 * 60 * 60 * 24));
                if (days < 15) {
                    drama.remove(event);
                }
                if (event.getAvailable_tickets() <= 1) {
                    drama.remove(event);
                }
            }
            for (Event event : sport) {
                Date start_date = event.getStart_date();
                Long timeDiffernce = start_date.getTime() - new Date().getTime();
                int days = (int) (timeDiffernce / (1000 * 60 * 60 * 24));
                if (days < 15) {
                    sport.remove(event);
                }
                if (event.getAvailable_tickets() <= 1) {
                    sport.remove(event);
                }
            }
            map.put("state", true);
            map.put("msg", "Find event for index page success");
            map.put("Live Concerts", liveConcerts);
            map.put("Movies", movies);
            map.put("Drama", drama);
            map.put("sport", sport);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", false);
            map.put("hint", e.getMessage());
            map.put("msg", "Event fails to find for index page");
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
            map.put("hint", e.getMessage());
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
            event.setStatus(1);
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

    @ApiOperation("get all related event information by host id router in event controller")
    @GetMapping("/findByHost")
    public Map<String, Object> findByHost(HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        User currentUser = (User)session.getAttribute(Consts.CURRENT_USER);
        Integer userId = currentUser.getId();
        log.info("user id : [{}]", userId);
        List<Event> eventsByHost = eventDao.findHost(userId,1);
        if (eventsByHost.size() == 0){
            map.put("state", false);
            map.put("msg", "No event found by host");
        }else {
            map.put("state", true);
            map.put("msg", "Find event success");
            map.put("event", eventsByHost);
        }
        return map;
    }

    @ApiOperation("get all related event information by event type router in event controller")
    @ResponseBody
    @PostMapping("/findByType")
    public Map<String, Object> findByType(@RequestBody Map<String,Object> map1, HttpSession session) {
        String type =(String) map1.get("type");
        Map<String, Object> map = new HashMap<>();
        List<Event> events = eventService.findByType(type);
        if (events.size() == 0){
            map.put("state", false);
            map.put("msg", "No event found by event type");
        }else {
            map.put("state", true);
            map.put("msg", "Find event success");
            map.put("event", events);
        }
        return map;
    }

    @ApiOperation("search related event information by keyword router in event controller")
    @ResponseBody
    @PostMapping("/findByKeyword")
    public Map<String, Object> findByKeyword(@RequestBody Map<String,Object> map1, HttpSession session) {
        String keyword =(String) map1.get("keyword");
        Map<String, Object> map = new HashMap<>();
        List<Event> events = eventService.findByKeyword(keyword);
        if (events.size() == 0){
            map.put("state", false);
            map.put("msg", "No event found by keyword");
        }else {
            map.put("state", true);
            map.put("msg", "Find event success");
            map.put("event", events);
        }
        return map;
    }

//    @ApiOperation("recommended events by user router in event controller")
//    @ResponseBody
//    @PostMapping("/recommended")
//    public Map<String, Object> recommended(@RequestBody Map<String,Object> map1, HttpSession session) {
//        String keyword =(String) map1.get("keyword");
//        Map<String, Object> map = new HashMap<>();
//        User currentUser = (User)session.getAttribute(Consts.CURRENT_USER);
//        List<Event> events = eventService.findByRecommended(currentUser);
//        if (events.size() == 0){
//            map.put("state", false);
//            map.put("msg", "No event found for recommended");
//        }else {
//            map.put("state", true);
//            map.put("msg", "Find recommended events success");
//            map.put("event", events);
//        }
//        return map;
//    }
}
