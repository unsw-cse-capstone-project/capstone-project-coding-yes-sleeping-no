package com.edwin.controller;

import com.edwin.dao.EventDao;
import com.edwin.dao.EventReviewDao;
import com.edwin.dao.UserDao;
import com.edwin.entity.Event;
import com.edwin.entity.EventReview;
import com.edwin.entity.User;
import com.edwin.service.EventService;
import com.edwin.utlis.Consts;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

/**
 * event status
 * 0 => delete
 * 1 => exist
 * Description: event controller has all routers of event related
 */
@RestController
@CrossOrigin
@RequestMapping("/event")
@Slf4j
public class EventController {

//    @Value("${photo.dir}")
//    private String realPath;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private EventDao eventDao;

    @Autowired
    private EventReviewDao eventReviewDao;

    /**
     * Get all events
     *
     * @return
     */
    @ApiOperation("Get all event information router in event controller")
    @GetMapping("/findAll")
    public List<Event> findAll() {
        return eventService.findAll();
    }

    /**
     * Get one event by event id
     *
     * @param id
     * @return
     */
    @ApiOperation("Get one event information by event id router in event controller")
    @GetMapping("/find/{id}")
    public Map<String, Object> findOne(@PathVariable(value = "id") Integer id) {
        Map<String, Object> response = new HashMap<>();
        log.info("event id:[{}]", id);
        Event eventById = eventService.findOne(id);
        if (eventById == null) {
            response.put("state", false);
            response.put("msg", "event id is invalid");
            return response;
        }
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(4, 10, 3, TimeUnit.SECONDS, new LinkedBlockingDeque<>(5),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardOldestPolicy());
        String type = eventById.getType();
        List<Event> recommendedEvents = eventDao.findType(type, 1);
        recommendedEvents.remove(eventById);
        List<EventReview> eventSendReviews = eventReviewDao.findSend(0, id);
        List<List<Object>> reviewResult = new ArrayList<>();
        if (eventSendReviews.size() == 1) {
            List<Object> tmp = new ArrayList<>();
            EventReview eventReview = eventSendReviews.get(0);
            User sender = userDao.findByUserId(eventReview.getSender_id());
            tmp.add(eventReview);
            tmp.add(sender);
            Integer senderId = eventReview.getSender_id();
            EventReview eventReply = eventReviewDao.findReply(senderId, id);
            User receiver = userDao.findByUserId(eventReply.getSender_id());
            tmp.add(eventReply);
            tmp.add(receiver);
            reviewResult.add(tmp);
        } else if (eventSendReviews.size() > 1) {
            for (EventReview eventReview : eventSendReviews) {
                List<Object> tmp = new ArrayList<>();
                Integer senderId = eventReview.getSender_id();
                User sender = userDao.findByUserId(eventReview.getSender_id());
                tmp.add(eventReview);
                tmp.add(sender);
                EventReview eventReply = eventReviewDao.findReply(senderId, id);
                User receiver = userDao.findByUserId(eventReply.getSender_id());
                tmp.add(eventReply);
                tmp.add(receiver);
                reviewResult.add(tmp);
            }
        }
        log.info("recommended events:[{}]", recommendedEvents.size());
        if (recommendedEvents.size() <= 0) {
            List<Event> allEvents = eventDao.findAll(1);
            allEvents.remove(eventById);
            Iterator<Event> iterator = allEvents.iterator();
            while (iterator.hasNext()) {
                Event event = iterator.next();
                Date startDate = event.getStart_date();
                Long timeDifference = startDate.getTime() - new Date().getTime();
                int days = (int) (timeDifference / (1000 * 60 * 60 * 24));
                if (days < 1) {
                    iterator.remove();
                } else if (event.getAvailable_tickets() < 1) {
                    iterator.remove();
                }
            }
            for (int i = 0; i < 3; i++) {
                recommendedEvents.add(allEvents.get(i));
            }
            response.put("state", true);
            response.put("msg", "Find event success");
            response.put("event", eventById);
            response.put("recommended", recommendedEvents);
            response.put("eventReviews", reviewResult);
            return response;
        } else if (recommendedEvents.size() == 1) {
            Date startDate = recommendedEvents.get(0).getStart_date();
            Long timeDifference = startDate.getTime() - new Date().getTime();
            int days = (int) (timeDifference / (1000 * 60 * 60 * 24));
            if (days < 1) {
                recommendedEvents.remove(recommendedEvents.get(0));
            } else if (recommendedEvents.get(0).getAvailable_tickets() < 1) {
                recommendedEvents.remove(recommendedEvents.get(0));
            }
            List<Event> allEvents = eventDao.findAll(1);
            allEvents.remove(eventById);
            if (recommendedEvents.size() == 1) {
                allEvents.remove(recommendedEvents.get(0));
            }
            log.info("recommended events vvvvvvvvvvv:[{}]", allEvents.size());
            Iterator<Event> iterator = allEvents.iterator();
            while (iterator.hasNext()) {
                Event event = iterator.next();
                startDate = event.getStart_date();
                timeDifference = startDate.getTime() - new Date().getTime();
                days = (int) (timeDifference / (1000 * 60 * 60 * 24));
                if (days < 1) {
                    iterator.remove();
                } else if (event.getAvailable_tickets() < 1) {
                    iterator.remove();
                }
            }
            log.info("recommended events ccccccccvvvvvvvvvvv:[{}]", recommendedEvents.size());
            log.info("recommended events ccccccccvvvvvvvvvvv:[{}]", allEvents.get(1));
            int length = recommendedEvents.size();
            for (int i = 0; i < 3 - length; i++) {
                recommendedEvents.add(allEvents.get(i));
            }
            log.info("aaaaaaa ccccccccvvvvvvvvvvv:[{}]", recommendedEvents.size());
            response.put("state", true);
            response.put("msg", "find event success");
            response.put("event", eventById);
            response.put("recommended", recommendedEvents);
            response.put("eventReviews", reviewResult);
            return response;
        } else {
            Iterator<Event> iterator1 = recommendedEvents.iterator();
            while (iterator1.hasNext()) {
                Event event = iterator1.next();
                Date startDate = event.getStart_date();
                Long timeDifference = startDate.getTime() - new Date().getTime();
                int days = (int) (timeDifference / (1000 * 60 * 60 * 24));
                if (days < 1) {
                    iterator1.remove();
                } else if (event.getAvailable_tickets() < 1) {
                    iterator1.remove();
                }
            }
            log.info("aaaa: [{}]", recommendedEvents.size());
            List<Event> allEvents = eventDao.findAll(1);
            allEvents.remove(eventById);
            Iterator<Event> iterator2 = recommendedEvents.iterator();
            while (iterator2.hasNext()) {
                Event event = iterator2.next();
                allEvents.remove(event);
            }
            log.info("aaaa: [{}]", allEvents.size());
            List<Event> t4 = allEvents;
            Iterator<Event> iterator3 = allEvents.iterator();
            while (iterator3.hasNext()) {
                Event event = iterator3.next();
                Date startDate = event.getStart_date();
                Long timeDifference = startDate.getTime() - new Date().getTime();
                int days = (int) (timeDifference / (1000 * 60 * 60 * 24));
                if (days < 1) {
                    iterator3.remove();
                } else if (event.getAvailable_tickets() < 1) {
                    iterator3.remove();
                }
            }
            log.info("cdcdcdcdcdcd: [{}]", allEvents.size());
            int length = recommendedEvents.size();
            if (allEvents.size() < 3){
                Iterator<Event> iterator4 = allEvents.iterator();
                while (iterator4.hasNext()){
                    Event event = iterator4.next();
                    recommendedEvents.add(event);
                }
            }else {
                for (int i = 0; i < 3 - length; i++) {
                    recommendedEvents.add(allEvents.get(i));
                }
            }
            response.put("state", true);
            response.put("msg", "find event success");
            response.put("event", eventById);
            response.put("recommended", recommendedEvents);
            response.put("eventReviews", reviewResult);
            return response;
        }
    }

    /**
     * Get events of index page
     *
     * @return
     */
    @ApiOperation("Get event information of index page router in event controller")
    @GetMapping("/find/index")
    public Map<String, Object> findIndex() {
        Map<String, Object> response = new HashMap<>();
        List<Event> liveConcerts = eventDao.findType("Live Concerts", 1);
        List<Event> movies = eventDao.findType("Movies", 1);
        List<Event> drama = eventDao.findType("Drama", 1);
        List<Event> sport = eventDao.findType("Sport", 1);
        try {
            if (liveConcerts.size() > 1) {
                Iterator<Event> iterator = liveConcerts.iterator();
                while (iterator.hasNext()) {
                    Event event = iterator.next();
                    Date startDate = event.getStart_date();
                    Long timeDifference = startDate.getTime() - new Date().getTime();
                    int days = (int) (timeDifference / (1000 * 60 * 60 * 24));
                    if (days < 5) {
                        iterator.remove();
                    } else if (event.getAvailable_tickets() <= 1) {
                        iterator.remove();
                    }
                }
            }
            if (movies.size() > 1) {
                Iterator<Event> iterator = movies.iterator();
                while (iterator.hasNext()) {
                    Event event = iterator.next();
                    Date startDate = event.getStart_date();
                    Long timeDifference = startDate.getTime() - new Date().getTime();
                    int days = (int) (timeDifference / (1000 * 60 * 60 * 24));
                    if (days < 5) {
                        iterator.remove();
                    } else if (event.getAvailable_tickets() <= 1) {
                        iterator.remove();
                    }
                }
            }
            if (drama.size() > 1) {
                Iterator<Event> iterator = drama.iterator();
                while (iterator.hasNext()) {
                    Event event = iterator.next();
                    Date startDate = event.getStart_date();
                    Long timeDifference = startDate.getTime() - new Date().getTime();
                    int days = (int) (timeDifference / (1000 * 60 * 60 * 24));
                    if (days < 5) {
                        iterator.remove();
                    } else if (event.getAvailable_tickets() <= 1) {
                        iterator.remove();
                    }
                }
            }
            if (sport.size() > 1) {
                Iterator<Event> iterator = sport.iterator();
                while (iterator.hasNext()) {
                    Event event = iterator.next();
                    Date startDate = event.getStart_date();
                    Long timeDifference = startDate.getTime() - new Date().getTime();
                    int days = (int) (timeDifference / (1000 * 60 * 60 * 24));
                    if (days < 5) {
                        iterator.remove();
                    } else if (event.getAvailable_tickets() <= 1) {
                        iterator.remove();
                    }
                }
            }
            List<Event> allEvents = eventDao.findAll(1);
            List<Event> recommendedEvents = new ArrayList<>();
            if (allEvents.size() > 1) {
                Iterator<Event> iterator = allEvents.iterator();
                while (iterator.hasNext()) {
                    Event event = iterator.next();
                    Date startDate = event.getStart_date();
                    Long timeDifference = startDate.getTime() - new Date().getTime();
                    int days = (int) (timeDifference / (1000 * 60 * 60 * 24));
                    if (days < 5) {
                        iterator.remove();
                    } else if (event.getAvailable_tickets() <= 1) {
                        iterator.remove();
                    }
                }
            }
            if (allEvents.size() > 3) {
                for (int i = 0; i < 3; i++) {
                    recommendedEvents.add(allEvents.get(i));
                }
            }
            response.put("state", true);
            response.put("msg", "find event for index page success");
            response.put("Recommended", recommendedEvents);
            response.put("Live_Concerts", liveConcerts);
            response.put("Movies", movies);
            response.put("Drama", drama);
            response.put("sport", sport);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("state", false);
            response.put("hint", e.getMessage());
            response.put("msg", "event fails to find for index page");
        }
        return response;
    }

    /**
     * Delete one event by host
     *
     * @param id
     * @return
     */
    @ApiOperation("Delete one event by event id router in event controller")
    @GetMapping("/delete/{id}")
    public Map<String, Object> delete(@PathVariable(value = "id") Integer id) {
        log.info("deleter user id:[{}]", id);
        Map<String, Object> response = new HashMap<>();
        try {
            Event event = eventService.findOne(id);
            if (event == null) {
                response.put("state", false);
                response.put("msg", "event id is invalid");
                return response;
            } else {
//                File file = new File(realPath, event.getCover_image());
//                if (file.exists()) {
//                    file.delete();
//                }
                eventService.delete(id);
                response.put("state", true);
                response.put("msg", "event deletes success");
                return response;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("state", false);
            response.put("hint", e.getMessage());
            response.put("msg", "event fails to delete");
            return response;
        }
    }

    /**
     * Create one event by host
     *
     * @param event
     * @param session
     * @return
     * @throws IOException
     */
    @ApiOperation("Create one event with cover image router in event controller")
    @PostMapping("/create")
    public Map<String, Object> save(@RequestBody Event event, HttpSession session) throws IOException {
        Map<String, Object> response = new HashMap<>();
        User currentUser = (User) session.getAttribute(Consts.CURRENT_USER);
        log.info("event:[{}]", event.toString());
        try {
            event.setCreated_at(new Date());
            event.setUpdated_at(new Date());
            event.setUser_id(currentUser.getId());
            event.setStatus(1);
            eventService.save(event);
            response.put("state", true);
            response.put("msg", "event information saves success");
        } catch (Exception e) {
            e.printStackTrace();
            response.put("state", false);
            response.put("msg", "event information fails to save");
        }
        return response;
    }

    /**
     * Find all events created by host
     *
     * @param session
     * @return
     */
    @ApiOperation("Get all related event information by host id router in event controller")
    @GetMapping("/findByHost")
    public Map<String, Object> findByHost(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        User currentUser = (User) session.getAttribute(Consts.CURRENT_USER);
        Integer userId = currentUser.getId();
        log.info("user id : [{}]", userId);
        List<Event> eventsByHost = eventDao.findHost(userId, 1);
        if (eventsByHost.size() == 0) {
            response.put("state", false);
            response.put("msg", "no event found by host");
        } else {
            response.put("state", true);
            response.put("msg", "find event success");
            response.put("event", eventsByHost);
        }
        return response;
    }

    /**
     * Get events by type
     *
     * @param request
     * @param session
     * @return
     */
    @ApiOperation("Get all related event information by event type router in event controller")
    @ResponseBody
    @PostMapping("/findByType")
    public Map<String, Object> findByType(@RequestBody Map<String, Object> request, HttpSession session) {
        String type = (String) request.get("type");
        Map<String, Object> response = new HashMap<>();
        List<Event> events = eventService.findByType(type);
        if (events.size() == 0) {
            response.put("state", false);
            response.put("msg", "no event found by event type");
        } else {
            response.put("state", true);
            response.put("msg", "find event success");
            response.put("event", events);
        }
        return response;
    }

    /**
     * Search events by keyword
     *
     * @param request
     * @param session
     * @return
     */
    @ApiOperation("Search events by keyword router in event controller")
    @ResponseBody
    @PostMapping("/findByKeyword")
    public Map<String, Object> findByKeyword(@RequestBody Map<String, Object> request, HttpSession session) {
        String keyword = (String) request.get("keyword");
        Map<String, Object> response = new HashMap<>();
        List<Event> events = eventService.findByKeyword(keyword);
        if (events.size() == 0) {
            response.put("state", false);
            response.put("msg", "no event found by keyword");
        } else {
            response.put("state", true);
            response.put("msg", "find event success");
            response.put("event", events);
        }
        return response;
    }
}
