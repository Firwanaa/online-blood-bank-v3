import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { DonorService } from './donor/donorr.service'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DonorComponent } from './donor/donor.component';
import { DonorAddComponent } from './donor/donor-add/donor-add.component';
import { DonorListComponent } from './donor/donor-list/donor-list.component';
import { HeaderComponent } from './header/header.component';
import { NotifierModule } from 'angular-notifier';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    DonorComponent,
    DonorAddComponent,
    DonorListComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NotifierModule.withConfig({
            // Custom options in here
            position: {
                horizontal: { position: 'right' },
                vertical: { position: 'top', distance: 56 },
            },
        }),
  ],
  providers: [DonorService],
  bootstrap: [AppComponent]
})
export class AppModule { }
