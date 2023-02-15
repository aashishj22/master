export class User
{
    emailId:string;
    profilePicture:any;
    firstName:string;
    lastName:string;
    role:string;
    gender:string;
    password:string;
    // private List<Restaurant> favouriteRestaurant;
    // private List<Cuisine> favouriteCuisine;

    constructor(emailId:any, profilePicture:any, firstName:any, lastName:any, role:any, gender:any,password:any)
    {
        this.emailId = emailId;
        this.profilePicture = profilePicture;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.gender = gender;
        this.password = password;
    }
} 