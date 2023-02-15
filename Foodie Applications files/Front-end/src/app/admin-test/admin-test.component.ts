import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { Restaurant } from '../model/restaurant';
import { User } from '../model/user';
import { RestaurantService } from '../service/restaurant.service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-admin-test',
  templateUrl: './admin-test.component.html',
  styleUrls: ['./admin-test.component.css']
})
export class AdminTestComponent implements OnInit {
  id:any;
  constructor(private userService:UserService,private route:ActivatedRoute ,private dialog : MatDialog, private restaurantService:RestaurantService, private fb:FormBuilder, private httpClient : HttpClient, private sanitizer: DomSanitizer) 
  {
    this.id=this.route.snapshot.params['id'];
    this.emailId=this.route.snapshot.params['emailId'];
    this.getAllRestaurants();
    // this.getUserDetails();
  }

  ngOnInit(): void {
  }

  restaurants:Restaurant[]= [];
  restaurantId='';
  restaurantData:any;
  public userFile1:any = File;
  public userFile2:any = File;
  retrievedImage: any;
  data: any;
  profileImage:any;
  user:any;
  emailId:any;

  getAllRestaurants()
  {
    this.restaurantService.getAllRestaurants().subscribe(
      data=>
      {
        this.restaurants=data;
        this.retrievedImage = 'data:image/jpeg;base64,' + this.data.picByte;
      }
    );
  }

  deleteRestaurant(restaurantId:any)
  {
    this.restaurantService.deleteRestaurant(restaurantId).subscribe(
      data=>
      {
        alert("Deleted");
        this.getAllRestaurants();
      }
    )
  }

  // getUserDetails()
  // {
  //   this.userService.getUserDetails().subscribe(
  //     data => {console.log(data)
  //     }
  //   )
  // }
}
