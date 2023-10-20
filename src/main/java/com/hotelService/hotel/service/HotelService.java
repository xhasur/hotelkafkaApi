package com.hotelService.hotel.service;


import com.hotelService.hotel.exception.TechBusinessException;
import com.hotelService.hotel.persistence.HotelRepository;
import com.hotelService.hotel.persistence.HotelSearchRepository;
import com.hotelService.hotel.persistence.entity.HotelEntity;
import com.hotelService.hotel.persistence.entity.SearchHotelEntity;
import com.hotelService.hotel.persistence.mapper.HotelMapper;
import com.hotelService.hotel.persistence.mapper.HotelSearchMapper;
import com.hotelService.hotel.service.dto.HotelDto;
import com.hotelService.hotel.service.dto.SearchHotelDto;
import com.hotelService.hotel.service.dto.SearchHotelWrapper;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


@Service
public class HotelService {

    private static Logger LOGGER = LogManager.getLogger(HotelService.class);

    private static final String TOPIC = "availability";

    private static final String TOPIC_HOTEL = "hotel";

    private static final String GROUP = "group-id";

    private final KafkaService kafkaService;

    private final HotelSearchRepository hotelSearchRepository;

    private final HotelRepository hotelRepository;

    private final HotelSearchMapper mapper;

    private final HotelMapper hotelMapper;

    @Autowired
    public HotelService(KafkaService kafkaService, HotelSearchRepository hotelRepository, HotelSearchMapper mapper, HotelRepository hotelRepository1, HotelSearchMapper mapper1, HotelMapper hotelMapper) {
        this.kafkaService = kafkaService;
        this.hotelSearchRepository = hotelRepository;
        this.hotelRepository = hotelRepository1;
        this.mapper = mapper1;
        this.hotelMapper = hotelMapper;
    }

    private static SearchHotelWrapper setWrapper(SearchHotelEntity hotel) {
        return new SearchHotelWrapper(
                hotel.getSearchId(),
                new SearchHotelDto(hotel.getHotelId(), hotel.getCheck(), hotel.getCheckOut(), hotel.getAges()),
                hotel.getOccurrence()
        );
    }

    public SearchHotelWrapper getCount(String searchId) {
        return this.hotelSearchRepository.findById(searchId)
                .map(HotelService::setWrapper)
                .orElseThrow(() -> new TechBusinessException("No se encontraron registros de busqueda de hoteles"));
    }

    public long getNextId() {
        return this.hotelSearchRepository.count() + 1;
    }

    public boolean exist(SearchHotelDto searchHotel) {
        return this.findSearch(searchHotel) != null ? true : false;
    }

    public SearchHotelEntity findSearch(SearchHotelDto searchHotel) {
        return this.hotelSearchRepository.findByHotelIdAndCheckAndCheckOutAndAges(
                searchHotel.getHotelId(),
                searchHotel.getCheckIn(),
                searchHotel.getCheckOut(),
                searchHotel.getAges()).orElse(null);
    }

    public String save(SearchHotelDto searchHotel, int occurrence) {
        String nextId = String.valueOf(this.getNextId());
        SearchHotelEntity entity = mapper.toSearchHotelEntity(searchHotel);
        entity.setSearchId(nextId);
        entity.setOccurrence(occurrence);
        this.hotelSearchRepository.save(entity);
        return nextId;
    }

    public String getId(SearchHotelDto searchHotel){
        return this.findSearch(searchHotel).getSearchId();
    }

    public String update(SearchHotelDto searchHotel) {
        SearchHotelEntity entity = this.findSearch(searchHotel);
        entity.setOccurrence(entity.getOccurrence() + 1);
        this.hotelSearchRepository.save(entity);
        return entity.getSearchId();
    }

    public String sendMessage(SearchHotelDto searchHotel)  {
        if (searchHotel.getHotelId() == null || !this.exist(searchHotel)) {;
            return this.save(searchHotel, 1);
        }
        return this.update(searchHotel);
    }

    @KafkaListener(topics = TOPIC, groupId = GROUP)
    public void listen(SearchHotelDto message) {
        if (message.getHotelId() == null || !this.exist(message)) {;
            this.save(message, 1);
        }
        this.update(message);
    }

    public String sendKafkaMessage(SearchHotelDto searchHotel) throws ExecutionException, InterruptedException {
        CompletableFuture<SendResult<String, SearchHotelDto>> future = this.kafkaService.sendMessage(searchHotel);
        CompletableFuture<SearchHotelDto> greetingFuture = future.thenApply(name -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return name.getProducerRecord().value();
        }).exceptionally(ex -> {
            LOGGER.error("Exception" ,ex);
            throw new TechBusinessException("Error Calling kafka ");
        });
        greetingFuture.get();
        return getId(searchHotel);
    }

    @KafkaListener(topics = TOPIC_HOTEL, groupId = GROUP)
    public void listen(HotelDto message) {
        this.saveHotel(message);
    }

    public HotelEntity saveHotelKafkaMessage(@Valid HotelDto hotel) throws ExecutionException, InterruptedException {
        CompletableFuture<SendResult<String, SearchHotelDto>> future = this.kafkaService.sendMessageHotel(hotel);
        CompletableFuture<SearchHotelDto> greetingFutureHotel = future.thenApply(name -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return name.getProducerRecord().value();
        }).exceptionally(ex -> {
            LOGGER.error("Exception" ,ex);
            throw new TechBusinessException("Error Calling kafka ");
        });
        greetingFutureHotel.get();
        return getIdHotelSaved(hotel);
    }

    private void saveHotel(HotelDto message) {
        HotelEntity hotel = hotelMapper.toHotelEntity(message);
        this.hotelRepository.save(hotel);
    }

    private HotelEntity getIdHotelSaved(HotelDto hotel) {
        return  this.hotelRepository.findByNameAndType(hotel.getName(), hotel.getType()).orElse(null);
    }
}