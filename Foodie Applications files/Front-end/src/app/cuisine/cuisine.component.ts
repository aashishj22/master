import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AdminService } from '../service/admin.service';
import { RestaurantService } from '../service/restaurant.service';

@Component({
  selector: 'app-cuisine',
  templateUrl: './cuisine.component.html',
  styleUrls: ['./cuisine.component.css']
})
export class CuisineComponent implements OnInit {

  id:any;
  data: any;
  retrievedImage: any;
  user:any;
  profileImage:any;

  constructor(private adminService:AdminService,private route:ActivatedRoute, private restaurantService:RestaurantService) {
    this.id=this.route.snapshot.params['id'];
    this.getRestaurant();
    console.log(this.id);
   }

   cuisineForm = new FormGroup({
    cuisineId:new FormControl(''),
    cuisineName:new FormControl(''),
    cuisineDescription:new FormControl(''),
    price:new FormControl('')
  });

  cuisineData:any;

   ngOnInit(): void {}

   getRestaurant()
   {
     this.restaurantService.getRestaurant(this.id).subscribe(
       data=>{
           this.data = data; 
           console.log(data);
           this.restaurantService.restaurantId = this.data.restaurantId;
           this.retrievedImage = 'data:image/jpeg;base64,' + this.data.picByte;
       }
     )
   }
 
   deleteCuisine(cuisineId:any)
   {
     this.restaurantService.deleteCuisine(cuisineId).subscribe(
       data=>
       {
         alert("Deleted");
         this.getRestaurant();
       }
     )
   }

   getUserDetails2()
  {
    this.adminService.getUserDetails2(this.adminService.emailId).subscribe(
      data => 
      {
        console.log(data);
        this.user = data; 
        this.profileImage = 'data:image/jpeg;base64,' + this.user.profilePicture;
      }
    )
  }
}
