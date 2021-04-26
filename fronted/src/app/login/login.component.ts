import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from '../service/auth-service';
import {UserService} from '../service/user.service';
import {AuthConfig, NullValidationHandler, OAuthService} from 'angular-oauth2-oidc';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  error = '';

  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private router: Router,
              private authService: AuthService,
              private userService: UserService,
              private oauthService: OAuthService) {
    this.configure();
    if (this.authService.currentUserValue) {
      this.router.navigate(['cabinet']);
    }


  }

  authConfig: AuthConfig = {
    issuer: 'http://localhost:8180/auth/realms/SpringHotel',
    redirectUri: window.location.origin + '/rooms',
    clientId: 'spa-client',
    responseType: 'code',
    scope: 'openid profile email',
    // at_hash is not present in JWT token
    disableAtHashCheck: true,
    showDebugInformation: true
  };

  public login(): void {
    this.oauthService.initLoginFlow();
  }

  public logoff(): void {
    this.oauthService.logOut();
  }

  private configure(): void {
    this.oauthService.configure(this.authConfig);
    this.oauthService.tokenValidationHandler = new NullValidationHandler();
    this.oauthService.loadDiscoveryDocumentAndTryLogin();
  }

  getUserClaims(): void {
    const user = this.oauthService.loadUserProfile();
    console.log(user, user);
  }


  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });

    // get return url from route parameters or default to '/'
    // this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  // convenience getter for easy access to form fields
  get f(): any {
    return this.loginForm.controls;
  }

  onSubmit(): void {
    this.submitted = true;

    // stop here if form is invalid
    if (this.loginForm.invalid) {
      return;
    }
    console.log(this.f.username.value, this.f.password.value);
    this.authService.login(this.f.username.value, this.f.password.value).pipe().subscribe(
      data => {
        this.router.navigate(['cabinet']);
      },
      error => {
        this.error = error;
        this.loading = false;
      });

  }


}
