import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserCuisineComponent } from './user-cuisine.component';

describe('UserCuisineComponent', () => {
  let component: UserCuisineComponent;
  let fixture: ComponentFixture<UserCuisineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserCuisineComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserCuisineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
