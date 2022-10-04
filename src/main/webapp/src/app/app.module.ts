import {AuthInterceptor} from '../_helpers/auth.interceptor';
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
import { GoogleMapsModule } from '@angular/google-maps';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';

@NgModule({
    declarations: [
        AppComponent,
        DonorComponent,
        DonorAddComponent,
        DonorListComponent,
        HeaderComponent,
        LoginComponent,
        HomeComponent,
        ProfileComponent,
    ],
    imports: [
        GooglePlaceModule,
        GoogleMapsModule,
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule,
        NotifierModule.withConfig({
            // Custom options in here
            position: {
                horizontal: { position: 'right' },
                vertical: { position: 'top', distance: 76 },
            },
        }),
    ],
    providers: [DonorService, AuthInterceptor],
    bootstrap: [AppComponent],
})
export class AppModule {}
