// craete a service that will save the data of the lat long to the backend

import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";

import { map, Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { TrackPointData } from "../models/track_point.model";


@Injectable({
    providedIn: 'root'
  })
export class TrackerService {
    
    private url = `${environment.apiUrl}/track`;

    constructor(private http: HttpClient) {
        // constructor
    }
    
   public saveTrackPoint(data : TrackPointData) : Observable<TrackPointData> {
        // save the data to the backend
         return this.http.post<TrackPointData>(this.url, data);
       
    }

    public getTrackPoints(latitude: string, longitude: string): Observable<TrackPointData[]> {
        // Construct the URL with query parameters (latitude, longitude)
        const params = new HttpParams()
            .set('latitude', latitude)
            .set('longitude', longitude);
    
        return this.http.get<{ success: boolean; message: string; data: TrackPointData[] }>(this.url, { params })
            .pipe(
                map((response) => response.data)  // Extract the `data` field from the response
            );
    }

    public removeTrackPoint(id: number): Observable<string> {
        console.log('Removing marker with ID:', id);
        return this.http.delete<string>(`${this.url}/${id}`);
    }
    
}