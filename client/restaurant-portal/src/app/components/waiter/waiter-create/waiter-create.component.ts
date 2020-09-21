import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Waiter } from 'src/app/models/Waiter';
import { WaiterService } from 'src/app/services/waiter.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-waiter-create',
  templateUrl: './waiter-create.component.html',
  styleUrls: ['./waiter-create.component.css'],
})
export class WaiterCreateComponent implements OnInit {

  newWaiter: Waiter = new Waiter();

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private waiterService: WaiterService,
    private datepipe: DatePipe
  ) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.newWaiter['birthDate'] = this.datepipe.transform(this.newWaiter['birthDate'], 'MM-dd-yyyy');
    this.waiterService.saveWaiter(this.newWaiter);
  }

  onCancel() {
    this.router.navigate(['../'], { relativeTo: this.route });
  }
}
