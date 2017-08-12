import {Injectable} from '@angular/core';
import {Http, Headers, Response, RequestOptions} from '@angular/http';
import {Observable} from 'rxjs';
import 'rxjs/add/operator/map'

@Injectable()
export class AuthenticationService {
    public token: string;

    constructor(private http: Http) {
        // set token if saved in local storage
        var currentUser = JSON.parse(localStorage.getItem('currentUser'));
        this.token = currentUser && currentUser.token;
    }

    login(username: string, password: string): Observable<boolean> {
        let credentials = 'password=1234&username=apssouza22@gmail.com&grant_type=password&scope=write&client_secret=123456&client_id=todo-app';
        let headers = new Headers({
            'Accept': 'application/json',
            'Access-Control-Allow-Origin': '*'
        });

        let options = new RequestOptions({headers: headers});
        return this.http.post(
            'http://todo-app:123456@localhost:8017/oauth/token',
            credentials,
            options
        )
            .map((response: Response) => {
                console.log(response);
                // login successful if there's a jwt token in the response
                let token = response.json() && response.json().token;
                if (token) {
                    this.token = token;
                    localStorage.setItem(
                        'currentUser', JSON.stringify({username: username, token: token})
                    );
                    return true;
                } else {
                    return false;
                }
            });
    }

    logout(): void {
        this.token = null;
        localStorage.removeItem('currentUser');
    }
}