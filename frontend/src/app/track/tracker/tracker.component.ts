import { Component } from '@angular/core';

// Extend MarkerOptions to include id property
declare module 'leaflet' {
  interface MarkerOptions {
    id?: number;
  }
}
import * as L from 'leaflet';
import { TrackPointData } from 'src/app/theme/shared/models/track_point.model';
import { TrackerService } from 'src/app/theme/shared/services/tracker.service';

@Component({
  selector: 'app-tracker',
  templateUrl: './tracker.component.html',
  styleUrl: './tracker.component.scss',
})
export class TrackerComponent {
  private trackedPoints: TrackPointData[] = [];
  private map: L.Map | undefined;
  isObserverMode = false;

  constructor(private service: TrackerService) {
    // constructor
  }

  ngOnInit(): void {
    this.initMap();
  }

  private initMap(): void {
    this.map = L.map('map', {
      center: [23.0225, 72.5714], // Coordinates for map center (latitude, longitude)
      zoom: 19,
    });

    // Add OpenStreetMap tiles
    L.tileLayer('https://server.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer/tile/{z}/{y}/{x}', {
      maxZoom: 20,
      maxNativeZoom: 19,
    }).addTo(this.map!);

    // Change the cursor to normal
    this.map.getContainer().style.cursor = 'default';

    // when i click on the map, it will show the coordinates
    this.map.on('click', (e) => {
      if (this.isObserverMode) {
        console.log('Observer mode is on');

        this.map!.eachLayer((layer) => {
          if (layer instanceof L.Marker) {
            this.map!.removeLayer(layer);
          }
        });

        this.getTrackPoints(e.latlng.lat.toString(), e.latlng.lng.toString());
      } else {
        console.log('Observer mode is off');
        console.log(`Latitude: ${e.latlng.lat}, Longitude: ${e.latlng.lng}`); // e.latlng.lat and e.latlng.lng are the latitude and longitude of the point where you clicked
        // add a popup to the map
        const marker = L.marker([e.latlng.lat, e.latlng.lng]).addTo(this.map!).bindPopup(`${e.latlng.lat} , ${e.latlng.lng}`).openPopup();
        (marker as any).id = Date.now();

        this.saveTrackPoint(e.latlng.lat.toString(), e.latlng.lng.toString());

        // Add click event listener to the marker
        let clickCount = 0;
        marker.on('click', () => {
          clickCount++;
          if (clickCount === 2) {
            console.log(`Removing marker with ID: ${marker.options.id}, Latitude: ${marker.getLatLng().lat}, Longitude: ${marker.getLatLng().lng}`);
            this.map!.removeLayer(marker);
          }
        });
      }
      this.removeLeafletShadowPane();
    });
  }

  saveTrackPoint(lat: string, long: string): void {
    const data: TrackPointData = {
      latitude: Number.parseFloat(lat),
      longitude: Number.parseFloat(long),
    };
    this.service.saveTrackPoint(data).subscribe((res) => {
      console.log(res);
    });
  }

  onToggleChange(event: any): void {
    console.log('Toggle state:', this.isObserverMode);
    this.handleToggleState(this.isObserverMode);
  }

  handleToggleState(isChecked: boolean): void {
    if (isChecked) {
      this.isObserverMode = true;
      // this.getTrackPoints();
      // Perform action for ON state
    } else {
      this.isObserverMode = false;
      this.map!.eachLayer((layer) => {
        if (layer instanceof L.Marker) {
          this.map!.removeLayer(layer);
        }
      });
    }
  }

  getTrackPoints(latitude: string, longitude: string): void {
    this.service.getTrackPoints(latitude, longitude).subscribe((res) => {
      console.log(res);
      this.trackedPoints = res;
      console.log(this.trackedPoints.length);
      this.trackedPoints.forEach((point) => {
        const marker = L.marker([point.latitude, point.longitude], { id: point.id }).addTo(this.map!).bindPopup(`${point.latitude} , ${point.longitude}`).openPopup();

        // Add click event listener to the marker
        let clickCount = 0;
        marker.on('click', () => {
          clickCount++;
          if (clickCount === 2) {
            console.log(`Removing marker with ID: ${marker.options.id}, Latitude: ${marker.getLatLng().lat}, Longitude: ${marker.getLatLng().lng}`);
            this.service.removeTrackPoint(marker.options.id!).subscribe((res) => { 
              console.log(res);
            });
            this.map!.removeLayer(marker);
          }
        });
      });
    });
  }

  removeLeafletShadowPane() {
    const shadowPane = document.querySelector('.leaflet-pane.leaflet-shadow-pane');
    if (shadowPane) {
      shadowPane.remove();
    }
  }
}