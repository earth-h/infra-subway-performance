package nextstep.subway.station.application;

import nextstep.subway.station.domain.Station;
import nextstep.subway.station.domain.StationRepository;
import nextstep.subway.station.dto.StationRequest;
import nextstep.subway.station.dto.StationResponse;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class StationService {
    private final StationRepository stationRepository;

    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Caching(
            put = @CachePut(value = "station", key = "#result.id"),
            evict = {
                    @CacheEvict(value = "stations", allEntries = true),
                    @CacheEvict(value = "line", key = "#result.id"),
                    @CacheEvict(value = "lines", allEntries = true),
                    @CacheEvict(value = "path", allEntries = true)
            }
    )
    @Transactional
    public StationResponse saveStation(StationRequest stationRequest) {
        Station station = stationRepository.save(stationRequest.toStation());
        return StationResponse.of(station);
    }

    @Cacheable(value = "stations", unless = "#result.empty")
    public List<StationResponse> findAllStations() {
        List<Station> stations = stationRepository.findAll();

        return stations.stream()
                .map(StationResponse::of)
                .collect(Collectors.toList());
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "station", key = "#id"),
                    @CacheEvict(value = "stations", allEntries = true),
                    @CacheEvict(value = "line", key = "#id"),
                    @CacheEvict(value = "lines", allEntries = true),
                    @CacheEvict(value = "path", allEntries = true)
            }
    )
    @Transactional
    public void deleteStationById(Long id) {
        stationRepository.deleteById(id);
    }

    public Station findStationById(Long id) {
        return stationRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Station findById(Long id) {
        return stationRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
