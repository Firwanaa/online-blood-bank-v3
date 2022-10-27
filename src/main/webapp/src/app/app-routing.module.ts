import {HashLocationStrategy} from '@angular/common/common';
import {ProfileComponent} from './profile/profile.component';
import {LoginComponent} from './login/login.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DonorListComponent} from '../app/donor/donor-list/donor-list.component';
import {DonorAddComponent} from '../app/donor/donor-add/donor-add.component'
import { AboutUsComponent } from './about-us/about-us.component';
import { ContactComponent } from './contact/contact.component';

const routes: Routes = [
	{
	path:'all',
	component:DonorListComponent
	},
		{
	path:'register',
	component:DonorAddComponent
	},
    {
        path:'signin', component:LoginComponent
    },
    {
        path:'profile', component:ProfileComponent
    },
    {
        path:'about-us', component:AboutUsComponent
    },
    {
        path:'contact', component:ContactComponent
    }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
