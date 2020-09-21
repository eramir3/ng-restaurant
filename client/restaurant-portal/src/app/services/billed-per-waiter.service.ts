import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';
import { Subject } from 'rxjs';

import { BilledPerWaiter } from '../models/billed-per-waiter';

@Injectable({
  providedIn: 'root'
})
export class BilledPerWaiterService {

  billedPerWaiterChanged = new Subject<BilledPerWaiter[]>();
  private billedPerWaiter: BilledPerWaiter[] = [];

  constructor(
    private http: HttpClient
  ) { }

  setBilledPerWaiters(clients: BilledPerWaiter[]) {
    this.billedPerWaiter = clients['data'];
    this.billedPerWaiterChanged.next(this.billedPerWaiter.slice());
  }

  getBilledPerWaiters() {
    return this.billedPerWaiter.slice();
  }

  fetchBilledPerWaiter() {
    return this.http.get<BilledPerWaiter[]>('http://localhost:8080/api/waiter/totalBilledByWaiter/2020')
    .pipe(tap(clients => {
            this.setBilledPerWaiters(clients);
        })
    );
  }
}
