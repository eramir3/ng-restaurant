import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';

import { CookService } from '../../../services/cook.service';
import { Cook } from '../../../models/Cook';

@Component({
  selector: 'app-cook-list',
  templateUrl: './cook-list.component.html',
  styleUrls: ['./cook-list.component.css']
})
export class CookListComponent implements OnInit {

  cooks: Cook[] = [];
  subscription: Subscription;
  cook: Cook;
  cookIndex: number;


  constructor(
    private router:Router,
    private route: ActivatedRoute,
    private cookService: CookService,
  ) { }

  ngOnInit(): void {
    this.fetchCooks();
  }

  fetchCooks() {
    this.cookService.fetchCooks().subscribe();
    this.subscription = this.cookService.cooksChanged
      .subscribe((cooks: Cook[]) => {
        this.cooks = cooks;
      }
    );
    this.cooks = this.cookService.getCooks();
  }

  createCookView() {
    this.router.navigate(['create'], {relativeTo: this.route});
  }

  editCookView(cook: Cook) {
    this.router.navigate(['edit', cook.id], {relativeTo: this.route});
  }

  removeSelectedCook() {
    this.cookService.deleteCook(this.cookIndex, this.cook.id);
  }

  setCookForDelete(index: number, cook: Cook) {
    this.cookIndex = index;
    this.cook = cook;
  }

}
