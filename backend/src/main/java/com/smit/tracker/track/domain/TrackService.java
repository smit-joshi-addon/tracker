package com.smit.tracker.track.domain;

import java.util.List;

import com.smit.tracker.track.domain.dto.TrackPoint;
import com.smit.tracker.track.domain.dto.TrackPointData;
import com.smit.tracker.utility.ResponseWrapper;

public interface TrackService {

    ResponseWrapper<TrackPointData> save(TrackPoint track);
    ResponseWrapper<List<TrackPointData>> findAll(Double latitude, Double longitude);
    ResponseWrapper<TrackPointData> findById(Long id);
    void deleteById(Long id);
    void deleteAll();
}
