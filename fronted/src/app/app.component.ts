import {Component, OnInit} from '@angular/core';
import {AuthConfig, NullValidationHandler, OAuthService} from 'angular-oauth2-oidc';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {

  constructor(private oauthService: OAuthService) {
  }

  title = 'fronted';

  authConfig: AuthConfig = {
    issuer: 'http://localhost:8180/auth/realms/SpringHotel',
    redirectUri: window.location.origin + '/rooms',
    clientId: 'spa-client',
    responseType: 'code',
    scope: 'openid profile email ',
    requireHttps: false,
    // at_hash is not present in JWT token
    disableAtHashCheck: true,
    showDebugInformation: true
  };

  ngOnInit(): void {
    this.configure();
  }

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
}
