import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Cook } from 'src/app/models/Cook';
import { CookService } from 'src/app/services/cook.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-cook-create',
  templateUrl: './cook-create.component.html',
  styleUrls: ['./cook-create.component.css']
})
export class CookCreateComponent implements OnInit {

  newCook: Cook = new Cook();

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private cookService: CookService,
    private datepipe: DatePipe
  ) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.newCook['birthDate'] = this.datepipe.transform(this.newCook['birthDate'], 'MM-dd-yyyy');
    this.cookService.saveCook(this.newCook);
  }

  onCancel() {
    this.router.navigate(['../'], { relativeTo: this.route });
  }

}
