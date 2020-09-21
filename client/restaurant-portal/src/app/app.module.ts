import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BillingComponent } from './components/billing/billing.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';

import { ProfitableClientService } from './services/profitable-client.service';
import { BilledPerWaiterService } from './services/billed-per-waiter.service';
import { WaiterService } from './services/waiter.service';
import { ClientService } from './services/client.service';
import { DiningTableService } from './services/dining-table.service';
import { CookService } from './services/cook.service';
import { DropdownDirective } from './directives/dropdown.directive';
import { BilledPerWaiterComponent } from './components/billed-per-waiter/billed-per-waiter.component';
import { WaiterListComponent } from './components/waiter/waiter-list/waiter-list.component';
import { WaiterCreateComponent } from './components/waiter/waiter-create/waiter-create.component';

import { DatePipe } from '@angular/common';
import { WaiterEditComponent } from './components/waiter/waiter-edit/waiter-edit.component';
import { ModalDirective } from './directives/modal.directive';
import { ClientListComponent } from './components/client/client-list/client-list.component';
import { ClientCreateComponent } from './components/client/client-create/client-create.component';
import { ClientEditComponent } from './components/client/client-edit/client-edit.component';
import { CookListComponent } from './components/cook/cook-list/cook-list.component';
import { CookCreateComponent } from './components/cook/cook-create/cook-create.component';
import { CookEditComponent } from './components/cook/cook-edit/cook-edit.component';
import { DiningTableListComponent } from './components/dining-table/dining-table-list/dining-table-list.component';
import { DiningTableCreateComponent } from './components/dining-table/dining-table-create/dining-table-create.component';
import { DiningTableEditComponent } from './components/dining-table/dining-table-edit/dining-table-edit.component';
import { ProfitableClientComponent } from './components/profitable-client/profitable-client.component';

@NgModule({
  declarations: [
    AppComponent,
    BillingComponent,
    NavBarComponent,
    DropdownDirective,
    BilledPerWaiterComponent,
    WaiterListComponent,
    WaiterCreateComponent,
    WaiterEditComponent,
    ModalDirective,
    ClientListComponent,
    ClientCreateComponent,
    ClientEditComponent,
    CookListComponent,
    CookCreateComponent,
    CookEditComponent,
    DiningTableListComponent,
    DiningTableCreateComponent,
    DiningTableEditComponent,
    ProfitableClientComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    BsDatepickerModule.forRoot()
  ],
  providers: [
    WaiterService,
    ClientService,
    DiningTableService,
    CookService,
    ProfitableClientService,
    BilledPerWaiterService,
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
