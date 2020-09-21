import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DiningTable } from 'src/app/models/dining-table';
import { DiningTableService } from 'src/app/services/dining-table.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-dining-table-create',
  templateUrl: './dining-table-create.component.html',
  styleUrls: ['./dining-table-create.component.css']
})
export class DiningTableCreateComponent implements OnInit {

  newDiningTable: DiningTable = new DiningTable();

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private diningTableService: DiningTableService,
    private datepipe: DatePipe
  ) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.diningTableService.saveDiningTable(this.newDiningTable);
  }

  onCancel() {
    this.router.navigate(['../'], { relativeTo: this.route });
  }

}
