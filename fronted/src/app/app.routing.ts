import {RouterModule, Routes} from '@angular/router';
import {CabinetComponent} from './cabinet/cabinet/cabinet.component';
import {LoginComponent} from './login/login.component';
import {RoomsComponent} from './rooms/rooms.component';
import {OrderComponent} from './order/order.component';
import {AdminComponent} from './admin/admin.component';

const routes: Routes = [
  {path: 'cabinet', component: CabinetComponent},
  {path: 'login', component: LoginComponent},
  {path: 'rooms', component: RoomsComponent},
  {path: 'order', component: OrderComponent},
  {path: 'admin', component: AdminComponent},
  {path: '**', redirectTo: 'login'}
];

export const appRoutingModule = RouterModule.forRoot(routes);

