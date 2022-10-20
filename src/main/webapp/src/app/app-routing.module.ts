import {HashLocationStrategy} from '@angular/common/common';
import {ProfileComponent} from './profile/profile.component';
import {LoginComponent} from './login/login.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DonorListComponent} from '../app/donor/donor-list/donor-list.component';
import {DonorAddComponent} from '../app/donor/donor-add/donor-add.component'

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
    }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
