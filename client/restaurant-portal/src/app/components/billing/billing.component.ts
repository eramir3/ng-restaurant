import { Component, OnInit } from '@angular/core';
import { WaiterService } from '../../services/waiter.service';
import { Waiter } from '../../models/Waiter';
import { DiningTableService } from '../../services/dining-table.service';
import { DiningTable } from '../../models/dining-table';
import { CookService } from '../../services/cook.service';
import { Cook } from '../../models/Cook';

@Component({
  selector: 'app-billing',
  templateUrl: './billing.component.html',
  styleUrls: ['./billing.component.css']
})
export class BillingComponent implements OnInit {

  waiters: Waiter[] = [];
  selectedWaiter: Waiter;

  tables: DiningTable[] = [];
  selectedTable: DiningTable;

  cooks: Cook[] = [];
  selectedCook: Cook;

  constructor(
    private waiterService: WaiterService,
    private tableService: DiningTableService,
    private cookService: CookService
  ) { }

  ngOnInit(): void {
    this.fetchWaiters();
    this.fetchDiningTables();
    this.fetchCooks();
  }

  fetchWaiters() {
    this.waiterService.fetchWaiters().subscribe(waiters => {
        this.waiters = waiters['data'];
        this.selectedWaiter = this.waiters[0];
      });
  }

  fetchDiningTables() {
    this.tableService.fetchDiningTables().subscribe(tables => {
      this.tables = tables['data'];
      this.selectedTable = this.tables[0];
    })
  }

  fetchCooks() {
    this.cookService.fetchCooks().subscribe(cooks => {
      this.cooks = cooks['data'];
      this.selectedCook = this.cooks[0];
    })
  }

  onWaiterChanged() {
    console.log(this.selectedWaiter);
  }

}
