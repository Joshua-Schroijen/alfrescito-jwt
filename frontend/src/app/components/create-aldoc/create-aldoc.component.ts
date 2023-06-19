import { Component } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

import { AldocsService } from '../../services/aldocs.service';

@Component({
  selector: 'create-aldoc',
  templateUrl: './create-aldoc.component.html',
  styleUrls: ['./create-aldoc.component.scss']
})
export class CreateAldocComponent {
  constructor(private toastr: ToastrService, private aldocsService: AldocsService) { }

  onSubmit(inputs: {[key: string]: File | string}): void {
    this.aldocsService.create(inputs["file"] as File)
      .subscribe({
        complete: () => {
          this.toastr.success('AlDoc created successfully', 'Success');
        },
        error: (e) => console.error(e)
      });
  }
}