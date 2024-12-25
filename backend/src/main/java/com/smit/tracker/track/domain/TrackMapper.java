package com.smit.tracker.track.domain;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import com.smit.tracker.track.domain.dto.TrackPoint;
import com.smit.tracker.track.domain.dto.TrackPointData;

public class TrackMapper {

    private static final GeometryFactory GEOMETRY_FACTORY = new GeometryFactory();

        static Track toTrack(TrackPoint createTrack) {
            // Create a Point from the latitude and longitude
            // Point point = GEOMETRY_FACTORY.createPoint(new Coordinate(createTrack.latitude(), createTrack.longitude()));
            // point.setSRID(3857);
            // Return the Track object
                Point point = GEOMETRY_FACTORY.createPoint(new Coordinate(createTrack.latitude(), createTrack.longitude()));
                point.setSRID(3857);
            return new Track(null, createTrack.latitude(), createTrack.longitude(), point);
        }

        static Point toPoint (Double latitude, Double longitude) {
            // Create a Point from the latitude and longitude
            Point point = GEOMETRY_FACTORY.createPoint(new Coordinate(latitude, longitude));
            point.setSRID(3857);
            return point;
        }

    static Track toTrack(TrackPointData updateTrack) {
        // Create a Point from the latitude and longitude
        Point point = GEOMETRY_FACTORY.createPoint(new Coordinate(updateTrack.latitude(), updateTrack.longitude()));
        point.setSRID(3857);
        // Return the Track object
        return new Track(updateTrack.id(), updateTrack.latitude(), updateTrack.longitude(), point);
    }

    static TrackPointData fromTrack(Track track) {
        // Return the TrackPointData object
        return new TrackPointData(track.getId(), track.getLatitude(), track.getLongitude());
    }
    


    
}
