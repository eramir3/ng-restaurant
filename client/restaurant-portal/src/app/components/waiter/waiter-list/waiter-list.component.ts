import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';

import { WaiterService } from '../../../services/waiter.service';
import { Waiter } from '../../../models/Waiter';


@Component({
  selector: 'app-waiter-list',
  templateUrl: './waiter-list.component.html',
  styleUrls: ['./waiter-list.component.css']
})
export class WaiterListComponent implements OnInit {

  waiters: Waiter[] = [];
  subscription: Subscription;
  waiter: Waiter;
  waiterIndex: number;


  constructor(
    private router:Router,
    private route: ActivatedRoute,
    private waiterService: WaiterService,
  ) { }

  ngOnInit(): void {
    this.fetchWaiters();
  }

  fetchWaiters() {
    this.waiterService.fetchWaiters().subscribe();
    this.subscription = this.waiterService.waitersChanged
      .subscribe((waiters: Waiter[]) => {
        this.waiters = waiters;
      }
    );
    this.waiters = this.waiterService.getWaiters();
  }

  createWaiterView() {
    this.router.navigate(['create'], {relativeTo: this.route});
  }

  editWaiterView(waiter: Waiter) {
    this.router.navigate(['edit', waiter.id], {relativeTo: this.route});
  }

  removeSelectedWaiter() {
    this.waiterService.deleteWaiter(this.waiterIndex, this.waiter.id);
  }

  setWaiterForDelete(index: number, waiter: Waiter) {
    this.waiterIndex = index;
    this.waiter = waiter;
  }

}