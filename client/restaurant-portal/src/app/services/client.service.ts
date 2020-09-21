import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';
import { Subject } from 'rxjs';

import { Client } from '../models/client';

@Injectable()
export class ClientService {

    clientsChanged = new Subject<Client[]>();
    

    private clients: Client[] = [];
    

 
    constructor(
        private http: HttpClient
    ) { }

    saveClient(client: Client) {
        this.http.post('http://localhost:8080/api/client/save', client)
        .subscribe(response => {
            console.log(response);
        });
    }

    fetchClients() {
        return this.http.get<Client[]>('http://localhost:8080/api/client/all')
        .pipe(tap(clients => {
                this.setClients(clients);
            })
        );
    }

    fetchClient(id: number) {
        return this.http.get<Client>('http://localhost:8080/api/client/'+id)
        .pipe(tap(client => {
            return client;
        }));
    }

    deleteClient(index: number, id: number) {
        this.http.delete('http://localhost:8080/api/client/'+id).subscribe(response => {
            this.clients.splice(index, 1);
            this.clientsChanged.next(this.clients.slice());
        });
        
    }

    setClients(clients: Client[]) {
        this.clients = clients['data'];
        this.clientsChanged.next(this.clients.slice());
    }

    getClients() {
        return this.clients.slice();
    }

    getClient(id: number) {
        let searchedClient = null;
        this.clients.forEach(client => {
            if (id === client.id) {
                searchedClient = client;
                return;
            }
        });
        return searchedClient;
    }
}
