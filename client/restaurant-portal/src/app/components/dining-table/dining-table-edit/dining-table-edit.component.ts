import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { FormGroup, FormControl, Validators} from '@angular/forms';
import { DiningTableService } from '../../../services/dining-table.service';
import { DiningTable } from 'src/app/models/dining-table';

@Component({
  selector: 'app-dining-table-edit',
  templateUrl: './dining-table-edit.component.html',
  styleUrls: ['./dining-table-edit.component.css']
})
export class DiningTableEditComponent implements OnInit {

  id: number;
  diningTableForm: FormGroup;
  diningTable: DiningTable;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private diningTableService: DiningTableService
    ) { }

  ngOnInit(): void {

    this.diningTableForm = this.initForm();

    this.route.params.forEach((params: Params) => {
      this.id = Number.parseInt(params['id']);
      console.log('id', this.id);
      
      this.diningTableService.fetchDiningTable(this.id).subscribe(response => {
        console.log('diningTablerr', response['data']);
        this.diningTable = response['data'];
        this.fillForm();
      })
    });
  }

  onSubmit() {
    this.diningTableForm.addControl('id', new FormControl(this.id));
    this.diningTableService.saveDiningTable(this.diningTableForm.value);
  }

  fillForm() {
    let numDiners = 0;
    let location = '';

    numDiners = this.diningTable.numDiners;
    location = this.diningTable.location;

    this.diningTableForm = new FormGroup({
      'numDiners': new FormControl(numDiners, Validators.required),
      'location': new FormControl(location, Validators.required)
    });
  }

  initForm() {
    return new FormGroup({
      'numDiners': new FormControl('', Validators.required),
      'location': new FormControl('', Validators.required),
    });
  } 

  onCancel() {
    this.router.navigate(['../../'], { relativeTo: this.route });
  }

}
