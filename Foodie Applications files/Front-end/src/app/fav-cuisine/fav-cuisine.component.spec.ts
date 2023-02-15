import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FavCuisineComponent } from './fav-cuisine.component';

describe('FavCuisineComponent', () => {
  let component: FavCuisineComponent;
  let fixture: ComponentFixture<FavCuisineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FavCuisineComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FavCuisineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
