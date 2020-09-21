import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ClientListComponent } from './components/client/client-list/client-list.component';
import { ClientCreateComponent } from './components/client/client-create/client-create.component';
import { ClientEditComponent } from './components/client/client-edit/client-edit.component';
import { WaiterListComponent } from './components/waiter/waiter-list/waiter-list.component';
import { WaiterCreateComponent } from './components/waiter/waiter-create/waiter-create.component';
import { WaiterEditComponent } from './components/waiter/waiter-edit/waiter-edit.component';
import { CookListComponent } from './components/cook/cook-list/cook-list.component';
import { CookCreateComponent } from './components/cook/cook-create/cook-create.component';
import { CookEditComponent } from './components/cook/cook-edit/cook-edit.component';
import { DiningTableListComponent } from './components/dining-table/dining-table-list/dining-table-list.component';
import { DiningTableCreateComponent } from './components/dining-table/dining-table-create/dining-table-create.component';
import { DiningTableEditComponent } from './components/dining-table/dining-table-edit/dining-table-edit.component';
import { BillingComponent } from './components/billing/billing.component'
import { ProfitableClientComponent } from './components/profitable-client/profitable-client.component';
import { BilledPerWaiterComponent } from './components/billed-per-waiter/billed-per-waiter.component';

const routes: Routes = [
  { path: '', redirectTo: '/billing', pathMatch: 'full' },
  { path: 'admin', children : [
    // Client
    { path: 'client', component: ClientListComponent },
    { path: 'client/create', component: ClientCreateComponent },
    { path: 'client/edit/:id', component: ClientEditComponent },

    // Waiter
    { path: 'waiter', component: WaiterListComponent },
    { path: 'waiter/create', component: WaiterCreateComponent },
    { path: 'waiter/edit/:id', component: WaiterEditComponent },

    // Cook
    { path: 'cook', component: CookListComponent },
    { path: 'cook/create', component: CookCreateComponent },
    { path: 'cook/edit/:id', component: CookEditComponent },

    // Dining table
    { path: 'dining-table', component: DiningTableListComponent },
    { path: 'dining-table/create', component: DiningTableCreateComponent },
    { path: 'dining-table/edit/:id', component: DiningTableEditComponent },
  ] },
  { path: 'billing', component: BillingComponent },
  { path: 'profitableClient', component: ProfitableClientComponent} ,
  { path: 'billedPerWaiter', component: BilledPerWaiterComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
