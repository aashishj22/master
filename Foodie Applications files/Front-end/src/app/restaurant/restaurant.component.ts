import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DomSanitizer } from '@angular/platform-browser';
import { FileHandle } from '../model/FileHandle';
import { RestaurantService } from '../service/restaurant.service';

@Component({
  selector: 'app-restaurant',
  templateUrl: './restaurant.component.html',
  styleUrls: ['./restaurant.component.css']
})
export class RestaurantComponent implements OnInit {

  constructor(private sanitizer: DomSanitizer, private restaurantService:RestaurantService, private snackBar : MatSnackBar) { }

  ngOnInit(): void {
  }

  restaurantId='';
  restaurantData:any;
  public userFile1:any = File;
  public userFile2:any = File;


  restaurantForm = new FormGroup({
    restaurantId:new FormControl(''),
    restaurantName:new FormControl(''),
    restaurantLocation:new FormControl(''),
    picByte: new FormControl('')
  });

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

  addRestaurant(submitForm:FormGroup)
  {
    const restaurant = submitForm.value;
    const formData = new FormData();
    formData.append('restaurant', JSON.stringify(restaurant));
    formData.append('file',this.userFile2);
    this.restaurantService.addRestaurant(formData).subscribe(
      response =>
      {
        this.snackBar.open("Restaurant Added", "Close");
      }
    )
  }

}
