import { Cuisine } from "./cuisine";

export class Restaurant
{
    restaurantId:string;
    restaurantName:string;
    restaurantLocation:string;
    cuisineList:Cuisine[] = [];
    picByte: any;
    

    constructor(restaurantId:any, restaurantName:any, restaurantLocation:any, cuisineList:Cuisine[])
    {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.restaurantLocation = restaurantLocation;
        this.cuisineList = cuisineList;
    }
}