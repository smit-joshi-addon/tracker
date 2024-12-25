package com.smit.tracker.track.domain;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import com.smit.tracker.track.constants.TrackConstants;
import com.smit.tracker.track.domain.dto.TrackPoint;
import com.smit.tracker.track.domain.dto.TrackPointData;

public class TrackMapper {

    private static final GeometryFactory GEOMETRY_FACTORY = new GeometryFactory();

        static Track toTrack(TrackPoint createTrack) {
                Point point = GEOMETRY_FACTORY.createPoint(new Coordinate(createTrack.latitude(), createTrack.longitude()));
                point.setSRID(TrackConstants.SRID);
            return new Track(null, createTrack.latitude(), createTrack.longitude(), point);
        }

        static Point toPoint (Double latitude, Double longitude) {
            Point point = GEOMETRY_FACTORY.createPoint(new Coordinate(latitude, longitude));
            point.setSRID(TrackConstants.SRID);
            return point;
        }

    static Track toTrack(TrackPointData updateTrack) {
        Point point = GEOMETRY_FACTORY.createPoint(new Coordinate(updateTrack.latitude(), updateTrack.longitude()));
        point.setSRID(TrackConstants.SRID);
        return new Track(updateTrack.id(), updateTrack.latitude(), updateTrack.longitude(), point);
    }

    static TrackPointData fromTrack(Track track) {
        return new TrackPointData(track.getId(), track.getLatitude(), track.getLongitude());
    }
}
