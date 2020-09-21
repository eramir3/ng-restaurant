import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';
import { Subject } from 'rxjs';

import { ProfitableClient } from '../models/profitable-client';


@Injectable()
export class ProfitableClientService {

  profitableClientsChanged = new Subject<ProfitableClient[]>();
  private profitableClients: ProfitableClient[] = [];

  constructor(
    private http: HttpClient
  ) { }

  setProfitableClients(clients: ProfitableClient[]) {
    this.profitableClients = clients['data'];
    this.profitableClientsChanged.next(this.profitableClients.slice());
  }

  getProfitableClients() {
    return this.profitableClients.slice();
  }

  fetchProfitableClients() {
    return this.http.get<ProfitableClient[]>('http://localhost:8080/api/client/profitableClients')
    .pipe(tap(clients => {
            this.setProfitableClients(clients);
        })
    );
  }
}
