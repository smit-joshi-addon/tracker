import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TrackRoutingModule } from './track-routing.module';
import { CardComponent } from '../theme/shared/components/card/card.component';
import { TrackerComponent } from './tracker/tracker.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [TrackerComponent],
  imports: [
    CommonModule,
    TrackRoutingModule,
    CardComponent,
    FormsModule
  ]
})
export class TrackModule { }
