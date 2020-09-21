import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { FormGroup, FormControl, Validators} from '@angular/forms';
import { ClientService } from '../../../services/client.service';
import { Client } from 'src/app/models/client';

@Component({
  selector: 'app-client-edit',
  templateUrl: './client-edit.component.html',
  styleUrls: ['./client-edit.component.css']
})
export class ClientEditComponent implements OnInit {

  id: number;
  clientForm: FormGroup;
  client: Client;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private clientService: ClientService
    ) { }

  ngOnInit(): void {

    this.clientForm = this.initForm();

    this.route.params.forEach((params: Params) => {
      this.id = Number.parseInt(params['id']);
      console.log('id', this.id);
      
      this.clientService.fetchClient(this.id).subscribe(response => {
        console.log('clientrr', response['data']);
        this.client = response['data'];
        this.fillForm();
      })
    });
  }

  onSubmit() {
    this.clientForm.addControl('id', new FormControl(this.id));
    this.clientService.saveClient(this.clientForm.value);
  }

  fillForm() {
    let firstName = '';
    let lastName = '';
    let remarks = '';

    firstName = this.client.firstName;
    lastName = this.client.lastName;
    remarks = this.client.remarks;

    this.clientForm = new FormGroup({
      'firstName': new FormControl(firstName, Validators.required),
      'lastName': new FormControl(lastName, Validators.required),
      'remarks': new FormControl(remarks, Validators.required),
    });
  }

  initForm() {
    return new FormGroup({
      'firstName': new FormControl('', Validators.required),
      'lastName': new FormControl('', Validators.required),
      'remarks': new FormControl('', Validators.required)
    });
  } 

  onCancel() {
    this.router.navigate(['../../'], { relativeTo: this.route });
  }

}
