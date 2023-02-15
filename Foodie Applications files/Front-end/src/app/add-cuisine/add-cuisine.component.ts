import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { FileHandle } from '../model/FileHandle';
import { RestaurantService } from '../service/restaurant.service';

@Component({
  selector: 'app-add-cuisine',
  templateUrl: './add-cuisine.component.html',
  styleUrls: ['./add-cuisine.component.css']
})
export class AddCuisineComponent implements OnInit {
  constructor(private restaurantService:RestaurantService, private sanitizer: DomSanitizer) {
    this.getAllCuisine();
   }

  ngOnInit(): void {
  }

  cuisineForm = new FormGroup({
    cuisineId:new FormControl(''),
    cuisineName:new FormControl(''),
    cuisineDescription:new FormControl(''),
    price:new FormControl('')
  });
  
  cuisineData:any;

  public userFile1:any = File;
  public userFile2:any = File;

  getAllCuisine()
  {
    this.restaurantService.getAllCuisine().subscribe(
      response =>
      {
        this.cuisineData=response;
      }
    )
  }

  // addRestaurant(submitForm:FormGroup)
  // {
  //   const restaurant = submitForm.value;
  //   const formData = new FormData();
  //   formData.append('restaurant', JSON.stringify(restaurant));
  //   formData.append('file',this.userFile2);
  //   this.restaurantService.addRestaurant(formData).subscribe(
  //     response =>
  //     {
  //       this.snackBar.open("Restaurant Added", "Close");
  //     }
  //   )
  // }

  addCuisine(submitForm:FormGroup)
  {
    const cuisine = submitForm.value;
    const formData = new FormData();
    formData.append('cuisine', JSON.stringify(cuisine));
    formData.append('file',this.userFile2);
    this.restaurantService.addCuisine(formData).subscribe(
      response =>
      {
        alert("Cuisine added")
      }
    )
  }

  onFileSelect(event:any)
  {
    const file = event.target.files[0];
    const fileHandle : FileHandle={
      file: file,
      url:this.sanitizer.bypassSecurityTrustUrl(
        window.URL.createObjectURL(file)
      )
    }
    this.userFile1 = fileHandle;
    this.userFile2 = fileHandle.file;
  }
}
