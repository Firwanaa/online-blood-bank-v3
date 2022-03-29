import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NotifierModule } from 'angular-notifier';
import { GooglePlaceModule } from 'ngx-google-places-autocomplete';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DonorAddComponent } from './donor/donor-add/donor-add.component';
import { DonorListComponent } from './donor/donor-list/donor-list.component';
import { DonorComponent } from './donor/donor.component';
import { DonorService } from './donor/donor.service';
import { HeaderComponent } from './header/header.component';

@NgModule({
    declarations: [
        AppComponent,
        DonorComponent,
        DonorAddComponent,
        DonorListComponent,
        HeaderComponent,
    ],
    imports: [
        GooglePlaceModule,
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule,
        NotifierModule.withConfig({
            // Custom options in here
            position: {
                horizontal: { position: 'right' },
                vertical: { position: 'top', distance: 56 },
            },
        }),
    ],
    providers: [DonorService],
    bootstrap: [AppComponent],
})
export class AppModule {}
