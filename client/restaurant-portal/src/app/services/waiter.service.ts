import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';
import { Subject } from 'rxjs';

import { Waiter } from '../models/Waiter';


@Injectable()
export class WaiterService {

    waitersChanged = new Subject<Waiter[]>();

    private waiters: Waiter[] = [];
 
    constructor(
        private http: HttpClient
    ) { }

    saveWaiter(waiter: Waiter) {
        this.http.post('http://localhost:8080/api/waiter/save', waiter)
        .subscribe(response => {
            console.log(response);
        });
    }

    fetchWaiters() {
        return this.http.get<Waiter[]>('http://localhost:8080/api/waiter/all')
        .pipe(tap(waiters => {
                this.setWaiters(waiters);
            })
        );
    }

    fetchWaiter(id: number) {
        return this.http.get<Waiter>('http://localhost:8080/api/waiter/'+id)
        .pipe(tap(waiter => {
            return waiter;
        }));
    }

    deleteWaiter(index: number, id: number) {
        this.http.delete('http://localhost:8080/api/waiter/'+id).subscribe(response => {
            this.waiters.splice(index, 1);
            this.waitersChanged.next(this.waiters.slice());
        });
    }

    setWaiters(waiters: Waiter[]) {
        this.waiters = waiters['data'];
        this.waitersChanged.next(this.waiters.slice());
    }

    getWaiters() {
        return this.waiters.slice();
    }

    getWaiter(id: number) {
        let searchedWaiter = null;
        this.waiters.forEach(waiter => {
            if (id === waiter.id) {
                searchedWaiter = waiter;
                return;
            }
        });
        return searchedWaiter;
    }

}