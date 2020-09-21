import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';

import { DiningTableService } from '../../../services/dining-table.service';
import { DiningTable } from '../../../models/dining-table';

@Component({
  selector: 'app-dining-table-list',
  templateUrl: './dining-table-list.component.html',
  styleUrls: ['./dining-table-list.component.css']
})
export class DiningTableListComponent implements OnInit {

  diningTables: DiningTable[] = [];
  subscription: Subscription;
  diningTable: DiningTable;
  diningTableIndex: number;


  constructor(
    private router:Router,
    private route: ActivatedRoute,
    private diningTableService: DiningTableService,
  ) { }

  ngOnInit(): void {
    this.fetchDiningTables();
  }

  fetchDiningTables() {
    this.diningTableService.fetchDiningTables().subscribe();
    this.subscription = this.diningTableService.diningTablesChanged
      .subscribe((diningTables: DiningTable[]) => {
        this.diningTables = diningTables;
      }
    );
    this.diningTables = this.diningTableService.getDiningTables();
  }

  createDiningTableView() {
    this.router.navigate(['create'], {relativeTo: this.route});
  }

  editDiningTableView(diningTable: DiningTable) {
    this.router.navigate(['edit', diningTable.id], {relativeTo: this.route});
  }

  removeSelectedDiningTable() {
    this.diningTableService.deleteDiningTable(this.diningTableIndex, this.diningTable.id);
  }

  setDiningTableForDelete(index: number, diningTable: DiningTable) {
    this.diningTableIndex = index;
    this.diningTable = diningTable;
  }

}
