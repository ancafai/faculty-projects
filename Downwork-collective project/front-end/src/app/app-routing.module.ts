import {Routes, RouterModule} from "@angular/router";
import {NgModule} from "@angular/core";
import {LoginComponent} from "./login/login.component";
import {AuthGuard} from "./guards/auth.guard";
import {ProfileViewComponent} from "./profile/profile-view/profile-view.component";
import {ProfileEditComponent} from "./profile/profile-edit/profile-edit.component";
import {ForgotPasswordComponent} from "./profile/forgot-password/forgot-password.component";
import {CommonModule} from "@angular/common";
import {BrowserModule} from "@angular/platform-browser";
import {JobViewComponent} from "./job/job-view/job-view.component";
import {JobAddComponent} from "./job/job-add/job-add.component";
import {JobHomeComponent} from "./job/job-home/job-home.component";
import {JobsCompletedComponent} from "./profile/jobs-completed/jobs-completed.component";
import {ChangePasswordComponent} from "./profile/change-password/change-password.component";
import {JobsPendingComponent} from "./profile/jobs-pending/jobs-pending.component";

const routes: Routes = [{
  path: "",
  redirectTo: "login",
  pathMatch: "full",
  canActivate: [AuthGuard]
},
  {
    path: "profile/:username",
    canActivate: [AuthGuard],
    component: ProfileViewComponent
  },

  {
    path: "login",
    component: LoginComponent
  },

  {
    path: "profile/edit/:username",
    component: ProfileEditComponent
  },

  {
    path: "profile/completedJobs/:username",
    component: JobsCompletedComponent
  },

  {
    path: "profile/jobsPending/:username",
    component: JobsPendingComponent
  },

  {
    path: "resetpassword",
    component: ForgotPasswordComponent
  },

  {
    path: "reset/:token",
    component: ChangePasswordComponent
  },
  {
    path: "job/view/:id",
    component: JobViewComponent

  },
  {
    path: "job/home",
    component: JobHomeComponent
  },
  {
    path: "job/create",
    // canActivate: [AuthGuard],
    component: JobAddComponent
  }
];

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    RouterModule.forRoot(routes)
  ],
  exports: [],
})
export class AppRoutingModule {
}
