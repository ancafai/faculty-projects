import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { JobsCompletedComponent } from './jobs-completed.component';

describe('JobsCompletedComponent', () => {
  let component: JobsCompletedComponent;
  let fixture: ComponentFixture<JobsCompletedComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ JobsCompletedComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JobsCompletedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
