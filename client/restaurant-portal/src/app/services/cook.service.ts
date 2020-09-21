import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';
import { Subject } from 'rxjs';

import { Cook } from '../models/Cook';

@Injectable()
export class CookService {

  cooksChanged = new Subject<Cook[]>();

  private cooks: Cook[] = [];

  constructor(
      private http: HttpClient
  ) { }

  saveCook(cook: Cook) {
      this.http.post('http://localhost:8080/api/cook/save', cook)
      .subscribe(response => {
          console.log(response);
      });
  }

  fetchCooks() {
      return this.http.get<Cook[]>('http://localhost:8080/api/cook/all')
      .pipe(tap(cooks => {
              this.setCooks(cooks);
          })
      );
  }

  fetchCook(id: number) {
      return this.http.get<Cook>('http://localhost:8080/api/cook/'+id)
      .pipe(tap(cook => {
          return cook;
      }));
  }

  deleteCook(index: number, id: number) {
      this.http.delete('http://localhost:8080/api/cook/'+id).subscribe(response => {
          this.cooks.splice(index, 1);
          this.cooksChanged.next(this.cooks.slice());
      });
  }

  setCooks(cooks: Cook[]) {
      this.cooks = cooks['data'];
      this.cooksChanged.next(this.cooks.slice());
  }

  getCooks() {
      return this.cooks.slice();
  }

  getCook(id: number) {
      let searchedCook = null;
      this.cooks.forEach(cook => {
          if (id === cook.id) {
              searchedCook = cook;
              return;
          }
      });
      return searchedCook;
  }
}
