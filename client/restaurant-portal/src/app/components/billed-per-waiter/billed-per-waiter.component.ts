import { Component, OnInit, Input } from '@angular/core';
import { Subscription } from 'rxjs';

import { BilledPerWaiterService } from '../../services/billed-per-waiter.service';
import { BilledPerWaiter } from '../../models/billed-per-waiter';

@Component({
  selector: 'app-billed-per-waiter',
  templateUrl: './billed-per-waiter.component.html',
  styleUrls: ['./billed-per-waiter.component.css']
})
export class BilledPerWaiterComponent implements OnInit {

  billedPerWaiter: BilledPerWaiter[] = [];
  subscription: Subscription;

  constructor(
    private billedPerWaiterService: BilledPerWaiterService,
  ) { }

  ngOnInit(): void {
    this.fetchbilledPerWaiter();
  }

  fetchbilledPerWaiter() {
    this.billedPerWaiterService.fetchBilledPerWaiter().subscribe();
    this.subscription = this.billedPerWaiterService.billedPerWaiterChanged
      .subscribe((billedPerWaiter: BilledPerWaiter[]) => {
        this.billedPerWaiter = billedPerWaiter;
      }
    );
    this.billedPerWaiter = this.billedPerWaiterService.getBilledPerWaiters();
  }

}
