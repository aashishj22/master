import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {

  restaurantId:any;
  baseUrl = "http://localhost:65100/api/v1"
  constructor(private httpClient : HttpClient, private fb : FormBuilder) { }

  getAllCuisine()
  {
    return this.httpClient.get(this.baseUrl + "/" +this.restaurantId + "/cuisine");
  }

  getAllRestaurants():Observable<any>
  {
    return this.httpClient.get(this.baseUrl + "/restaurants");
  }

  getRestaurant(id:any)
  {
    return this.httpClient.get(this.baseUrl + "/" + id);
  }

  deleteRestaurant(restaurantid:any)
  {
    return this.httpClient.delete(this.baseUrl + "/" + restaurantid);
  }

  addCuisine(cuisineobj:any)
  {
    return this.httpClient.post(this.baseUrl + "/" + this.restaurantId + "/cuisine", cuisineobj);
  }

  deleteCuisine(cuisineid:any)
  {
    return this.httpClient.delete(this.baseUrl + "/" + this.restaurantId + "/" + cuisineid);
  }

  addRestaurant(formData:FormData):Observable<any>
  {
    return this.httpClient.post(this.baseUrl + "/restaurant", formData);
  }
}
