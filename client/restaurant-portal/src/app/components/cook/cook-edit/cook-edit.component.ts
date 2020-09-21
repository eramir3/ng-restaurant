import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { FormGroup, FormControl, Validators} from '@angular/forms';
import { CookService } from '../../../services/cook.service';
import { Cook } from 'src/app/models/Cook';

@Component({
  selector: 'app-cook-edit',
  templateUrl: './cook-edit.component.html',
  styleUrls: ['./cook-edit.component.css']
})
export class CookEditComponent implements OnInit {

  id: number;
  cookForm: FormGroup;
  cook: Cook;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private cookService: CookService
    ) { }

  ngOnInit(): void {

    this.cookForm = this.initForm();

    this.route.params.forEach((params: Params) => {
      this.id = Number.parseInt(params['id']);
      console.log('id', this.id);
      
      this.cookService.fetchCook(this.id).subscribe(response => {
        console.log('cookrr', response['data']);
        this.cook = response['data'];
        this.fillForm();
      })
    });
  }

  onSubmit() {
    this.cookForm.addControl('id', new FormControl(this.id));
    this.cookService.saveCook(this.cookForm.value);
  }

  fillForm() {
    let firstName = '';
    let lastName = '';
    let birthDate = '';
    let remarks = '';

    firstName = this.cook.firstName;
    lastName = this.cook.lastName;
    birthDate = this.cook.birthDate;
    remarks = this.cook.remarks;

    this.cookForm = new FormGroup({
      'firstName': new FormControl(firstName, Validators.required),
      'lastName': new FormControl(lastName, Validators.required),
      'birthDate': new FormControl(birthDate, Validators.required),
      'remarks': new FormControl(remarks, Validators.required),
    });
  }

  initForm() {
    return new FormGroup({
      'firstName': new FormControl('', Validators.required),
      'lastName': new FormControl('', Validators.required),
      'birthDate': new FormControl('', Validators.required),
      'remarks': new FormControl('', Validators.required)
    });
  } 

  onCancel() {
    this.router.navigate(['../../'], { relativeTo: this.route });
  }

}
