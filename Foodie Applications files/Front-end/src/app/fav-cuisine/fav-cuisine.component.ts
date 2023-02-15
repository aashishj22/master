import { Component, OnInit } from '@angular/core';
import { FavouriteService } from '../service/favourite.service';

@Component({
  selector: 'app-fav-cuisine',
  templateUrl: './fav-cuisine.component.html',
  styleUrls: ['./fav-cuisine.component.css'],
})
export class FavCuisineComponent implements OnInit {
  public items: any = [];
  show: boolean = false;
  constructor(private fav: FavouriteService) {
    this.getUserCuisine();
  }

  ngOnInit(): void {
  }

  cuisineList:any;

  getUserCuisine()
  {
    this.fav.getItems().subscribe(
      res => {
        this.items=res;
      }
    )
  }

  removeFav(cuisineId:string){
    
    this.fav.removeFavItem(cuisineId).subscribe(
      res=>{
        alert("favourite removed");
        this.getUserCuisine();
      }
    );
    console.log(cuisineId)
    // window.location.reload();
  }
}
