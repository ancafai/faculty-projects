import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { JobsPendingComponent } from './jobs-pending.component';

describe('JobsPendingComponent', () => {
  let component: JobsPendingComponent;
  let fixture: ComponentFixture<JobsPendingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ JobsPendingComponent ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JobsPendingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
