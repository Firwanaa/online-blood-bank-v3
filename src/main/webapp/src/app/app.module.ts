import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { DonnerService } from './donner/donner.service'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DonnerComponent } from './donner/donner.component';
import { DonnerAddComponent } from './donner/donner-add/donner-add.component';
import { DonnerListComponent } from './donner/donner-list/donner-list.component';
import { HeaderComponent } from './header/header.component';
import { NotifierModule } from 'angular-notifier';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    DonnerComponent,
    DonnerAddComponent,
    DonnerListComponent,
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
  providers: [DonnerService],
  bootstrap: [AppComponent]
})
export class AppModule { }
