package com.smit.tracker.track.domain;

import java.util.List;
import java.util.Optional;

import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import com.smit.tracker.track.constants.TrackConstants;
import com.smit.tracker.track.domain.dto.TrackPoint;
import com.smit.tracker.track.domain.dto.TrackPointData;
import com.smit.tracker.utility.ResponseWrapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
class TrackServiceImpl implements TrackService {

    private final TrackRepository trackRepository;

    @Override
    public ResponseWrapper<TrackPointData> save(TrackPoint track) {
        Track newTrack = TrackMapper.toTrack(track);
        Track savedTrack = trackRepository.save(newTrack);
        return ResponseWrapper.success(TrackConstants.SUCCESS_MESSAGE,TrackMapper.fromTrack(savedTrack));
    }

    @Override
    public ResponseWrapper<List<TrackPointData>> findAll(Double latitude, Double longitude) {
        Point point = TrackMapper.toPoint(latitude, longitude);
        List<TrackPointData> data =  trackRepository.findAllWithinRadius(point);
        return ResponseWrapper.success(TrackConstants.SUCCESS_MESSAGE, data);
    }

    @Override
    public ResponseWrapper<TrackPointData> findById(Long id) {
        Optional<Track> track = trackRepository.findById(id);
        if(track.isPresent()){
            return ResponseWrapper.success(TrackConstants.SUCCESS_MESSAGE, TrackMapper.fromTrack(track.get()));
        }
        return ResponseWrapper.failure(TrackConstants.ERROR_MESSAGE);
    }

    @Override
    public void deleteById(Long id) {
        trackRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        trackRepository.deleteAll();
    }

    
}
