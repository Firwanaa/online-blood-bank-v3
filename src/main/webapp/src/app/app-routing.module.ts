import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import{DonorListComponent} from '../app/donor/donor-list/donor-list.component';
import{DonorAddComponent} from '../app/donor/donor-add/donor-add.component'

const routes: Routes = [
	{
	path:'InstitutionDonorList',
	component:DonorListComponent
	},
		{
	path:'Register',
	component:DonorAddComponent
	}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
