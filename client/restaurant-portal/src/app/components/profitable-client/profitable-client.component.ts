import { Component, OnInit, Input } from '@angular/core';
import { Subscription } from 'rxjs';

import { ProfitableClientService } from '../../services/profitable-client.service';
import { ProfitableClient } from '../../models/profitable-client';

@Component({
  selector: 'app-profitable-client',
  templateUrl: './profitable-client.component.html',
  styleUrls: ['./profitable-client.component.css']
})
export class ProfitableClientComponent implements OnInit {

  profitableClients: ProfitableClient[] = [];
  subscription: Subscription;


  constructor(
    private profitableClientService: ProfitableClientService,
  ) { }

  ngOnInit(): void {
    this.fetchProfitableClients();
  }

  fetchProfitableClients() {
    this.profitableClientService.fetchProfitableClients().subscribe();
    this.subscription = this.profitableClientService.profitableClientsChanged
      .subscribe((profitableClients: ProfitableClient[]) => {
        this.profitableClients = profitableClients;
      }
    );
    this.profitableClients = this.profitableClientService.getProfitableClients();
  }

}
