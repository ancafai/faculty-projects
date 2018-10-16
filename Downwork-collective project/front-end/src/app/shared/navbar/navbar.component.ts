import {Location, LocationStrategy, PathLocationStrategy} from "@angular/common";
import {Component, OnInit, Inject, Renderer, ElementRef, Input, OnDestroy} from "@angular/core";
import { Router, NavigationEnd } from "@angular/router";
import { Subscription } from "rxjs/Subscription";
import "rxjs/add/operator/filter";
import { DOCUMENT } from "@angular/platform-browser";
import {GlobalApp} from "../../helpers/global";
import {NotificationnService} from "../../profile/shared/notification.service";
import {Notificationn} from "../../profile/shared/notification.model";
import {Observable} from "rxjs/Observable";
import { TimerObservable } from "rxjs/observable/TimerObservable";

@Component({
  selector: "app-navbar",
  templateUrl: "./navbar.component.html",
  styleUrls: ["./navbar.component.scss"]
})
export class NavbarComponent implements OnInit, OnDestroy {

  @Input() isTransparent: boolean;

  app: GlobalApp;

  private toggleButton: any;
  private alive: boolean;

  private sidebarVisible: boolean;


  private _router: Subscription;

  constructor(private element: ElementRef,
              private renderer: Renderer,
              private router: Router, @Inject(DOCUMENT, )
              private document: any,
              public location: Location,
              private notificationService: NotificationnService) {
    this.sidebarVisible = false;
    this.app = new GlobalApp();
  }

  ngOnInit() {
    this.alive = true;
    TimerObservable.create(0, 10000).subscribe(() => {
        let username = localStorage.getItem("username");
        if(username != null)
          this.getNotifications(username);
      });



    // const navbar: HTMLElement = this.element.nativeElement;
    // this.toggleButton = navbar.getElementsByClassName("navbar-toggler")[0];
    // const navbarScroll: HTMLElement = this.element.nativeElement.children[0];
    // this._router = this.router.events.filter(event => event instanceof NavigationEnd).subscribe((event: NavigationEnd) => {
    //   if (window.outerWidth > 991) {
    //     window.document.children[0].scrollTop = 0;
    //   } else {
    //     window.document.activeElement.scrollTop = 0;
    //   }
    //   this.sidebarClose();
    // });
    // this.renderer.listenGlobal("window", "scroll", (event) => {
    //   const number = window.scrollY;
    //   if (number > 150 || window.pageYOffset > 150) {
    //     // add logic
    //     navbarScroll.classList.remove("navbar-transparent");
    //   } else {
    //     // remove logic
    //     navbarScroll.classList.add("navbar-transparent");
    //   }
    // });
    // const ua = window.navigator.userAgent;
    // const trident = ua.indexOf("Trident/");
    // let version = 0;
    // if (trident > 0) {
    //   // IE 11 => return version number
    //   const rv = ua.indexOf("rv:");
    //   version = parseInt(ua.substring(rv + 3, ua.indexOf(".", rv)), 10);
    // }
    // if (version !== 0) {
    //   const body = document.getElementsByTagName("body")[0];
    //   body.classList.add("ie-background");
    // }
  }

  getNotifications(username:string) {
    this.notificationService.getNotificationns(username).subscribe(data => {
      const notificationns: Notificationn[] = data;
      for (let i = 0; i < notificationns.length ; i++) {
        if(!notificationns[i].read) {
          alert(notificationns[i].message);
        }
      }
    });
  }

  sidebarOpen() {
    const toggleButton = this.toggleButton;
    const html = document.getElementsByTagName("html")[0];
    // console.log(html);
    // console.log(toggleButton, 'toggle');

    setTimeout(function () {
      toggleButton.classList.add("toggled");
    }, 500);
    html.classList.add("nav-open");

    this.sidebarVisible = true;
  }

  sidebarClose() {
    const html = document.getElementsByTagName("html")[0];
    // console.log(html);
    this.toggleButton.classList.remove("toggled");
    this.sidebarVisible = false;
    html.classList.remove("nav-open");
  }

  sidebarToggle() {
    // const toggleButton = this.toggleButton;
    // const body = document.getElementsByTagName('body')[0];
    if (this.sidebarVisible === false) {
      this.sidebarOpen();
    } else {
      this.sidebarClose();
    }
  }

  isHome() {
    const titlee = this.location.prepareExternalUrl(this.location.path());

    if (titlee === "/home") {
      return true;
    } else {
      return false;
    }
  }

  ngOnDestroy() {
    this.alive = false;
  }

}
