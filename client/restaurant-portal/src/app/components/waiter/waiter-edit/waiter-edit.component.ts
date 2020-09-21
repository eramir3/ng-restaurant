import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { FormGroup, FormControl, Validators} from '@angular/forms';
import { WaiterService } from '../../../services/waiter.service';
import { Waiter } from 'src/app/models/Waiter';

@Component({
  selector: 'app-waiter-edit',
  templateUrl: './waiter-edit.component.html',
  styleUrls: ['./waiter-edit.component.css']
})
export class WaiterEditComponent implements OnInit {

  id: number;
  waiterForm: FormGroup;
  waiter: Waiter;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private waiterService: WaiterService
    ) { }

  ngOnInit(): void {

    this.waiterForm = this.initForm();

    this.route.params.forEach((params: Params) => {
      this.id = Number.parseInt(params['id']);
      console.log('id', this.id);
      
      this.waiterService.fetchWaiter(this.id).subscribe(response => {
        console.log('waiterrr', response['data']);
        this.waiter = response['data'];
        this.fillForm();
      })
    });
  }

  onSubmit() {
    this.waiterForm.addControl('id', new FormControl(this.id));
    this.waiterService.saveWaiter(this.waiterForm.value);
  }

  fillForm() {
    let firstName = '';
    let lastName = '';
    let birthDate = '';
    let remarks = '';

    firstName = this.waiter.firstName;
    lastName = this.waiter.lastName;
    birthDate = this.waiter.birthDate;
    remarks = this.waiter.remarks;

    this.waiterForm = new FormGroup({
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
