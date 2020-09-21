import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';


import { ClientService } from '../../../services/client.service';
import { Client } from '../../../models/client';

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {

  clients: Client[] = [];
  subscription: Subscription;
  client: Client;
  clientIndex: number;

  constructor(
    private router:Router,
    private route: ActivatedRoute,
    private clientService: ClientService,
  ) { }

  ngOnInit(): void {
    this.fetchClients();
  }

  fetchClients() {
    this.clientService.fetchClients().subscribe();
    this.subscription = this.clientService.clientsChanged
      .subscribe((clients: Client[]) => {
        this.clients = clients;
      }
    );
    this.clients = this.clientService.getClients();
  }

  createClientView() {
    this.router.navigate(['create'], {relativeTo: this.route});
  }

  editClientView(client: Client) {
    this.router.navigate(['edit', client.id], {relativeTo: this.route});
  }

  removeSelectedClient() {
    this.clientService.deleteClient(this.clientIndex, this.client.id);
  }

  setClientForDelete(index: number, client: Client) {
    this.clientIndex = index;
    this.client = client;
  }

}
