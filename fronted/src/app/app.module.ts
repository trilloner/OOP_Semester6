import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {CabinetComponent} from './cabinet/cabinet/cabinet.component';
import {HttpClientModule} from '@angular/common/http';
import {LoginComponent} from './login/login.component';
import {appRoutingModule} from './app.routing';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RoomsComponent} from './rooms/rooms.component';
import {OrderComponent} from './order/order.component';
import {AdminComponent} from './admin/admin.component';
import {KeycloakAngularModule} from 'keycloak-angular';
import {OAuthModule} from 'angular-oauth2-oidc';

@NgModule({
  declarations: [
    AppComponent,
    CabinetComponent,
    LoginComponent,
    RoomsComponent,
    OrderComponent,
    AdminComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    appRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    KeycloakAngularModule,
    OAuthModule.forRoot({
      resourceServer: {
        allowedUrls: ['http://localhost:8080/rooms'],
        sendAccessToken: true
      }
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
