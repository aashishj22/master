import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService 
{
  emailId:any;
  role:any;
  baseUrl = "http://localhost:9000/api/v2/register"
  baseUrl2 = "http://localhost:8085/authservice/" 
  constructor(private httpClient: HttpClient) {
    this.getUserDetails();
   }

  registerUser(formData:FormData):Observable<any>
  {
    return this.httpClient.post(this.baseUrl, formData);
  }

  loginCheck(userobj:any)
  {
    return this.httpClient.post(this.baseUrl2 + "login", userobj);
  }

  getUserDetails()
  {
    return this.httpClient.get("http://localhost:9000/api/v2/user/" + this.emailId);
  }
}
