import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { Restaurant } from '../model/restaurant';
import { AdminService } from '../service/admin.service';
import { RestaurantService } from '../service/restaurant.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  id:any;
  emailId:any;
  constructor(private route:ActivatedRoute,private adminService:AdminService,private dialog : MatDialog, private restaurantService:RestaurantService, private fb:FormBuilder, private httpClient : HttpClient, private sanitizer: DomSanitizer) 
  {
    this.id=this.route.snapshot.params['id'];
    this.emailId=this.route.snapshot.params['emailId'];
    this.getUserDetails2();
    this.getAllRestaurants();
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
  user:any;
  profileImage:any;

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
