import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Client } from 'src/app/models/client';
import { ClientService } from 'src/app/services/client.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-client-create',
  templateUrl: './client-create.component.html',
  styleUrls: ['./client-create.component.css']
})
export class ClientCreateComponent implements OnInit {

  newClient: Client = new Client();

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private clientService: ClientService,
    private datepipe: DatePipe
  ) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.newClient['birthDate'] = this.datepipe.transform(this.newClient['birthDate'], 'MM-dd-yyyy');
    this.clientService.saveClient(this.newClient);
  }

  onCancel() {
    this.router.navigate(['../'], { relativeTo: this.route });
  }

}
