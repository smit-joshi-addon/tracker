package com.smit.tracker.track.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.smit.tracker.track.domain.TrackService;
import com.smit.tracker.track.domain.dto.TrackPoint;
import com.smit.tracker.track.domain.dto.TrackPointData;
import com.smit.tracker.utility.ResponseWrapper;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RequiredArgsConstructor
@Controller
@RequestMapping("/track")
@CrossOrigin(origins = "http://localhost:4200")
class TrackController {

    private final TrackService trackService;

    
    @GetMapping
    ResponseEntity<ResponseWrapper<List<TrackPointData>>> getAllTracks(@RequestParam Double latitude, @RequestParam Double longitude){
        ResponseWrapper<List<TrackPointData>> response = trackService.findAll(latitude, longitude);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST );
    }

    @PostMapping
    ResponseEntity<ResponseWrapper<TrackPointData>> saveTrack(@RequestBody TrackPoint track){
        ResponseWrapper<TrackPointData> response = trackService.save(track);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper<TrackPointData>> getTrackBy(@PathVariable Long id) {
        ResponseWrapper<TrackPointData> response = trackService.findById(id);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST );
    }


    @DeleteMapping("/{id}")
    ResponseEntity<ResponseWrapper<String>> deleteTrack(@PathVariable Long id){
        trackService.deleteById(id);
        return new ResponseEntity<>(ResponseWrapper.success("Track Data Deleted Successfully", null), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deleteAll")
    ResponseEntity<ResponseWrapper<String>> deleteAllTracks(){
        trackService.deleteAll();
        return new ResponseEntity<>(ResponseWrapper.success("All Track Data Deleted Successfully", null), HttpStatus.NO_CONTENT);
    }
    
}
