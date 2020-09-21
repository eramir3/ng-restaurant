import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';
import { Subject } from 'rxjs';

import { DiningTable } from '../models/dining-table';

@Injectable()
export class DiningTableService {

  diningTablesChanged = new Subject<DiningTable[]>();

  private diningTables: DiningTable[] = [];

  constructor(
      private http: HttpClient
  ) { }

  saveDiningTable(diningTable: DiningTable) {
      this.http.post('http://localhost:8080/api/table/save', diningTable)
      .subscribe(response => {
          console.log(response);
      });
  }

  fetchDiningTables() {
      return this.http.get<DiningTable[]>('http://localhost:8080/api/table/all')
      .pipe(tap(diningTables => {
              this.setDiningTables(diningTables);
          })
      );
  }

  fetchDiningTable(id: number) {
      return this.http.get<DiningTable>('http://localhost:8080/api/table/'+id)
      .pipe(tap(diningTable => {
          return diningTable;
      }));
  }

  deleteDiningTable(index: number, id: number) {
      this.http.delete('http://localhost:8080/api/table/'+id).subscribe(response => {
          this.diningTables.splice(index, 1);
          this.diningTablesChanged.next(this.diningTables.slice());
      });
  }

  setDiningTables(diningTables: DiningTable[]) {
      this.diningTables = diningTables['data'];
      this.diningTablesChanged.next(this.diningTables.slice());
  }

  getDiningTables() {
      return this.diningTables.slice();
  }

  getDiningTable(id: number) {
      let searchedDiningTable = null;
      this.diningTables.forEach(diningTable => {
          if (id === diningTable.id) {
              searchedDiningTable = diningTable;
              return;
          }
      });
      return searchedDiningTable;
  }
}
