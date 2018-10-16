import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";

import {HttpModule} from "@angular/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

import {AppComponent} from "./app.component";
import {LoginComponent} from "./login/login.component";

import {ProfileViewComponent} from "./profile/profile-view/profile-view.component";
import {ForgotPasswordComponent} from "./profile/forgot-password/forgot-password.component";
import {ProfileEditComponent} from "./profile/profile-edit/profile-edit.component";
import {AppRoutingModule} from "./app-routing.module";
import {AuthenticationService} from "./login/authentication.service";
import {AuthGuard} from "./guards/auth.guard";
import {GlobalApp} from "./helpers/global";
import {PersonService} from "./profile/shared/person.service";
import {NavbarComponent} from "./shared/navbar/navbar.component";
import {FooterComponent} from "./shared/footer/footer.component";
import {AngularMaterialModule} from "./angular-material.module";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {RouterModule} from "@angular/router";
import { JobViewComponent } from './job/job-view/job-view.component';
import {JobService} from "./job/shared/job.service";
import { JobAddComponent } from './job/job-add/job-add.component';
import {JobHomeComponent} from "./job/job-home/job-home.component";
import { JobsCompletedComponent } from './profile/jobs-completed/jobs-completed.component';
import {ChangePasswordComponent} from "./profile/change-password/change-password.component";
import {JobTypeService} from "./job/shared/jobtype.service";
import {ProfileModalComponent} from "./profile/profile-modal/profile-modal.component";
import {NotificationnService} from "./profile/shared/notification.service";
import {JobsPendingComponent} from "./profile/jobs-pending/jobs-pending.component";
import { RegisterComponent } from './register/register.component';

@NgModule({
  entryComponents: [
    ProfileModalComponent
  ],
  declarations: [
    AppComponent,
    LoginComponent,
    ProfileViewComponent,
    ForgotPasswordComponent,
    ProfileEditComponent,
    NavbarComponent,
    FooterComponent,
    JobViewComponent,
    JobAddComponent,
    JobHomeComponent,
    JobsCompletedComponent,
    JobsPendingComponent,
    ChangePasswordComponent,
    ProfileModalComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    NgbModule.forRoot(),
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    HttpModule,
    BrowserAnimationsModule,
    AngularMaterialModule,
    AppRoutingModule
  ],
  providers: [
    AuthenticationService,
    AuthGuard,
    GlobalApp,
    PersonService,
    JobService,
    JobTypeService,
    NotificationnService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
