package com.smit.tracker.track.domain;

import java.util.List;

import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smit.tracker.track.domain.dto.TrackPointData;

interface TrackRepository extends JpaRepository<Track, Long> {
    Track findByPoint(Point point);

    @Query(value = "SELECT t.id, t.latitude, t.longitude FROM track t WHERE ST_DWithin(t.point,:point, 0.2 / 111.325)", nativeQuery = true)
    List<TrackPointData> findAllWithinRadius(@Param("point") Point point);
    
}
