export class Cuisine
{
    cuisineId:string;
    cuisineName:string;
    cuisineDescription:string;
    price:number;
    image:any;

    constructor(cuisineId:any, cuisineName:any, cuisineDescription:any, price:any, image:any)
    {   
        this.cuisineId = cuisineId;
        this.cuisineName = cuisineName;
        this.cuisineDescription = cuisineDescription;
        this.price = price;
        this.image = image;
    }
}